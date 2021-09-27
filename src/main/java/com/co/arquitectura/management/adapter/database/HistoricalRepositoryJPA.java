package com.co.arquitectura.management.adapter.database;
import com.co.arquitectura.management.domain.repository.model.database.HistoricalEntity;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricalRepositoryJPA extends JpaRepository<HistoricalEntity, Integer> {
	final static Logger logger = Logger.getLogger(HistoricalRepositoryJPA.class);
}
