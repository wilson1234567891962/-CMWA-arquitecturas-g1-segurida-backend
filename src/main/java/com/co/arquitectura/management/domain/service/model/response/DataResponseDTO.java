package com.co.arquitectura.management.domain.service.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponseDTO {

    private LoginResponseDTO data;

    public DataResponseDTO() {
    }

    public DataResponseDTO(LoginResponseDTO data) {
        this.data = data;
    }
}
