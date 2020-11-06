package com.upemor.petstorerest.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.petstorerest.model.Orderpet;

@Repository
public interface OrderpetRepository extends JpaRepository<Orderpet, Integer> {
	
	Orderpet findById(int id);
	
	public Orderpet findBycreated(Date created);

}