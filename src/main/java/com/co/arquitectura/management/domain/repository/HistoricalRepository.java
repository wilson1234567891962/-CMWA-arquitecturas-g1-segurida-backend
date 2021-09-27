package com.co.arquitectura.management.domain.repository;

import com.co.arquitectura.management.domain.repository.model.database.HistoricalEntity;
import com.co.arquitectura.management.domain.service.model.request.HistoricalRequestDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HistoricalRepository {

	final static Logger logger = Logger.getLogger(HistoricalRepository.class);

	List<HistoricalEntity> getHistorical(HistoricalRequestDTO historicalRequestDTO);


}
