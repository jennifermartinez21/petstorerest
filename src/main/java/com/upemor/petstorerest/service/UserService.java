package com.upemor.petstorerest.service;

import java.util.List;

import com.upemor.petstorerest.model.UserDTO;

public interface UserService {

	UserDTO findUserForLogin(String email, String password);
	
	List<UserDTO> listAllUsers();
	
	UserDTO findById(int id);
	
	boolean createUser(UserDTO user);
	
	UserDTO updateUser(int id, UserDTO user);
	
	void deleteUser(int id);
	
	boolean existsUserByEmail(String email);
}
