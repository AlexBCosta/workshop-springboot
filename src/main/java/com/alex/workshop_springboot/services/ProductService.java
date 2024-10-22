package com.alex.workshop_springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.alex.workshop_springboot.entities.Product;
import com.alex.workshop_springboot.repositories.ProductRepository;
import com.alex.workshop_springboot.services.exceptions.DatabaseException;
import com.alex.workshop_springboot.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository ProductRepository;

	public List<Product> findAll() {
		return ProductRepository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> obj = ProductRepository.findById(id);
		return obj.get();
	}

	public Product insert(Product obj) {
		return ProductRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			if (!ProductRepository.existsById(id)) {
				throw new ResourceNotFoundException(id);
			}
			ProductRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	public Product update(Long id, Product obj) {
		try {
			Product entity = ProductRepository.getReferenceById(id);
			updateData(entity, obj);
			return ProductRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product entity, Product obj) {
		entity.setDescription(obj.getDescription());
		entity.setImgUrl(obj.getImgUrl());
		entity.setName(obj.getName());
		entity.setPrice(obj.getPrice());

	}
}
