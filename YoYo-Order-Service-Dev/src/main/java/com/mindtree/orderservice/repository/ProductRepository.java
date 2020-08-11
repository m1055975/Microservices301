package com.mindtree.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.orderservice.entity.Product;
@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

}
