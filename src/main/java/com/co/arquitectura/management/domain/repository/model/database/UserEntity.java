package com.co.arquitectura.management.domain.repository.model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import org.apache.log4j.Logger;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class UserEntity implements Serializable {

	final static Logger logger = Logger.getLogger(UserEntity.class);
	private static final long serialVersionUID = -2463354084291480284L;

	@Id
	private Integer id;

	@Column(nullable = false , name = "usuario")
	private String  user;


	@Column(nullable = false , name = "clave")
	private String  password;

	@Column(nullable = false , name = "correo")
	private String  email;

	@Column(nullable = false , name = "contador")
	private Integer  count;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
