package com.alex.workshop_springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.alex.workshop_springboot.entities.Category;
import com.alex.workshop_springboot.repositories.CategoryRepository;
import com.alex.workshop_springboot.services.exceptions.DatabaseException;
import com.alex.workshop_springboot.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository CategoryRepository;

	public List<Category> findAll() {
		return CategoryRepository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> obj = CategoryRepository.findById(id);
		return obj.get();
	}

	public Category insert(Category obj) {
		return CategoryRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			if (!CategoryRepository.existsById(id)) {
				throw new ResourceNotFoundException(id);
			}
			CategoryRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	public Category update(Long id, Category obj) {
		try {
			Category entity = CategoryRepository.getReferenceById(id);
			updateData(entity, obj);
			return CategoryRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());

	}
}
