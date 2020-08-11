package com.mindtree.productservice.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.productservice.controller.BrandController;
import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.entity.Model;
import com.mindtree.productservice.entity.Product;
import com.mindtree.productservice.exception.service.InvalidModelException;
import com.mindtree.productservice.exception.service.InvalidRangeException;
import com.mindtree.productservice.exception.service.NoProductFoundException;
import com.mindtree.productservice.exception.service.ServiceException;
import com.mindtree.productservice.repository.ModelRepository;
import com.mindtree.productservice.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService {
	
	private static Logger logger=LogManager.getLogger(ModelServiceImpl.class);
	
	private ModelRepository modelReopsitory;
	

	@Autowired
	public ModelServiceImpl(ModelRepository modelReopsitory) {
		this.modelReopsitory = modelReopsitory;
	}

	private ModelMapper mapper = new ModelMapper();

	@Override
	@Cacheable(value = "model",key="#modelName", condition = "#modelName!=null")
	@Transactional(readOnly = true)
	public List<ProductDto> filterByModelService(String modelName) throws ServiceException {
		try {
			logger.warn("Validating modelName");
			List<ProductDto> productDtoList = new ArrayList<ProductDto>();
			long count=modelReopsitory.count();
			Pageable pageable = PageRequest.of(0, (int)count);
			List<Model> modelList = modelReopsitory.findBymodelName(modelName, pageable);
			if (modelList.size()==0) {
				logger.error("Invalid modelName");
				throw new InvalidModelException("INVALID_MODEL");
			}
			Model model=modelList.get(0);
			List<Product> productList=model.getProductList();
			Collections.sort(productList);
			for (Product product : productList) {
				productDtoList.add(mapper.map(product, ProductDto.class));
			}
			logger.trace("Getting Products ....");
			return productDtoList;
		} catch (InvalidModelException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}

	@Override
	@Cacheable(value = "model",key="#modelName + #minPrice + #maxPrice",condition = "#modelName!=null && #minPrice!=null && #maxPrice!=null")
	@Transactional(readOnly = true)
	public List<ProductDto> filterByModelPriceService(String modelName, double minPrice, double maxPrice)
			throws ServiceException {
		try {
			logger.warn("Validating modelName and price-range");
			if(minPrice>=maxPrice) {
				logger.error("Invalid price-range");
				throw new InvalidRangeException("NEGATIVE_RANGE");
			}
			List<ProductDto> productDtoList = new ArrayList<ProductDto>();
			long count=modelReopsitory.count();
			Pageable pageable = PageRequest.of(0, (int)count);
			List<Model> modelList = modelReopsitory.findBymodelName(modelName, pageable);
			if (modelList.size()==0) {
				logger.error("Invalid modelName");
				throw new InvalidModelException("INVALID_MODEL");
			}
			Model model=modelList.get(0);
			List<Product> productList=model.getProductList();
			Collections.sort(productList);
			for (Product product : productList) {
				if (product.getProductPrice() > minPrice && product.getProductPrice() < maxPrice)
					productDtoList.add(mapper.map(product, ProductDto.class));
			}
			if (productDtoList.size() == 0) {
				logger.fatal("No products Found");
				throw new NoProductFoundException("INVALID_RANGE");
			}
			logger.trace("Getting Products ....");
			return productDtoList;
		} catch (InvalidModelException | NoProductFoundException|InvalidRangeException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}

}
