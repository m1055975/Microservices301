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
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;

import com.mindtree.productservice.dto.BrandDto;
import com.mindtree.productservice.dto.CategoryDto;
import com.mindtree.productservice.dto.ModelDto;
import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.entity.Brand;
import com.mindtree.productservice.entity.Category;
import com.mindtree.productservice.entity.Model;
import com.mindtree.productservice.entity.Product;
import com.mindtree.productservice.exception.service.ServiceException;
import com.mindtree.productservice.repository.BrandRepository;

class BrandServiceImplTest {
	@Mock
	BrandRepository brandRepository;
	
	@Mock
	ModelMapper modelMapper;
	
	@InjectMocks
	BrandServiceImpl brandServiceImpl;
	
	List<ProductDto> productDtoList;
	List<Product> productList;
	Product product;
	ProductDto productDto;
	List<Brand> brandList=new ArrayList<Brand>();
	Brand brand;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		productDtoList=getProductDtoList();
		productList=getProductList();
		product=getProduct();
		productDto=getProductDto();
		brandList.add(getBrand1());
	}
	
	private Brand getBrand1() {
		Brand brand=new Brand();
		brand.setBrandId(100);
		brand.setBrandName("brandName");
		Model model=new Model();
		model.setModelId(100);
		model.setModelName("modelName");
		model.setModelBrand(brand);
		List<Model> modelList=new ArrayList<Model>();
		modelList.add(model);
		brand.setModelList(modelList);
		Product product=new Product();
		product.setProductId(100);
		product.setProductDescription("product Description");
		product.setProductPrice(100);
		product.setProductQuantity(100);
		product.setProductModel(model);
		List<Product> ProductList=new ArrayList<Product>();
		ProductList.add(product);
		model.setProductList(ProductList);
		return brand;
	}
	private ProductDto getProductDto() {
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
		brandDto.setBrandId(100);
		brandDto.setBrandName("brandName");
		modelDto.setModelBrand(brandDto);
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCategoryId(101);
		categoryDto.setCategoryName("categoryName");
		modelDto.setModelBrand(brandDto);
		return productDto;
	}
	
	private Product getProduct() {
		Product product=new Product();
		product.setProductId(100);
		product.setProductDescription("description");
		product.setProductPrice(100);
		product.setProductQuantity(100);
		Model model=new Model();
		model.setModelId(100);
		model.setModelName("modelname");
		product.setProductModel(model);
		Brand brand=new Brand();
		brand.setBrandId(100);
		brand.setBrandName("brandName");
		model.setModelBrand(brand);
		Category category=new Category();
		category.setCategoryId(100);
		category.setCategoryName("categoryName");
		model.setModelBrand(brand);
		return product;
	}
	
	private List<ProductDto> getProductDtoList(){
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
		brandDto.setBrandId(100);
		brandDto.setBrandName("brandName");
		modelDto.setModelBrand(brandDto);
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCategoryId(101);
		categoryDto.setCategoryName("categoryname");
		modelDto.setModelBrand(brandDto);
		List<ProductDto> productDtoList=new ArrayList<ProductDto>();
		productDtoList.add(productDto);
		return productDtoList;
	} 
	private List<Product> getProductList(){
		Product product=new Product();
		product.setProductId(100);
		product.setProductDescription("description");
		product.setProductPrice(100);
		product.setProductQuantity(100);
		Model model=new Model();
		model.setModelId(100);
		model.setModelName("modelName");
		product.setProductModel(model);
		Brand brand=new Brand();
		brand.setBrandId(100);
		brand.setBrandName("brandName");
		model.setModelBrand(brand);
		Category category=new Category();
		category.setCategoryId(100);
		category.setCategoryName("categoryName");
		model.setModelBrand(brand);
		List<Product> productList=new ArrayList<Product>();
		productList.add(product);
		return productList;
	} 

	@Test
	void testFilterByBrandService() throws ServiceException {
		System.out.println("testFilterByBrandService method ran");
		when(brandRepository.findBybrandName("brandName",PageRequest.of(0, 40))).thenReturn(brandList);
		when(modelMapper.map(getProduct(), ProductDto.class)).thenReturn(productDto);
		List<ProductDto> productDtoListTest = brandServiceImpl.filterByBrandService("brandName");
		assertEquals(productDtoList.size(),productDtoListTest.size());
	}

	@Test
	void testFilterByBrandModelService() throws ServiceException {
		System.out.println("testFilterByBrandModelService method ran");
		when(brandRepository.findBybrandName("brandName",PageRequest.of(0, 20))).thenReturn(brandList);
		when(modelMapper.map(getProduct(), ProductDto.class)).thenReturn(productDto);
		List<ProductDto> productDtoListTest = brandServiceImpl.filterByBrandModelService("brandName","modelName");
		assertEquals(productDtoList.size(),productDtoListTest.size());
	}

	@Test
	void testFilterByBrandModelPriceService() throws ServiceException {
		System.out.println("testFilterBrandModelPriceService method ran");
		when(brandRepository.findBybrandName("brandName",PageRequest.of(0, 20))).thenReturn(brandList);
		when(modelMapper.map(getProduct(), ProductDto.class)).thenReturn(productDto);
		List<ProductDto> productDtoListTest = brandServiceImpl.filterByBrandModelPriceService("brandName","modelName",0,200);
		assertEquals(productDtoList.size(),productDtoListTest.size());
	}

	@Test
	void testFilterByBrandPriceService() throws ServiceException {
		System.out.println("testFilterByBrandPriceService method ran");
		when(brandRepository.findBybrandName("brandName",PageRequest.of(0, 20))).thenReturn(brandList);
		when(modelMapper.map(getProduct(), ProductDto.class)).thenReturn(productDto);
		List<ProductDto> productDtoListTest = brandServiceImpl.filterByBrandPriceService("brandName",0,200);
		assertEquals(productDtoListTest.size(),productDtoList.size());
	}

}
