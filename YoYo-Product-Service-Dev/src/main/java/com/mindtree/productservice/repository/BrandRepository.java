package com.mindtree.productservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.productservice.entity.Brand;
@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer> {

	public List<Brand> findBybrandName(String brandName, Pageable pageable);

	public boolean existsBybrandName(String brandName);

}
