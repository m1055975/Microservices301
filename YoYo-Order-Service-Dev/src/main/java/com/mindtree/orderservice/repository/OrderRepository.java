package com.mindtree.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.orderservice.entity.Order;
@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

	Order findByredeemCode(String redeemCode);

}
