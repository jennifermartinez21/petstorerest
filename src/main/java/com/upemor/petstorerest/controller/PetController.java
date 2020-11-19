package com.upemor.petstorerest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.upemor.petstorerest.exception.PetErrorException;
import com.upemor.petstorerest.model.PetDTO;
import com.upemor.petstorerest.service.PetService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/pet")
public class PetController {
	
	@Autowired
	private PetService petService;

	@GetMapping("/")
	public ResponseEntity<List<PetDTO>> listAllPets() {
		List<PetDTO> pets = petService.listAllPets();
		if (pets.isEmpty()) {
			return new ResponseEntity<List<PetDTO>>(HttpStatus.NO_CONTENT);
			}
		return new ResponseEntity<List<PetDTO>>(pets, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PetDTO> findById(@PathVariable("id") Integer id) {
		PetDTO pet = petService.findById(id);
		if (pet == null) {
			return new ResponseEntity<PetDTO>(
			new PetErrorException("Pet with id "
			+ id + " not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<PetDTO>(pet, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PetDTO> createPet(@RequestBody final PetDTO pet) {
		if(!petService.createPet(pet)) {
			return new ResponseEntity<PetDTO>(new PetErrorException(
					"Unable to create new pet. A Pet with name already exist."),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<PetDTO>(pet, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PetDTO> updatePet(@PathVariable("id") final int id, @RequestBody PetDTO pet) {
		PetDTO currentpet = petService.findById(id);
		if (currentpet == null) {
			return new ResponseEntity<PetDTO>(
			new PetErrorException("Unable to upate. Pet with id "
			+ id + " not found."), HttpStatus.NOT_FOUND);
			}
			PetDTO updatedPet = petService.updatePet(id, pet);
			return new ResponseEntity<PetDTO>(updatedPet, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PetDTO> deletePet(@PathVariable("id") final int id) {
		PetDTO currentpet = petService.findById(id);
		if (currentpet == null) {
		return new ResponseEntity<PetDTO>(
		new PetErrorException("Unable to delete Pet with id "
		+ id + " not found."), HttpStatus.NOT_FOUND);
		}
		petService.deletePet(id);
		return new ResponseEntity<PetDTO>(HttpStatus.NO_CONTENT);
	}
}
