package com.mindtree.productservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.productservice.entity.Model;
@Repository
public interface ModelRepository extends PagingAndSortingRepository<Model, Integer>{

	public List<Model> findBymodelName(String modelName, Pageable pageable);

}
