package com.upemor.petstorerest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upemor.petstorerest.model.PetDTO;
import com.upemor.petstorerest.repository.PetRepository;

@Service
public class PetServiceImp implements PetService{
	
	@Autowired
	private PetRepository petRepository;

	
	public List<PetDTO> listAllPets() {
		// TODO Auto-generated method stub
		return petRepository.findAll();
	}

	
	public PetDTO findById(int id) {
		// TODO Auto-generated method stub
		PetDTO pet = petRepository.findById(id);
		return pet;
	}

	
	public boolean createPet(PetDTO pet) {
		// TODO Auto-generated method stub
		petRepository.saveAndFlush(pet);
		return true;
	}

	
	public PetDTO updatePet(int id, PetDTO pet) {
		PetDTO currentPet = petRepository.findById(id);
		currentPet.setName(pet.getName());
		currentPet.setPhotourl(pet.getPhotourl());
		currentPet.setPrice(pet.getPrice());
		currentPet.setStatus(pet.isStatus());
		petRepository.saveAndFlush(currentPet);
		return currentPet;
	}

	
	public void deletePet(int id) {
		// TODO Auto-generated method stub
		petRepository.deleteById(id);
	}

}
