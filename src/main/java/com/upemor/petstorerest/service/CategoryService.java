package com.upemor.petstorerest.service;

import java.util.List;

import com.upemor.petstorerest.model.CategoryDTO;

public interface CategoryService {
	
	List<CategoryDTO> listAllCategories();
	
	CategoryDTO findById(int id);
	
	boolean createCategory(CategoryDTO category);
	
	CategoryDTO updateCategory(int id, CategoryDTO category);
	
	void deleteCategory(int id);

}
