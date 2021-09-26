package com.co.arquitectura.management.adapter.api.controller;
import com.co.arquitectura.management.adapter.api.ApiConst;
import com.co.arquitectura.management.domain.service.model.request.LoginRequestDTO;
import com.co.arquitectura.management.utils.exception.ConstantErrors;
import com.co.arquitectura.management.adapter.api.facade.LoginFacade;
import com.co.arquitectura.management.utils.exception.ArchitectureErrorEnum;
import com.co.arquitectura.management.utils.exception.ArchitectureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.google.gson.Gson;


import org.apache.log4j.Logger;
@RestController
@RequestMapping(value = ApiConst.LOGIN_PATH)
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class LoginController {

    final static Logger logger = Logger.getLogger(LoginController.class);
    
    private LoginFacade LoginFacade;
    private Gson gson;
    
    @Autowired
    public LoginController(LoginFacade loginFacade, Gson gson) {
        this.LoginFacade = loginFacade;
        this.gson = gson;
    }
    
	@GetMapping("/message")
	public String getMessage() {
		return "V20200914_01";
	}
	
	@ApiOperation(value = "login")
	@RequestMapping(value = "/login/", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> doLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
		try {

			return new ResponseEntity<String>(this.gson.toJson(this.LoginFacade.executeLogin(loginRequestDTO)), HttpStatus.OK);
		}
		catch (ArchitectureException e) {
			logger.error("Se presentaron problemas en el controller getBuses ", e);
			return new ResponseEntity<String>(new Gson().toJson(ConstantErrors.ERRORS_STATES.get(e.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (Exception e) {
			logger.error("Se presentaron problemas enviar la checkFields en el controller getBuses",e);
			return new ResponseEntity<String> (this.gson.toJson(ConstantErrors.ERRORS_STATES.get(ArchitectureErrorEnum.GENERIC_ERROR.getCode())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
