package com.examen.meli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class dnaDTO {
	
	@JsonProperty("dna")
	private String [] dna;

}
