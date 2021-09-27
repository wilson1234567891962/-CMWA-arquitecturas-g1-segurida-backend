package com.co.arquitectura.management.domain.service;
import com.co.arquitectura.management.adapter.database.model.request.LoginBDRequestDTO;
import com.co.arquitectura.management.domain.repository.HistoricalRepository;
import com.co.arquitectura.management.domain.repository.model.database.HistoricalEntity;
import com.co.arquitectura.management.domain.repository.model.database.UserEntity;
import com.co.arquitectura.management.domain.service.model.request.HistoricalRequestDTO;
import com.co.arquitectura.management.domain.service.model.request.LoginRequestDTO;
import com.co.arquitectura.management.domain.service.model.response.DataResponseDTO;
import com.co.arquitectura.management.domain.service.model.response.LoginResponseDTO;
import com.co.arquitectura.management.utils.exception.ArchitectureErrorEnum;
import com.co.arquitectura.management.utils.exception.ArchitectureException;
import com.co.arquitectura.management.domain.repository.LoginRepository;
import com.co.arquitectura.management.utils.token.JwtUtils;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
	
	final static Logger logger = Logger.getLogger(LoginService.class);
	private LoginRepository loginRepository;
	private HistoricalRepository historicalRepository;
	private DozerBeanMapper dozerBeanMapper;

	@Autowired
	public LoginService(LoginRepository loginRepository, HistoricalRepository historicalRepository, DozerBeanMapper dozerBeanMapper) {
		this.loginRepository = loginRepository;
		this.historicalRepository = historicalRepository;
		this.dozerBeanMapper = dozerBeanMapper;
	}

	public DataResponseDTO executeValidation(LoginRequestDTO loginRequestDTO) throws ArchitectureException {
		LoginBDRequestDTO loginBDRequestDTO =  dozerBeanMapper.map(loginRequestDTO, LoginBDRequestDTO.class, "login-umb");
		UserEntity userEntity = this.loginRepository.getUserAndPassword(loginBDRequestDTO);
		if(userEntity != null) {
			String token = JwtUtils.createJWT(loginRequestDTO.getUser(), loginRequestDTO.getPassword(), 300000);
			return new DataResponseDTO(new LoginResponseDTO(token));
		}
		throw new ArchitectureException(ArchitectureErrorEnum.LOGIN_PASSWORD_AND_USER_CONSULTING.getCode());
	}

	public DataResponseDTO executeHistorical(String token, HistoricalRequestDTO historicalRequestDTO) throws ArchitectureException {
		List<HistoricalEntity> historical = this.historicalRepository.getHistorical(historicalRequestDTO);
		if (!token.startsWith("Bearer ") || JwtUtils.decodeJWTAnExtend(token.substring(7)).isEmpty()) {
			throw new ArchitectureException(ArchitectureErrorEnum.GENERIC_ERROR.getCode());
		}
		if(historical != null && !historical.isEmpty()) {
			return new DataResponseDTO(historical);
		}
		throw new ArchitectureException(ArchitectureErrorEnum.DATABASE_OBJECT_CONSULTING.getCode());
	}
}