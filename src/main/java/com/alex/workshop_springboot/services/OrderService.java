package com.alex.workshop_springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.workshop_springboot.entities.Order;
import com.alex.workshop_springboot.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order findById(Long id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.get();

	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}
}
