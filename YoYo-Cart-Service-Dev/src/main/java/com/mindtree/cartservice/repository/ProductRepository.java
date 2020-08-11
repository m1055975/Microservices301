package com.mindtree.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.cartservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	void deleteByUserId(String userId);

	//DELETE FROM `yoyocart`.`cart_product` WHERE `product_id`='125' and`user_id`='akash123';

	@Modifying
	@Query(value = "DELETE FROM cart_product WHERE product_id=?1 AND user_id=?2",nativeQuery = true)
	void deleteByUserIdAndProuctId(int productId,String userId);
}
