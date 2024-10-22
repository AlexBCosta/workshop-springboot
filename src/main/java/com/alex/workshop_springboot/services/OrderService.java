package com.alex.workshop_springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.alex.workshop_springboot.entities.Order;
import com.alex.workshop_springboot.repositories.OrderRepository;
import com.alex.workshop_springboot.services.exceptions.DatabaseException;
import com.alex.workshop_springboot.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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

	public Order insert(Order obj) {
		return orderRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			if (!orderRepository.existsById(id)) {
				throw new ResourceNotFoundException(id);
			}
			orderRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	public Order update(Long id, Order obj) {
		try {
			Order entity = orderRepository.getReferenceById(id);
			updateData(entity, obj);
			return orderRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Order entity, Order obj) {
		entity.setClient(obj.getClient());
		entity.setMoment(obj.getMoment());
		entity.setOrderStatus(obj.getOrderStatus());
		entity.setPayment(obj.getPayment());
	}
}
