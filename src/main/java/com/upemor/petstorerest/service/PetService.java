package com.upemor.petstorerest.service;

import java.util.List;

import com.upemor.petstorerest.model.PetDTO;

public interface PetService {
	
	List<PetDTO> listAllPets();
	
	PetDTO findById(int id);
	
	boolean createPet(PetDTO pet);
	
	PetDTO updatePet(int id, PetDTO pet);
	
	void deletePet(int id);

}
