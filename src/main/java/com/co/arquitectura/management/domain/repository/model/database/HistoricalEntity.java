package com.co.arquitectura.management.domain.repository.model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "historial")
public class HistoricalEntity implements Serializable {

	final static Logger logger = Logger.getLogger(HistoricalEntity.class);
	private static final long serialVersionUID = -2463354084291480284L;

	@Id
	private Integer id;

	@Column(nullable = false , name = "descripcion")
	private String  description;


	@Column(nullable = false , name = "doctor")
	private String  doctor;

	@Column(nullable = false , name = "fecha_registro")
	private Date dateRegister;

	@Column(nullable = false , name = "cobro")
	private float expense;

	@Column(nullable = false , name = "usuario")
	private String  user;
}
