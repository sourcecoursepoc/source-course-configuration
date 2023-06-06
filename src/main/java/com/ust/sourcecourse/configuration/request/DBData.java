package com.ust.sourcecourse.configuration.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DBData {

	@NotBlank
	private String name;
	
	@NotBlank
	private String description;

	@NotBlank
	private String connectionURL;

//	@NotBlank
	private String username;

//	@NotBlank
	private String password;

}
