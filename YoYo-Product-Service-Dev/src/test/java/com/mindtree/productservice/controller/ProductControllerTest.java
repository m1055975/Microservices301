package com.mindtree.productservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mindtree.productservice.dto.BrandDto;
import com.mindtree.productservice.dto.CategoryDto;
import com.mindtree.productservice.dto.FilterParameterDto;
import com.mindtree.productservice.dto.ModelDto;
import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.dto.ProductStatus;
import com.mindtree.productservice.dto.ResponseDto;
import com.mindtree.productservice.exception.ProductException;
import com.mindtree.productservice.service.ProductService;

class ProductControllerTest {
	@Mock
	ProductService productService;
	
	@InjectMocks
	ProductController productController;
	
	private List<ProductDto> productList;
	private ProductDto productDto;
	private FilterParameterDto parameterDto;
	private ResponseDto responseDto;
	private List<ProductStatus> productStatusList;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		productList=getProductList();
		productStatusList=getProductStatusList();
		parameterDto=getParameter();
		responseDto=new ResponseDto(productList);
		
	}
	
	private FilterParameterDto getParameter() {
		return new FilterParameterDto(100,"categoryName","brandName","modelName",0,200);
		
	}
	
	private ProductDto getProduct(){
		ProductDto productDto=new ProductDto();
		productDto.setProductId(100);
		productDto.setProductDescription("description");
		productDto.setProductPrice(100);
		productDto.setProductQuantity(100);
		ModelDto  modelDto=new ModelDto();
		modelDto.setModelId(100);
		modelDto.setModelName("modelName");
		productDto.setProductModel(modelDto);
		BrandDto brandDto=new BrandDto();
		brandDto.setBrandId(500);
		brandDto.setBrandName("brandName");
		modelDto.setModelBrand(brandDto);
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCategoryId(100);
		categoryDto.setCategoryName("categoryName");
		modelDto.setModelBrand(brandDto);
		return productDto;
	}
	private List<ProductStatus> getProductStatusList(){
		ProductStatus productStatus=new ProductStatus();
		productStatus.setProductId(100);
		productStatus.setProductDescription("description");
		productStatus.setProductPrice(100);
		productStatus.setProductQuantity(100);
		ModelDto  modelDto=new ModelDto();
		modelDto.setModelId(100);
		modelDto.setModelName("modelName");
		productStatus.setProductModel(modelDto);
		BrandDto brandDto=new BrandDto();
		brandDto.setBrandId(100);
		brandDto.setBrandName("brandName");
		modelDto.setModelBrand(brandDto);
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCategoryId(100);
		categoryDto.setCategoryName("categoryName");
		modelDto.setModelBrand(brandDto);
		List<ProductStatus> productStatusList=new ArrayList<ProductStatus>();
		productStatusList.add(productStatus);
		return productStatusList;
	}
	
	private List<ProductDto> getProductList(){
		ProductDto productDto=new ProductDto();
		productDto.setProductId(100);
		productDto.setProductDescription("description");
		productDto.setProductPrice(100);
		productDto.setProductQuantity(100);
		ModelDto  modelDto=new ModelDto();
		modelDto.setModelId(100);
		modelDto.setModelName("ModelName");
		productDto.setProductModel(modelDto);
		BrandDto brandDto=new BrandDto();
		brandDto.setBrandId(100);
		brandDto.setBrandName("brandName");
		modelDto.setModelBrand(brandDto);
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCategoryId(100);
		categoryDto.setCategoryName("categoryname");
		modelDto.setModelBrand(brandDto);
		List<ProductDto> productDtoList=new ArrayList<ProductDto>();
		productDtoList.add(productDto);
		return productDtoList;
	}

	@Test
	void testGetProductController() throws ProductException {
		System.out.println("testGetProductController method ran");
		when(productService.getProductService((int)parameterDto.getProductId())).thenReturn(productDto);
		ResponseEntity<ResponseDto> responseDtoN =productController.getProductController(parameterDto);
		assertEquals(responseDto,responseDtoN.getBody());
	}

	@Test
	void testFilterByPriceController() throws ProductException {
		System.out.println("testFilterByPriceController method ran");
		when(productService.filterByPriceService(parameterDto.getMinPrice(),parameterDto.getMaxPrice())).thenReturn(productList);
		ResponseEntity<ResponseDto> responseDtoN =productController.filterByPriceController(parameterDto);
		assertEquals(responseDto,responseDtoN.getBody());
	}

	@Test
	void testViewProductController() {
		System.out.println("testViewProductController method ran");
		when(productService.viewProductService()).thenReturn(productList);
		ResponseEntity<ResponseDto> responseDtoN =productController.viewProductController();
		responseDto=new ResponseDto("ALL_PRODUCTS",productList);
		assertEquals(responseDto,responseDtoN.getBody());
		
	}

// 	@Test
// 	void testPurchasedProductController() throws ProductException {
// 		System.out.println("testPurchasedProductController method ran");
// 		when(productService.purchasedProductService(productList)).thenReturn(productStatusList);
// 		ResponseEntity<ResponseDto> responseDtoN =productController.viewProductController();
// 		responseDto=new ResponseDto("PURCHASED",productStatusList);
// 		assertEquals(responseDto,responseDtoN.getBody());
// 	}
	
}
