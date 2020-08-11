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
import com.mindtree.productservice.service.ModelService;

class ModelControllerTest {
	@Mock
	ModelService modelService;
	@InjectMocks
	ModelController modelController;
	
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
	void testFilterByModelController() throws ProductException {
		System.out.println("testFilterByModelController method ran");
		when(modelService.filterByModelService(parameterDto.getModelName())).thenReturn(productList);
		ResponseEntity<ResponseDto> responseDtoN =modelController.filterByModelController(parameterDto);
		assertEquals(responseDto,responseDtoN.getBody());
	}

	@Test
	void testFilterByModelPriceController() throws ProductException {
		System.out.println("testFilterByModelPriceController method ran");
		when(modelService.filterByModelPriceService(parameterDto.getModelName(),parameterDto.getMinPrice(),parameterDto.getMaxPrice())).thenReturn(productList);
		ResponseEntity<ResponseDto> responseDtoN =modelController.filterByModelPriceController(parameterDto);
		assertEquals(responseDto,responseDtoN.getBody());
	}


}
