package com.examen.meli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.meli.dto.AverageMutants;
import com.examen.meli.dto.dnaDTO;
import com.examen.meli.service.impl.SecuenceMutants;

@RestController
@RequestMapping("/mutants")
public class validateMutants {
	
	@Autowired
	@Qualifier("serviceMutants")
	private SecuenceMutants serviceSecuenceMutants; 
	
	@PostMapping(value="/validate")
	public ResponseEntity<HttpStatus> findMutants(@RequestBody dnaDTO dna){
		
	
		
		boolean validacion = serviceSecuenceMutants.validateMutants(dna.getDna());
		
		if(validacion)
		{
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@GetMapping(value="/stage")
	public ResponseEntity<AverageMutants> getAverage(){
			
		AverageMutants avg= new AverageMutants();
		avg=serviceSecuenceMutants.getAverageMutants();
		return new ResponseEntity<>(avg,HttpStatus.OK);
		
	}

}
