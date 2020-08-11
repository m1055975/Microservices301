package com.mindtree.productservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.productservice.entity.Category;
@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {
 public List<Category> findBycategoryName(String categoryName, Pageable pageable);

}
