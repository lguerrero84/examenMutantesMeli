package com.examen.meli.service;

import com.examen.meli.dto.AverageMutants;
import com.examen.meli.model.Mutants;

public interface IMutantsService extends ICRUD<Mutants> {
	
	AverageMutants averageMutants();

}
