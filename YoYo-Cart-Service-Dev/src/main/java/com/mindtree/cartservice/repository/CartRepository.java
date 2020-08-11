package com.mindtree.cartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.cartservice.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	
	void deleteByUserId(String userId);
	List<Cart> findAllByUserId(String userId);
	@Query(value = "SELECT * FROM cart WHERE cart_id=?1 and payment_status=?2",nativeQuery = true)
	Cart findByUserId(String userId,boolean active);

}
