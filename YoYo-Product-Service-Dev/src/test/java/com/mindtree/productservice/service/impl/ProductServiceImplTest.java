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
import com.mindtree.productservice.dto.ProductStatus;
import com.mindtree.productservice.exception.service.ServiceException;
import com.mindtree.productservice.repository.ProductRepository;

class ProductServiceImplTest {
	@Mock
	ProductRepository productRepository;
	
// 	@InjectMocks
// 	ProductServiceImpl productServiceImpl;
	
// 	List<ProductDto> productDtoList;
// 	List<ProductStatus> productStatusList;
// 	ProductDto productDto;
	
// 	@BeforeEach
// 	void setUp() throws Exception{
// 		MockitoAnnotations.initMocks(this);
// 		productDtoList=getProductList();
// 		productStatusList=getProductStatusList();
// 		productDto=getProduct();
		
		
// 	}
// 	private ProductDto getProduct(){
// 		ProductDto productDto=new ProductDto();
// 		productDto.setProductId(2001);
// 		productDto.setProductDescription("product Description");
// 		productDto.setProductPrice(30000);
// 		productDto.setProductQuantity(23);
// 		ModelDto  modelDto=new ModelDto();
// 		modelDto.setModelId(33113);
// 		modelDto.setModelName("product model");
// 		productDto.setProductModel(modelDto);
// 		BrandDto brandDto=new BrandDto();
// 		brandDto.setBrandId(500);
// 		brandDto.setBrandName("product name");
// 		modelDto.setModelBrand(brandDto);
// 		CategoryDto categoryDto=new CategoryDto();
// 		categoryDto.setCategoryId(101);
// 		categoryDto.setCategoryName("productCategory");
// 		modelDto.setModelBrand(brandDto);
// 		return productDto;
// 	}
// 	private List<ProductDto> getProductList(){
// 		ProductDto productDto=new ProductDto();
// 		productDto.setProductId(2503);
// 		productDto.setProductDescription("product Description");
// 		productDto.setProductPrice(30000);
// 		productDto.setProductQuantity(23);
// 		ModelDto  modelDto=new ModelDto();
// 		modelDto.setModelId(33113);
// 		modelDto.setModelName("product model");
// 		productDto.setProductModel(modelDto);
// 		BrandDto brandDto=new BrandDto();
// 		brandDto.setBrandId(500);
// 		brandDto.setBrandName("product name");
// 		modelDto.setModelBrand(brandDto);
// 		CategoryDto categoryDto=new CategoryDto();
// 		categoryDto.setCategoryId(101);
// 		categoryDto.setCategoryName("productCategory");
// 		modelDto.setModelBrand(brandDto);
// 		ProductDto productDto1=new ProductDto();
// 		productDto1.setProductId(2993);
// 		productDto1.setProductDescription("product Description1");
// 		productDto1.setProductPrice(50000);
// 		productDto1.setProductQuantity(27);
// 		ModelDto  modelDto1=new ModelDto();
// 		modelDto1.setModelId(33113);
// 		modelDto1.setModelName("product model1");
// 		productDto1.setProductModel(modelDto1);
// 		BrandDto brandDto1=new BrandDto();
// 		brandDto1.setBrandId(501);
// 		brandDto1.setBrandName("product name1");
// 		modelDto1.setModelBrand(brandDto1);
// 		CategoryDto categoryDto1=new CategoryDto();
// 		categoryDto1.setCategoryId(102);
// 		categoryDto1.setCategoryName("productCategory1");
// 		modelDto1.setModelBrand(brandDto1);
// 		List<ProductDto> productDtoList=new ArrayList<ProductDto>();
// 		productDtoList.add(productDto);
// 		productDtoList.add(productDto1);
// 		return productDtoList;
// 	}
// 	private List<ProductStatus> getProductStatusList(){
// 		ProductStatus productStatus=new ProductStatus();
// 		productStatus.setProductId(2503);
// 		productStatus.setProductDescription("product Description");
// 		productStatus.setProductPrice(30000);
// 		productStatus.setProductQuantity(23);
// 		productStatus.setStatus("sold");
// 		ModelDto  modelDto=new ModelDto();
// 		modelDto.setModelId(33113);
// 		modelDto.setModelName("product model");
// 		productStatus.setProductModel(modelDto);
// 		BrandDto brandDto=new BrandDto();
// 		brandDto.setBrandId(500);
// 		brandDto.setBrandName("product name");
// 		modelDto.setModelBrand(brandDto);
// 		CategoryDto categoryDto=new CategoryDto();
// 		categoryDto.setCategoryId(101);
// 		categoryDto.setCategoryName("productCategory");
// 		modelDto.setModelBrand(brandDto);
// 		ProductStatus productStatus1=new ProductStatus();
// 		productStatus1.setProductId(2993);
// 		productStatus1.setProductDescription("product Description1");
// 		productStatus1.setProductPrice(50000);
// 		productStatus1.setProductQuantity(27);
// 		ModelDto  modelDto1=new ModelDto();
// 		modelDto1.setModelId(33113);
// 		modelDto1.setModelName("product model1");
// 		productStatus1.setProductModel(modelDto1);
// 		BrandDto brandDto1=new BrandDto();
// 		brandDto1.setBrandId(501);
// 		brandDto1.setBrandName("product name1");
// 		modelDto1.setModelBrand(brandDto1);
// 		CategoryDto categoryDto1=new CategoryDto();
// 		categoryDto1.setCategoryId(102);
// 		categoryDto1.setCategoryName("productCategory1");
// 		modelDto1.setModelBrand(brandDto1);
// 		List<ProductStatus> productStatusList=new ArrayList<ProductStatus>();
// 		productStatusList.add(productStatus);
// 		productStatusList.add(productStatus1);
// 		return productStatusList;
// 	}

// 	@Test
// 	void testGetProductService() throws ServiceException {
// 		System.out.println("testFilterByBrandController method ran");
// 		when(productServiceImpl.getProductService(2001)).thenReturn(productDto);
// 		ProductDto productDtoTest = productServiceImpl.getProductService(2001);
// 		assertEquals(productDtoTest.getProductId(),productDto.getProductId());
// 	}

// 	@Test
// 	void testFilterByPriceService() throws ServiceException {
// 		System.out.println("testFilterByBrandController method ran");
// 		when(productServiceImpl.filterByPriceService(2000,60000)).thenReturn(productDtoList);
// 		List<ProductDto> productDtoListTest = productServiceImpl.filterByPriceService(2000,6000);
// 		assertEquals(productDtoListTest.size(),productDtoList.size());
// 	}

// 	@Test
// 	void testViewProductService() {
// 		System.out.println("testFilterByBrandController method ran");
// 		when(productServiceImpl.viewProductService()).thenReturn(productDtoList);
// 		List<ProductDto> productDtoListTest = productServiceImpl.viewProductService();
// 		assertEquals(productDtoListTest.size(),productDtoList.size());
// 	}

// 	@Test
// 	void testPurchasedProductService() throws ServiceException {
// 		System.out.println("testFilterByBrandController method ran");
// 		when(productServiceImpl.purchasedProductService(productDtoList)).thenReturn(productStatusList);
// 		List<ProductStatus> productStatusListTest = productServiceImpl.purchasedProductService(productDtoList);
// 		assertEquals(productStatusListTest.size(),productDtoList.size());
// 	}
// 	@Test
// 	void testVerifyProductService() throws ServiceException {
// 		System.out.println("testFilterByBrandController method ran");
// 		when(productServiceImpl.verifyProductService(productDtoList)).thenReturn(productDtoList);
// 		List<ProductDto> productListTest = productServiceImpl.verifyProductService(productDtoList);
// 		assertEquals(productListTest.size(),productDtoList.size());
// 	}

}
