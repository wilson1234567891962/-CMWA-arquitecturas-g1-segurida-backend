package com.co.arquitectura.management.utils.exception;

import org.apache.log4j.Logger;

public class ArchitectureException extends Exception {

    final static Logger logger = Logger.getLogger(ArchitectureException.class);
    private static final long serialVersionUID = 4607469051774932616L;

    public ArchitectureException(String code) {
        super(code);
    }
}
