package com.upemor.petstorerest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upemor.petstorerest.model.CategoryDTO;
import com.upemor.petstorerest.model.PetDTO;
import com.upemor.petstorerest.model.TagDTO;
import com.upemor.petstorerest.repository.CategoryRepository;
import com.upemor.petstorerest.repository.PetRepository;
import com.upemor.petstorerest.repository.TagRepository;

@Service
public class PetServiceImp implements PetService{
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TagRepository tagRepository;

	
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
		if(pet.equals(petRepository.findByName(pet.getName()))) {
		return false;
		}
		CategoryDTO cat = categoryRepository.findById(pet.getCategoryDTO().getId());
		TagDTO tag = tagRepository.findById(pet.getTagDTO().getId());
		pet.setCategoryDTO(cat);
		pet.setTagDTO(tag);
		petRepository.save(pet);
		return true;
	}

	
	public PetDTO updatePet(int id, PetDTO pet) {
		PetDTO currentPet = petRepository.findById(id);
		currentPet.setName(pet.getName());
		currentPet.setPhotourl(pet.getPhotourl());
		currentPet.setPrice(pet.getPrice());
		currentPet.setStatus(pet.isStatus());
		currentPet.setCategoryDTO(pet.getCategoryDTO());
		currentPet.setTagDTO(pet.getTagDTO());
		petRepository.saveAndFlush(currentPet);
		return currentPet;
	}

	
	public void deletePet(int id) {
		// TODO Auto-generated method stub
		petRepository.deleteById(id);
	}

}
