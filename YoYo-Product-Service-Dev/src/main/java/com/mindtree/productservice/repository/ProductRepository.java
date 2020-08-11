package com.mindtree.productservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query(value="select * from product p where p.product_price>?1 and p.product_price<?2 order by p.product_price",nativeQuery = true)
	public List<Product> findByproductPrice(double minPrice,double maxPrice, Pageable Pageable);

	public Product findByproductId(int i);

	public List<ProductDto> findByproductId(int productId, Pageable pageable);
	
	//@Query(value="select p.* from product p order by product_id limit 3500",nativeQuery = true)
	//public List<Product> findAllproduct();
	
	@Query(value="select p.* from product p order by product_id",nativeQuery = true)
	public List<Product> findAllproduct(Pageable pageable);


}
