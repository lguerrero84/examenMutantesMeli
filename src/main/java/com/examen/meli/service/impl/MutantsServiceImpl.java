package com.examen.meli.service.impl;

import com.examen.meli.dto.AverageMutants;
import com.examen.meli.model.Mutants;
import com.examen.meli.repository.IMutantsRepository;
import com.examen.meli.service.IMutantsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MutantsServiceImpl implements IMutantsService {

	@Autowired
	private IMutantsRepository respository;
	
	
	@Override
	public Mutants registerEntry(Mutants mutants) {
		return respository.save(mutants);
	}

	@Override
	public Mutants modifyEntry(Mutants t, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mutants modify(Mutants t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEntry(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mutants listById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mutants> listElement() {
		// TODO Auto-generated method stub
		return respository.findAll();
	}
	
	public AverageMutants averageMutants() {
		
		List<Mutants> avg= respository.findAll();
		int totalMutant=0;
		int totalHuman=0;
		int total=0;
		total=avg.size();
		
		for(Mutants m:avg)
		{
			if(m.getResultado()==1)
			{
				totalMutant ++;
			}else if(m.getResultado()==2) {
				totalHuman++;
			}
		}
		
		AverageMutants average= new AverageMutants();
		
		average.setCount_mutant_dna(totalMutant);
		average.setCount_human_dna(totalHuman);
		average.setRatio(totalHuman/totalHuman);
		return average;
		
	}

}
