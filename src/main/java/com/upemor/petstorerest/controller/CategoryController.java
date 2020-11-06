package com.upemor.petstorerest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upemor.petstorerest.exception.CategoryErrorException;
import com.upemor.petstorerest.model.CategoryDTO;
import com.upemor.petstorerest.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> listAllCategories() {
		List<CategoryDTO> categories = categoryService.listAllCategories();
		if (categories.isEmpty()) {
			return new ResponseEntity<List<CategoryDTO>>(HttpStatus.NO_CONTENT);
			}
		return new ResponseEntity<List<CategoryDTO>>(categories, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Integer id) {
		CategoryDTO category = categoryService.findById(id);
		if (category == null) {
		return new ResponseEntity<CategoryDTO>(
		new CategoryErrorException("Category with id "
		+ id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody final CategoryDTO category) {
		if(!categoryService.createCategory(category)) {
			return new ResponseEntity<CategoryDTO>(new CategoryErrorException(
					"Unable to create new category. A category with name already exist."),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<CategoryDTO>(category, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") final int id, @RequestBody CategoryDTO category) {
		CategoryDTO currentcategory = categoryService.findById(id);
		if (currentcategory == null) {
		return new ResponseEntity<CategoryDTO>(
		new CategoryErrorException("Unable to upate. Category with id "
		+ id + " not found."), HttpStatus.NOT_FOUND);
		}
		CategoryDTO updatedCategory = categoryService.updateCategory(id, category);
		return new ResponseEntity<CategoryDTO>(updatedCategory, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("id") final int id) {
		CategoryDTO currentcategory = categoryService.findById(id);
		if (currentcategory == null) {
		return new ResponseEntity<CategoryDTO>(
		new CategoryErrorException("Unable to delete Category with id "
		+ id + " not found."), HttpStatus.NOT_FOUND);
		}
		categoryService.deleteCategory(id);
		return new ResponseEntity<CategoryDTO>(HttpStatus.NO_CONTENT);
	}

}
