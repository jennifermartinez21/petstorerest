package com.upemor.petstorerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.petstorerest.model.TagDTO;

@Repository
public interface TagRepository extends JpaRepository<TagDTO, Integer>{
	
	TagDTO findById(int id);
	
	public TagDTO findByname(String name);
	
}
