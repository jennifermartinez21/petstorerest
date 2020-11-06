package com.upemor.petstorerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.petstorerest.model.CategoryDTO;
import com.upemor.petstorerest.model.UserDTO;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDTO, Integer>{
	
	CategoryDTO findById(int id);
	
	public UserDTO findByname(String name);

}
