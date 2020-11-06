package com.upemor.petstorerest.service;

import java.util.List;

import com.upemor.petstorerest.model.Orderpet;

public interface OrderService {
	
	List<Orderpet> listAllOrderpet();
	
	Orderpet findById(int id);
	
	boolean createOrderpet(Orderpet order);
	
	Orderpet updateOrderpet(int id, Orderpet order);
	
	void deleteOrderpet(int id);

}
