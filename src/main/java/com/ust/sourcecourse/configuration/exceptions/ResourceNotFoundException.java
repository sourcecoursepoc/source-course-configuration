package com.ust.sourcecourse.configuration.exceptions;

import java.util.List;

<<<<<<< HEAD


public class ResourceNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message, String string,Long uid) {
		super(message);
	}
	
public ResourceNotFoundException(String message, String string, List<String> tags) {
}

=======
public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message, String string, Long uid) {
        super(message);
    }

	public ResourceNotFoundException(String message, String string, List<String> tags) {
		
	}
>>>>>>> 3e8bc9e099f2e053e0ac3d57fa923b7c2fdec232
}
