package com.upemor.petstorerest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.upemor.petstorerest.model.Orderpet;
import com.upemor.petstorerest.model.PetDTO;
import com.upemor.petstorerest.model.UserDTO;
import com.upemor.petstorerest.repository.PetRepository;
import com.upemor.petstorerest.repository.OrderpetRepository;
import com.upemor.petstorerest.repository.UserRepository;

@Service
public class OrderpetServiceImp implements OrderService {

	@Autowired
	private OrderpetRepository orderpetRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Orderpet> listAllOrderpet() {
		return orderpetRepository.findAll();
	}

	@Override
	public boolean createOrderpet(Orderpet order) {
		if(order.getCreated() == null) {
			Date date = new Date();			
			order.setCreated(date);
		}
		PetDTO pet = petRepository.findById(order.getPet().getId());
		UserDTO user = userRepository.findById(order.getUser().getId());
		order.setPet(pet);
		order.setUser(user);
		orderpetRepository.save(order);
		return true;
	}

	@Override
	public Orderpet updateOrderpet(int id, Orderpet order) {
		Orderpet currentOrderpet = orderpetRepository.findById(id);
		currentOrderpet.setCreated(order.getCreated());
		orderpetRepository.saveAndFlush(currentOrderpet);
		return currentOrderpet;
	}



	@Override
	public Orderpet findById(int id) {
		return orderpetRepository.findById(id);
	}


	@Override
	public void deleteOrderpet(int id) {
		orderpetRepository.deleteById(id);
	}

}
