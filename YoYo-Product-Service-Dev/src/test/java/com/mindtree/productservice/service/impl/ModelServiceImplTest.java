package com.mindtree.productservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindtree.productservice.dto.BrandDto;
import com.mindtree.productservice.dto.CategoryDto;
import com.mindtree.productservice.dto.ModelDto;
import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.dto.ResponseDto;
import com.mindtree.productservice.entity.Brand;
import com.mindtree.productservice.entity.Category;
import com.mindtree.productservice.entity.Model;
import com.mindtree.productservice.entity.Product;
import com.mindtree.productservice.exception.service.ServiceException;
import com.mindtree.productservice.repository.ModelRepository;

class ModelServiceImplTest {
// 	@Mock
// 	ModelRepository modelRepository;
	
// 	@InjectMocks
// 	ModelServiceImpl modelServiceImpl;
	
// 	List<ProductDto> productDtoList;
// 	List<Product> productList;
// 	Product product;
// 	ProductDto productDto;
// 	List<Model> modelList = new ArrayList<Model>();

// 	@BeforeEach
// 	void setUp() throws Exception {
// 		MockitoAnnotations.initMocks(this);
// 		productDtoList = getProductDtoList();
// 		productList = getProductList();
// 		product = getProduct();
// 		productDto = getProductDto();
// 		modelList.add(getModel1());
// 	}

// 	private Model getModel1() {
// 		Model model = new Model();
// 		model.setModelId(100);
// 		model.setModelName("modelName");
// 		List<Product> productList = new ArrayList<Product>();
// 		Product product = new Product();
// 		product.setProductId(100);
// 		product.setProductDescription("description");
// 		product.setProductPrice(100);
// 		product.setProductQuantity(100);
// 		product.setProductModel(model);
// 		productList.add(product);
// 		return model;
// 	}

// 	private ProductDto getProductDto() {
// 		ProductDto productDto = new ProductDto();
// 		productDto.setProductId(100);
// 		productDto.setProductDescription("description");
// 		productDto.setProductPrice(100);
// 		productDto.setProductQuantity(100);
// 		ModelDto modelDto = new ModelDto();
// 		modelDto.setModelId(100);
// 		modelDto.setModelName("modelName");
// 		productDto.setProductModel(modelDto);
// 		BrandDto brandDto = new BrandDto();
// 		brandDto.setBrandId(100);
// 		brandDto.setBrandName("brandName");
// 		modelDto.setModelBrand(brandDto);
// 		CategoryDto categoryDto = new CategoryDto();
// 		categoryDto.setCategoryId(100);
// 		categoryDto.setCategoryName("categoryName");
// 		modelDto.setModelBrand(brandDto);
// 		return productDto;
// 	}

// 	private Product getProduct() {
// 		Product product = new Product();
// 		product.setProductId(100);
// 		product.setProductDescription("description");
// 		product.setProductPrice(100);
// 		product.setProductQuantity(100);
// 		Model model = new Model();
// 		model.setModelId(100);
// 		model.setModelName("modelName");
// 		product.setProductModel(model);
// 		Brand brand = new Brand();
// 		brand.setBrandId(100);
// 		brand.setBrandName("brandName");
// 		model.setModelBrand(brand);
// 		Category category = new Category();
// 		category.setCategoryId(100);
// 		category.setCategoryName("categoryName");
// 		model.setModelBrand(brand);
// 		return product;
// 	}

// 	private List<ProductDto> getProductDtoList() {
// 		ProductDto productDto = new ProductDto();
// 		productDto.setProductId(100);
// 		productDto.setProductDescription("description");
// 		productDto.setProductPrice(100);
// 		productDto.setProductQuantity(100);
// 		ModelDto modelDto = new ModelDto();
// 		modelDto.setModelId(100);
// 		modelDto.setModelName("modelname");
// 		productDto.setProductModel(modelDto);
// 		BrandDto brandDto = new BrandDto();
// 		brandDto.setBrandId(100);
// 		brandDto.setBrandName("brandName");
// 		modelDto.setModelBrand(brandDto);
// 		CategoryDto categoryDto = new CategoryDto();
// 		categoryDto.setCategoryId(100);
// 		categoryDto.setCategoryName("categoryname");
// 		modelDto.setModelBrand(brandDto);
// 		List<ProductDto> productDtoList = new ArrayList<ProductDto>();
// 		productDtoList.add(productDto);
// 		return productDtoList;
// 	}

// 	private List<Product> getProductList() {
// 		Product product = new Product();
// 		product.setProductId(100);
// 		product.setProductDescription("description");
// 		product.setProductPrice(100);
// 		product.setProductQuantity(100);
// 		Model model = new Model();
// 		model.setModelId(100);
// 		model.setModelName("modelName");
// 		product.setProductModel(model);
// 		Brand brand = new Brand();
// 		brand.setBrandId(100);
// 		brand.setBrandName("brandName");
// 		model.setModelBrand(brand);
// 		Category category = new Category();
// 		category.setCategoryId(100);
// 		category.setCategoryName("categoryName");
// 		model.setModelBrand(brand);
// 		Product product1 = new Product();
// 		List<Product> productList = new ArrayList<Product>();
// 		productList.add(product);
// 		return productList;
// 	}

// 	@Test
// 	void testFilterByModelService() throws ServiceException {
// 		System.out.println("testFilterByBrandController method ran");
// 		when(modelServiceImpl.filterByModelService("productBrand")).thenReturn(productDtoList);
// 		List<ProductDto> productDtoListTest = modelServiceImpl.filterByModelService("productBrand");
// 		assertEquals(productDtoListTest.size(),productDtoList.size());
// 	}

// 	@Test
// 	void testFilterByModelPriceService() throws ServiceException {
// 		System.out.println("testFilterByBrandController method ran");
// 		when(modelServiceImpl.filterByModelPriceService("productModel",2000,60000)).thenReturn(productDtoList);
// 		List<ProductDto> productDtoListTest = modelServiceImpl.filterByModelPriceService("productBrand",2000,60000);
// 		assertEquals(productDtoListTest.size(),productDtoList.size());
// 	}

}
