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

import com.upemor.petstorerest.exception.OrderpetErrorException;
import com.upemor.petstorerest.model.Orderpet;
import com.upemor.petstorerest.service.OrderService;


@RestController
@RequestMapping("/api/orderpet")
public class OrderpetController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/")
	public ResponseEntity<List<Orderpet>> listAllOrderpet() {
		List<Orderpet> orders = orderService.listAllOrderpet();
		if (orders.isEmpty()) {
			return new ResponseEntity<List<Orderpet>>(HttpStatus.NO_CONTENT);
			}
		return new ResponseEntity<List<Orderpet>>(orders, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Orderpet> findById(@PathVariable("id") Integer id) {
		Orderpet orderpet = orderService.findById(id);
		if (orderpet == null) {
			return new ResponseEntity<Orderpet>(
			new OrderpetErrorException("Order with id "
			+ id + " not found"), HttpStatus.NOT_FOUND);
			}
		return new ResponseEntity<Orderpet>(orderpet, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orderpet> createOrderpet(@RequestBody final Orderpet orderpet) {
		if(!orderService.createOrderpet(orderpet)) {
			return new ResponseEntity<Orderpet>(new OrderpetErrorException(
					"Unable to create new order. A order with name already exist."),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Orderpet>(orderpet, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orderpet> updateOrderpet(@PathVariable("id") final int id, @RequestBody Orderpet orderpet) {
		Orderpet currentorder = orderService.findById(id);
		if (currentorder == null) {
		return new ResponseEntity<Orderpet>(
		new OrderpetErrorException("Unable to upate. Order with id "
		+ id + " not found."), HttpStatus.NOT_FOUND);
		}
		Orderpet updatedOrderpet = orderService.updateOrderpet(id, orderpet);
		return new ResponseEntity<Orderpet>(updatedOrderpet, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Orderpet> deleteOrderpet(@PathVariable("id") final int id) {
		Orderpet currentorder = orderService.findById(id);
		if (currentorder == null) {
		return new ResponseEntity<Orderpet>(
		new OrderpetErrorException("Unable to delete Order with id "
		+ id + " not found."), HttpStatus.NOT_FOUND);
		}
		orderService.deleteOrderpet(id);
		return new ResponseEntity<Orderpet>(HttpStatus.NO_CONTENT);
	}

}
