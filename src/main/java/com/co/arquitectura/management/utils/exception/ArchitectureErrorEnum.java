package com.co.arquitectura.management.utils.exception;
import org.apache.log4j.Logger;

public enum ArchitectureErrorEnum {

    SUCCESS_TRANSACTION("TR-0000"),
    DATABASE_OBJECT_CONSULTING("DB-0000"),
    LOGIN_PASSWORD_AND_USER_CONSULTING("LG-0000"),
    GENERIC_ERROR("EX-0000");

    final static Logger logger = Logger.getLogger(ArchitectureErrorEnum.class);
    private String code;

    ArchitectureErrorEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
