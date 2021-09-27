package com.co.arquitectura.management.domain.repository;
import com.co.arquitectura.management.adapter.database.model.request.LoginBDRequestDTO;
import com.co.arquitectura.management.domain.repository.model.database.UserEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public interface LoginRepository {

	final static Logger logger = Logger.getLogger(LoginRepository.class);

	UserEntity getUserAndPassword(LoginBDRequestDTO logonBDRequestDTO);


}
