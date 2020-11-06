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

import com.upemor.petstorerest.exception.UserErrorException;
import com.upemor.petstorerest.model.UserDTO;
import com.upemor.petstorerest.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> listAllUsers() {
		List<UserDTO> users = userService.listAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
			}
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable("id") Integer id) {
		UserDTO user = userService.findById(id);
		if (user == null) {
		return new ResponseEntity<UserDTO>(
		new UserErrorException("User with id "
		+ id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@RequestBody final UserDTO user) {
		if(!userService.createUser(user)) {
			return new ResponseEntity<UserDTO>(new UserErrorException(
					"Unable to create new user. A User with email or username already exist."),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final int id, @RequestBody UserDTO user) {
		UserDTO currentuser = userService.findById(id);
		if (currentuser == null) {
		return new ResponseEntity<UserDTO>(
		new UserErrorException("Unable to upate. User with id "
		+ id + " not found."), HttpStatus.NOT_FOUND);
		}
		UserDTO updatedUser = userService.updateUser(id, user);
		return new ResponseEntity<UserDTO>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final int id) {
		UserDTO currentuser = userService.findById(id);
		if (currentuser == null) {
		return new ResponseEntity<UserDTO>(
		new UserErrorException("Unable to delete User with id "
		+ id + " not found."), HttpStatus.NOT_FOUND);
		}
		userService.deleteUser(id);

		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}
}