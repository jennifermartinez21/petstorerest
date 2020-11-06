package com.upemor.petstorerest.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.upemor.petstorerest.model.UserDTO;
import com.upemor.petstorerest.service.UserService;

@Component
public class AdminCreator {
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void adminCreate() {
		if(!userService.existsUserByEmail("admin@localhost.com")) {
			UserDTO user = new UserDTO("admin","admin","admin","admin@localhost.com","admin",true,"ADMIN");
			userService.createUser(user);
		}
	}

}
