
package com.carango.bom.controller.exception;

public class EntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 8259095166248687397L;

	public EntityNotFoundException(String message) {
        super(message);
    }
}
