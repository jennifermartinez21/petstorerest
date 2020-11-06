package com.upemor.petstorerest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upemor.petstorerest.model.UserDTO;
import com.upemor.petstorerest.repository.UserRepository;



@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDTO findUserForLogin(String email, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	@Override
	public List<UserDTO> listAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public boolean createUser(UserDTO user) {
		if (userRepository.findByEmail(user.getEmail()) != null || userRepository.findByUsername(user.getUsername())!=null)  {
			return false;
			}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
		userRepository.saveAndFlush(user);
		return true;
	}

	@Override
	public UserDTO updateUser(int id, UserDTO user) {
		UserDTO currentUser = userRepository.findById(id);
		currentUser.setUsername(user.getUsername());
		currentUser.setFirstname(user.getFirstname());
		currentUser.setLastname(user.getLastname());
		currentUser.setEmail(user.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        currentUser.setPassword(encoder.encode(user.getPassword()));
		currentUser.setStatus(user.isStatus());
		currentUser.setRole(user.getRole());
		userRepository.saveAndFlush(currentUser);
		
		return currentUser;
	}



	@Override
	public UserDTO findById(int id) {
		return userRepository.findById(id);
	}


	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public boolean existsUserByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}