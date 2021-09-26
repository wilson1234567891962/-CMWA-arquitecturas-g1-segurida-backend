package com.co.arquitectura.management.adapter.api;
import org.apache.log4j.Logger;
import org.springframework.core.env.ConfigurableEnvironment;

public class ApiConst {

    final static Logger logger = Logger.getLogger(ApiConst.class);
    
	protected ApiConst() {
		super();
	}

	public static final String LOGIN_PATH = "/ARCHITECTURE";
	
	public static final String SWAGGER_PATH = "com.co.umb.login.management";

	public static ConfigurableEnvironment CONFIGURABLE_PROPERTIES;

}