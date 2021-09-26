package com.co.arquitectura.management.utils.exception;

import org.apache.log4j.Logger;

public class ArchitectureError {
	final static Logger logger = Logger.getLogger(ArchitectureError.class);
	private String code;
	private String message;

	public ArchitectureError(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
