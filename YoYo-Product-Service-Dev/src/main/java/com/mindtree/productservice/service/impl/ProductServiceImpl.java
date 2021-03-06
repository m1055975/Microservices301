package com.mindtree.productservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.dto.ProductStatus;
import com.mindtree.productservice.entity.Product;
import com.mindtree.productservice.exception.service.InvalidIdException;
import com.mindtree.productservice.exception.service.InvalidRangeException;
import com.mindtree.productservice.exception.service.NoProductFoundException;
import com.mindtree.productservice.exception.service.ServiceException;
import com.mindtree.productservice.exception.service.SoldException;
import com.mindtree.productservice.repository.ProductRepository;
import com.mindtree.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	private static Logger logger=LogManager.getLogger(ProductServiceImpl.class);

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	private ModelMapper mapper = new ModelMapper();

	@Override
	@Cacheable(value = "product", key = "#productId")
	@Transactional(readOnly = true)
	public ProductDto getProductService(int productId) throws ServiceException {
		try {
			logger.warn("Validating Id..");
			Product product = productRepository.findByproductId(productId);
			if (product == null) {
				logger.error("Invalid Id");
				throw new InvalidIdException("INVALID_PRODUCT");
			}
			if (product.getProductQuantity() == 0) {
				logger.fatal("All products Sold");
				throw new NoProductFoundException("SOLD_OUT");
			}
			product.setProductQuantity(1);
			logger.trace("Getting product..");
			return mapper.map(product, ProductDto.class);
		} catch (NoProductFoundException | InvalidIdException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}

	@Override
	@Cacheable(value = "product", key = "#maxPrice + #minPrice")
	@Transactional(readOnly = true)
	public List<ProductDto> filterByPriceService(double minPrice, double maxPrice) throws ServiceException {
		try {
			logger.warn("validating price-range");
			if (minPrice >= maxPrice) {
				logger.error("Invalid price range");
				throw new InvalidRangeException("NEGATIVE_RANGE");
			}
			long count = productRepository.count();
			Pageable pageable = PageRequest.of(0, (int) count);
			List<Product> productList = productRepository.findByproductPrice(minPrice, maxPrice, pageable);
			List<ProductDto> productDtoList = new ArrayList<ProductDto>();
			for (Product product : productList) {
				productDtoList.add(mapper.map(product, ProductDto.class));
			}
			if (productDtoList.size() == 0) {
				logger.fatal("No products Found");
				throw new NoProductFoundException("INVALID_RANGE");
			}
			logger.trace("Getting products...");
			return productDtoList;
		} catch (NoProductFoundException | InvalidRangeException sx) {
			throw new ServiceException(sx.getMessage());
		}
	}

	@Override
	@Cacheable(value = "product")
	@Transactional(readOnly = true)
	public List<ProductDto> viewProductService() {
		long count = productRepository.count();
		Pageable pageable = PageRequest.of(0, (int) count);
		List<Product> productList = productRepository.findAllproduct(pageable);
		System.out.println(productList.size());
		List<ProductDto> productDtoList = new ArrayList<ProductDto>();
		for (Product product : productList) {
			productDtoList.add(mapper.map(product, ProductDto.class));
		}
		logger.trace("Getting all products ");
		return productDtoList;
	}

	@Override
	@Transactional
	public List<ProductStatus> purchasedProductService(List<ProductDto> productDtoList) throws ServiceException {
		try {
			logger.info("Updating Sold Products");
			List<ProductStatus> soldProduct = new ArrayList<ProductStatus>();
			for (ProductDto productDto : productDtoList) {
				Product product = productRepository
						.findByproductId(mapper.map(productDto, Product.class).getProductId());
				if (productDto.getProductQuantity() > product.getProductQuantity()) {
					logger.error("Quantity not available");
					throw new SoldException("INVALID_QUANTITY");
				}
				product.setProductQuantity(product.getProductQuantity() - productDto.getProductQuantity());
				ProductStatus productStatus = mapper.map(productDto, ProductStatus.class);
				productStatus.setStatus("sold");
				soldProduct.add(productStatus);
			}
			logger.trace("Updated Sold Products");
			return soldProduct;
		} catch (SoldException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<ProductDto> verifyProductService(List<ProductDto> productDtoList) throws ServiceException {
		try {
			logger.warn("Validating each Products");
			List<Product> productList = productDtoList.stream().map(n -> mapper.map(n, Product.class))
					.collect(Collectors.toList());
			productList.stream().forEach(n -> {
				Product product = productRepository.findByproductId(n.getProductId());
				if (product.getProductQuantity() < n.getProductQuantity()) {
					logger.error("Sufficient Quantity not available");
					throw new RuntimeException("INVALID_QUANTITY");
				}
			});
			logger.trace("Preparing for Purchase");
			return productList.stream().map(n->mapper.map(n,ProductDto.class)).collect(Collectors.toList());
		} catch (RuntimeException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
