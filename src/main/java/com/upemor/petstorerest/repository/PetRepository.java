package com.upemor.petstorerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.petstorerest.model.PetDTO;

@Repository
public interface PetRepository extends JpaRepository<PetDTO, Integer>{
	
	PetDTO findById(int id);
	
}
