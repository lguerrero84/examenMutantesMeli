package com.examen.meli.dto;

import lombok.Data;

@Data
public class AverageMutants {

	private int count_mutant_dna;
	private int count_human_dna;
	private double ratio;
	
}
