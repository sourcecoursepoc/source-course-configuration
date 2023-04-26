package com.ust.sourcecourse.configuration.exception;

import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandlerController {
	

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode().value(), ex.getReason());
		return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
	}

	/**
	 * check body request for post
	 * 
	 * @param ex
	 * @return
	 */

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.joining(", "));
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	/**
	 * Invalid Request
	 * 
	 * @param ex
	 * @return
	 */

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid request parameter");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class, DataAccessException.class })
	public ResponseEntity<ErrorResponse> handleDatabaseException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		
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
