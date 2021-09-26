package com.co.arquitectura.management.adapter.database;
import com.co.arquitectura.management.domain.repository.model.database.UserEntity;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepositoryJPA extends JpaRepository<UserEntity, Integer> {
	final static Logger logger = Logger.getLogger(LoginRepositoryJPA.class);
}
