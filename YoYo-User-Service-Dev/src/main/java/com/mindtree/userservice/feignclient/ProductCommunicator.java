package com.mindtree.userservice.feignclient;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindtree.userservice.model.ProductModel;
import com.mindtree.userservice.model.ProductStatus;

@FeignClient(name="productservice",url="http://localhost:9001/filter")
public interface ProductCommunicator { 
	
	/* static NoSuchUserFound connect() {
		 return Feign.builder()
				                 .errorDecoder(new MyErrorDecoder())
				                 .target(NoSuchUserFound.class, "http://localhost:9001/filter"); 
	 }
		 
*/
	
	@GetMapping("/brand")
	ResponseEntity<Map<String,List<ProductModel>>> filterByBrandController(@RequestParam("brandName") String brandName);
	
	
	//ResponseEntity<Object> 
	
	@GetMapping("/brand/model")
	ResponseEntity<Map<String,List<ProductModel>>> filterByBrandModelController(@RequestParam("brandName") String brandName, @RequestParam("modelName") String modelName);

	@GetMapping("/brand/model/price")
	ResponseEntity<Map<String,List<ProductModel>>> filterByBrandModelPriceController(@RequestParam("brandName") String brandName, @RequestParam("modelName") String modelName,
			@RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice);
	
	@GetMapping("/brand/price") 
	ResponseEntity<Map<String,List<ProductModel>>> filterByBrandPriceController(@RequestParam("brandName") String brandName, @RequestParam("minPrice") double minPrice,
			@RequestParam("maxPrice") double maxPrice);
	
	@GetMapping("/category")
	ResponseEntity<Map<String,List<ProductModel>>> filterByCategoryController(@RequestParam("categoryName") String categoryName);
	
	@GetMapping("/category/price")
	ResponseEntity<Map<String,List<ProductModel>>> filterByCategoryPriceController(@RequestParam("categoryName") String categoryName, @RequestParam("minPrice") double minPrice,
			@RequestParam("maxPrice") double maxPrice);
	

	@GetMapping("/category/brand")
	ResponseEntity<Map<String,List<ProductModel>>> filterByCategoryBrandController(@RequestParam("categoryName") String categoryName, @RequestParam("brandName") String brandName);

	@GetMapping("/category/brand/price")
	ResponseEntity<Map<String,List<ProductModel>>> filterByCategoryBrandPriceController(@RequestParam("categoryName") String categoryName,
			@RequestParam("brandName") String brandName, @RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice);

	@GetMapping("/model")
	ResponseEntity<Map<String,List<ProductModel>>> filterByModelController(@RequestParam("modelName") String modelName);
	
	@GetMapping("/model/price")
	ResponseEntity<Map<String,List<ProductModel>>> filterByModelPriceController(@RequestParam("modelName") String modelName, @RequestParam("minPrice") double minPrice,
			@RequestParam("maxPrice") double maxPrice);
	
	@GetMapping("/getProduct")
	public ResponseEntity<Map<String,ProductModel>> getProductController(@RequestParam("productId") double productId);
	
	@GetMapping("/price")
	public ResponseEntity<Map<String,List<ProductModel>>> filterByPriceController(@RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice);

	@GetMapping("/view")
	public ResponseEntity<Map<String,List<ProductModel>>> viewProductController();
	
	@PostMapping("/purchased") 
	public ResponseEntity<Map<String,List<ProductStatus>>> purchasedProductController(@RequestBody List<ProductModel> productDtoList);
	
}
