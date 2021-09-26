package com.co.arquitectura.management.utils.exception;

import java.util.HashMap;

import org.apache.log4j.Logger;


public class ConstantErrors {

	public static final HashMap<String, ArchitectureError> ERRORS_STATES = new HashMap<>();
	final static Logger logger = Logger.getLogger(ConstantErrors.class);
	
	static {
		ERRORS_STATES.put(ArchitectureErrorEnum.SUCCESS_TRANSACTION.getCode(), new ArchitectureError(ArchitectureErrorEnum.SUCCESS_TRANSACTION.getCode(),"SU transacción  fue procesa con exito."));

		ERRORS_STATES.put(ArchitectureErrorEnum.GENERIC_ERROR.getCode(), new ArchitectureError(ArchitectureErrorEnum.GENERIC_ERROR.getCode(),"Se presentaron problemas tecnicos por favor intente nuevamente."));

		ERRORS_STATES.put(ArchitectureErrorEnum.LOGIN_PASSWORD_AND_USER_CONSULTING.getCode(), new ArchitectureError(ArchitectureErrorEnum.LOGIN_PASSWORD_AND_USER_CONSULTING.getCode(),"Por favor revise el usuario o clave digitado."));

		ERRORS_STATES.put(ArchitectureErrorEnum.DATABASE_OBJECT_CONSULTING.getCode(), new ArchitectureError(ArchitectureErrorEnum.DATABASE_OBJECT_CONSULTING.getCode(),"No tenemos registro asociados para esta solicitud."));
	}
}
