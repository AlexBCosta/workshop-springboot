package com.alex.workshop_springboot.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) { // passa id q nao foi encontrado
		super("Resource not found. Id " + id);
	}
}
