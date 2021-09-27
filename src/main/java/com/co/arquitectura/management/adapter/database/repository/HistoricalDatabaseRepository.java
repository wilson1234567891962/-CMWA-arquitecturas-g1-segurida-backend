package com.co.arquitectura.management.adapter.database.repository;

import com.co.arquitectura.management.adapter.database.HistoricalRepositoryJPA;
import com.co.arquitectura.management.adapter.database.LoginRepositoryJPA;
import com.co.arquitectura.management.adapter.database.model.request.LoginBDRequestDTO;
import com.co.arquitectura.management.domain.repository.HistoricalRepository;
import com.co.arquitectura.management.domain.repository.LoginRepository;
import com.co.arquitectura.management.domain.repository.model.database.HistoricalEntity;
import com.co.arquitectura.management.domain.repository.model.database.UserEntity;
import com.co.arquitectura.management.domain.service.model.request.HistoricalRequestDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HistoricalDatabaseRepository implements HistoricalRepository {
	final static Logger logger = Logger.getLogger(HistoricalDatabaseRepository.class);

	private final HistoricalRepositoryJPA historicalRepositoryJPA;

	@Autowired
	public HistoricalDatabaseRepository(HistoricalRepositoryJPA historicalRepositoryJPA) {
		this.historicalRepositoryJPA = historicalRepositoryJPA;
	}

	@Override
	public List<HistoricalEntity> getHistorical(HistoricalRequestDTO historicalRequestDTO) {
		List<HistoricalEntity> historical= this.historicalRepositoryJPA.findAll();
		if(!historical.isEmpty()) {
			return historical.stream()
					.filter(x -> x.getUser().toLowerCase().equals(historicalRequestDTO.getUser()))
					.collect(Collectors.toList());
		}
		return historical;
	}
}
