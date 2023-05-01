package com.ust.sourcecourse.configuration.exception;

import java.util.List;

public class CustomException extends RuntimeException {
    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	public CustomException(String message) {
        super(message);
    }

    public static class ResourceNotFoundException extends CustomException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ResourceNotFoundException(String message) {
            super(message);
        }

        public ResourceNotFoundException(String message, String string, Long uid) {
            super(message);
        }

        public ResourceNotFoundException(String message, String string, List<String> tags) {
            super(message);
        }
    }

    public static class ResourceAlreadyExistsException extends CustomException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ResourceAlreadyExistsException(String message) {
            super(message);
        }
    }


}


