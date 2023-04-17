package com.ust.sourcecourse.configuration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionHandlerController {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode().value(), ex.getMessage());
		return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
	}

	public static class ErrorResponse {
		private int status;
		private String message;

		public ErrorResponse(int status, String message) {
			this.status = status;
			this.message = message;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

}
