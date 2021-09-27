package com.co.arquitectura.management.adapter.database.repository;
import com.co.arquitectura.management.adapter.database.model.request.LoginBDRequestDTO;
import com.co.arquitectura.management.domain.repository.LoginRepository;
import com.co.arquitectura.management.domain.repository.model.database.UserEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.co.arquitectura.management.adapter.database.LoginRepositoryJPA;
import java.util.List;

@Repository
public class LoginDatabaseRepository implements LoginRepository {
	final static Logger logger = Logger.getLogger(LoginDatabaseRepository.class);

	private final LoginRepositoryJPA loginRepositoryJPA;

	@Autowired
	public LoginDatabaseRepository(LoginRepositoryJPA loginRepositoryJPA) {
		this.loginRepositoryJPA = loginRepositoryJPA;
	}

	@Override
	public UserEntity getUserAndPassword(LoginBDRequestDTO logonBDRequestDTO)  {
		List<UserEntity> users= this.loginRepositoryJPA.findAll();
		UserEntity userEntity = users.stream()
				.filter(x -> x.getPassword().equals(logonBDRequestDTO.getPassword()) && x.getUser().equals(logonBDRequestDTO.getEmail()) )
				.findFirst()
				.orElse(null);

		if(userEntity == null) {
			logger.warn("Se intento auntenticar un usuario con los siguientes datos: Usuario: "+ logonBDRequestDTO.getUser()+" clave: "+ logonBDRequestDTO.getPassword());
		}

		UserEntity dto = users.stream()
				.filter(x -> x.getUser().equals(logonBDRequestDTO.getUser()))
				.findFirst()
				.orElse(null);

		if(dto != null && userEntity == null){
			this.loginRepositoryJPA.deleteById(dto.getId());
			dto.setCount(dto.getCount()+1);
			this.loginRepositoryJPA.saveAndFlush(dto);
		}

		return userEntity;
	}

	@Override
	public List<UserEntity> getAllUser() {
		return this.loginRepositoryJPA.findAll();
	}
}
