package com.co.arquitectura.management.adapter.api.facade;
import com.co.arquitectura.management.domain.service.model.request.HistoricalRequestDTO;
import com.co.arquitectura.management.domain.service.model.request.LoginRequestDTO;
import com.co.arquitectura.management.utils.exception.ArchitectureException;
import com.co.arquitectura.management.domain.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class LoginFacade {


    final static Logger logger = Logger.getLogger(LoginFacade.class);
    
    private LoginService loginService;
    
    @Autowired
    public LoginFacade(LoginService loginService) {
    	this.loginService = loginService;
    }
    
    public Object executeLogin(LoginRequestDTO loginRequestDTO) throws ArchitectureException, SQLException, ClassNotFoundException {
        return  this.loginService.executeValidation(loginRequestDTO);
    }

    public Object executeHistorical(String token, HistoricalRequestDTO historicalRequestDTO) throws ArchitectureException, SQLException, ClassNotFoundException {
        return  this.loginService.executeHistorical(token,historicalRequestDTO);
    }
}
