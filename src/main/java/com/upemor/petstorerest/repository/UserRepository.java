package com.upemor.petstorerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.upemor.petstorerest.model.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer>{

		UserDTO findByEmailAndPassword(String email, String password);
		
		UserDTO findById(int id);
		
		UserDTO findByEmail(String email);
		
		boolean existsUserByEmail(String email);
		
		public UserDTO findByUsername(String username);
}
