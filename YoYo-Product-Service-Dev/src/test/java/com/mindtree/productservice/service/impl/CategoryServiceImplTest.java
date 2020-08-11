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
import com.mindtree.productservice.repository.CategoryRepository;

class CategoryServiceImplTest {
	@Mock
	CategoryRepository categoryRepository;

	@Mock
	ModelMapper modelMapper;

	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;

	List<ProductDto> productDtoList;
	List<Product> productList;
	Product product;
	ProductDto productDto;
	List<Category> categoryList = new ArrayList<Category>();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		productDtoList = getProductDtoList();
		productList = getProductList();
		product = getProduct();
		productDto = getProductDto();
		categoryList.add(getCategory1());
	}

	private Category getCategory1() {
		Brand brand = new Brand();
		brand.setBrandId(100);
		brand.setBrandName("brandName");
		Model model = new Model();
		model.setModelId(100);
		model.setModelName("modelName");
		model.setModelBrand(brand);
		List<Model> modelList = new ArrayList<Model>();
		modelList.add(model);
		brand.setModelList(modelList);
		Product product = new Product();
		product.setProductId(100);
		product.setProductDescription("description");
		product.setProductPrice(100);
		product.setProductQuantity(100);
		product.setProductModel(model);
		List<Product> ProductList = new ArrayList<Product>();
		ProductList.add(product);
		model.setProductList(ProductList);
		Category category = new Category();
		category.setCategoryId(100);
		category.setCategoryName("categoryName");
		List<Brand> brandList = new ArrayList<Brand>();
		brandList.add(brand);
		category.setBrandList(brandList);
		return category;
	}

	private ProductDto getProductDto() {
		ProductDto productDto = new ProductDto();
		productDto.setProductId(100);
		productDto.setProductDescription("description");
		productDto.setProductPrice(100);
		productDto.setProductQuantity(100);
		ModelDto modelDto = new ModelDto();
		modelDto.setModelId(100);
		modelDto.setModelName("modelName");
		productDto.setProductModel(modelDto);
		BrandDto brandDto = new BrandDto();
		brandDto.setBrandId(100);
		brandDto.setBrandName("brandName");
		modelDto.setModelBrand(brandDto);
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryId(100);
		categoryDto.setCategoryName("categoryName");
		modelDto.setModelBrand(brandDto);
		return productDto;
	}

	private Product getProduct() {
		Product product = new Product();
		product.setProductId(100);
		product.setProductDescription("description");
		product.setProductPrice(100);
		product.setProductQuantity(100);
		Model model = new Model();
		model.setModelId(100);
		model.setModelName("modelName");
		product.setProductModel(model);
		Brand brand = new Brand();
		brand.setBrandId(100);
		brand.setBrandName("brandName");
		model.setModelBrand(brand);
		Category category = new Category();
		category.setCategoryId(100);
		category.setCategoryName("categoryName");
		model.setModelBrand(brand);
		return product;
	}

	private List<ProductDto> getProductDtoList() {
		ProductDto productDto = new ProductDto();
		productDto.setProductId(100);
		productDto.setProductDescription("description");
		productDto.setProductPrice(100);
		productDto.setProductQuantity(100);
		ModelDto modelDto = new ModelDto();
		modelDto.setModelId(100);
		modelDto.setModelName("modelname");
		productDto.setProductModel(modelDto);
		BrandDto brandDto = new BrandDto();
		brandDto.setBrandId(100);
		brandDto.setBrandName("brandName");
		modelDto.setModelBrand(brandDto);
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryId(100);
		categoryDto.setCategoryName("categoryname");
		modelDto.setModelBrand(brandDto);
		List<ProductDto> productDtoList = new ArrayList<ProductDto>();
		productDtoList.add(productDto);
		return productDtoList;
	}

	private List<Product> getProductList() {
		Product product = new Product();
		product.setProductId(100);
		product.setProductDescription("description");
		product.setProductPrice(100);
		product.setProductQuantity(100);
		Model model = new Model();
		model.setModelId(100);
		model.setModelName("modelName");
		product.setProductModel(model);
		Brand brand = new Brand();
		brand.setBrandId(100);
		brand.setBrandName("brandName");
		model.setModelBrand(brand);
		Category category = new Category();
		category.setCategoryId(100);
		category.setCategoryName("categoryName");
		model.setModelBrand(brand);
		Product product1 = new Product();
		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		return productList;
	}


	@Test
	void testFilterByCategoryService() throws ServiceException {
		System.out.println("testFilterByCategoryService method ran");
		when(categoryRepository.findBycategoryName("categoryName",PageRequest.of(0, 40))).thenReturn(categoryList);
		when(modelMapper.map(getProduct(), ProductDto.class)).thenReturn(productDto);
		when(categoryRepository.count()).thenReturn(40l);
		List<ProductDto> productDtoListTest = categoryServiceImpl.filterByCategoryService("categoryName");
		assertEquals(productDtoListTest.size(),productDtoList.size());
	}

	@Test
	void testFilterByCategoryPriceService() throws ServiceException {
		System.out.println("testFilterByCategoryPriceService method ran");
		when(categoryRepository.findBycategoryName("categoryName",PageRequest.of(0, 40))).thenReturn(categoryList);
		when(modelMapper.map(getProduct(), ProductDto.class)).thenReturn(productDto);
		when(categoryRepository.count()).thenReturn(40l);
		List<ProductDto> productDtoListTest = categoryServiceImpl.filterByCategoryPriceService("categoryName",0,200);
		assertEquals(productDtoListTest.size(),productDtoList.size());
	}

	@Test
	void testFilterByCategoryBrandPriceService() throws ServiceException {
		System.out.println("testFilterByCategoryBrandPriceService method ran");
		when(categoryRepository.findBycategoryName("categoryName",PageRequest.of(0, 40))).thenReturn(categoryList);
		when(modelMapper.map(getProduct(), ProductDto.class)).thenReturn(productDto);
		when(categoryRepository.count()).thenReturn(40l);
		List<ProductDto> productDtoListTest = categoryServiceImpl.filterByCategoryBrandPriceService("categoryName","brandName",0,200);
		assertEquals(productDtoListTest.size(),productDtoList.size());
	}

	@Test
	void testFilterByCategoryBrandService() throws ServiceException {
		System.out.println("testFilterByCategoryBrandService method ran");
		when(categoryRepository.findBycategoryName("categoryName",PageRequest.of(0,40 ))).thenReturn(categoryList);
		when(modelMapper.map(getProduct(), ProductDto.class)).thenReturn(productDto);
		when(categoryRepository.count()).thenReturn(40l);
		List<ProductDto> productDtoListTest = categoryServiceImpl.filterByCategoryBrandService("categoryName","brandName");
		assertEquals(productDtoListTest.size(),productDtoList.size());
	}

}
