package com.mindtree.productservice.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "brand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int brandId;
	private String brandName;
	@ManyToOne(cascade = CascadeType.ALL)
	private Category brandCategory;
	@OneToMany(mappedBy="modelBrand",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Model> ModelList;

	public Brand() {
		super();
		// constructor
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Category getBrandCategory() {
		return brandCategory;
	}

	public void setBrandCategory(Category brandCategory) {
		this.brandCategory = brandCategory;
	}

	public List<Model> getModelList() {
		return ModelList;
	}

	public void setModelList(List<Model> modelList) {
		ModelList = modelList;
	}

}
