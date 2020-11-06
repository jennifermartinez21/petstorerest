package com.upemor.petstorerest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upemor.petstorerest.model.CategoryDTO;
import com.upemor.petstorerest.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryDTO> listAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public CategoryDTO findById(int id) {
		return categoryRepository.findById(id);
	}

	@Override
	public boolean createCategory(CategoryDTO category) {
		if (categoryRepository.findByname(category.getName()) != null){
			return false;
			}
		categoryRepository.saveAndFlush(category);
		return true;
	}

	@Override
	public CategoryDTO updateCategory(int id, CategoryDTO category) {
		CategoryDTO currentCategory = categoryRepository.findById(id);
		currentCategory.setName(category.getName());
		categoryRepository.saveAndFlush(currentCategory);
		return currentCategory;
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}

}
