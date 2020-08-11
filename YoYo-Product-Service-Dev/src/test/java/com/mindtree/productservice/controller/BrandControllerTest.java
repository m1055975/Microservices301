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
import com.mindtree.productservice.dto.ResponseDto;
import com.mindtree.productservice.exception.ProductException;
import com.mindtree.productservice.service.BrandService;

class BrandControllerTest {
	@Mock
	BrandService brandService;
	
	@InjectMocks
	BrandController brandController;
	
	private List<ProductDto> productList;
	
	private FilterParameterDto parameterDto;
	private ResponseDto responseDto;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		productList=getProductList();
		parameterDto=getParameter();
		responseDto=getResponseDto();
	}
	
	private FilterParameterDto getParameter() {
		return new FilterParameterDto(100,"categoryName","brandName","modelname",0,200);
		
	}
	private ResponseDto getResponseDto() {
		return new ResponseDto(getProductList());
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
	void testFilterByBrandController() throws ProductException {
		System.out.println("testFilterByBrandController method ran");
		when(brandService.filterByBrandService(parameterDto.getBrandName())).thenReturn(productList);
		ResponseEntity<ResponseDto> responseDtoN =brandController.filterByBrandController(parameterDto);
		assertEquals(responseDto,responseDtoN.getBody());
	}

	@Test
	void testFilterByBrandModelController() throws ProductException {
		when(brandService.filterByBrandModelService(parameterDto.getBrandName(),parameterDto.getModelName())).thenReturn(productList);
		ResponseEntity<ResponseDto> responseDtoN =brandController.filterByBrandModelController(parameterDto);
		assertEquals(responseDto,responseDtoN.getBody());
	}

	@Test
	void testFilterByBrandModelPriceController() throws ProductException {
		System.out.println("testFilterByBrandModelPriceController method ran");
		when(brandService.filterByBrandModelPriceService(parameterDto.getBrandName(),parameterDto.getModelName(),parameterDto.getMinPrice(),parameterDto.getMaxPrice())).thenReturn(productList);
		ResponseEntity<ResponseDto> responseDtoN =brandController.filterByBrandModelPriceController(parameterDto);
		assertEquals(responseDto,responseDtoN.getBody());
	}

	@Test
	void testFilterByBrandPriceController() throws ProductException {
		System.out.println("testFilterByBrandPriceController method ran");
		when(brandService.filterByBrandPriceService(parameterDto.getBrandName(),parameterDto.getMinPrice(),parameterDto.getMaxPrice())).thenReturn(productList);
		ResponseEntity<ResponseDto> responseDtoN =brandController.filterByBrandPriceController(parameterDto);
		assertEquals(responseDto,responseDtoN.getBody());
	}

}
