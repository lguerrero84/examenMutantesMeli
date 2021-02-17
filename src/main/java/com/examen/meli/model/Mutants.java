package com.examen.meli.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="mutants")
@Data
public class Mutants implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idmutants;
	
	@Column(name="CADENA")
	private String cadena;
	
	@Column(name="RESULTADO")
	private Integer resultado;
	

}
