package com.examen.meli.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.examen.meli.dto.AverageMutants;
import com.examen.meli.model.Coordinate;
import com.examen.meli.model.Direction;
import com.examen.meli.model.Mutants;
import com.examen.meli.repository.IMutantsRepository;
import com.examen.meli.service.IMutantsService;

@Repository ("serviceMutants")
public class SecuenceMutants {

	public  int secFound = 0;
	
	@Autowired
	private IMutantsService service;
	
	public boolean validateMutants(String[] dna) {
		String sec1 = "";
		String sec2 = "";
		secFound = 0;
		String secuenciaCompleta="";
		Map<String, List<Coordinate>> mapa = new HashMap<String, List<Coordinate>>();
		Mutants mutants=new Mutants();
		
		for (int k=0;k <dna.length;k++)
		{
			secuenciaCompleta +=dna[k]+" ";
		}
		
		for (int j = 0; j < dna.length; j++) {

			sec1 = dna[j];
			secuenciaCompleta +=sec1+" ";
			secFound = ValidacionHorizontal(sec1);

			if (secFound == 2) {
				break;
			}

			if ((j + 1) < dna.length) {
				sec2 = dna[j + 1];

				for (int i = 0; i < dna[0].length(); i++) {
					if (i == 0) // Primer dato
					{

						if (Character.toString(sec2.charAt(i)).equalsIgnoreCase(Character.toString(sec1.charAt(i)))) {

							mapa = ValidacionArriba(mapa, Direction.A.toString(), j, i);

							if (secFound == 2) {
								break;
							}

						}

						if (Character.toString(sec2.charAt(i))
								.equalsIgnoreCase(Character.toString(sec1.charAt(i + 1)))) {

							mapa = ValidacionDerecha(mapa, Direction.AD.toString(), j, i);
							if (secFound == 2) {
								break;
							}
						}

					} else if (i == (dna[0].length() - 1)) // Ultimo dato de la cadena
					{

						if (Character.toString(sec2.charAt(i)).equalsIgnoreCase(Character.toString(sec1.charAt(i)))) {

							mapa = ValidacionArriba(mapa, Direction.A.toString(), j, i);

							if (secFound == 2) {
								break;
							}
						}

						if (Character.toString(sec2.charAt(i)).equalsIgnoreCase(Character.toString(sec1.charAt(i - 1)))) {

							mapa = ValidacionIzquierda(mapa, Direction.AI.toString(), j, i);

							if (secFound == 2) {
								break;
							}

						}

					} else {

						if (Character.toString(sec2.charAt(i)).equalsIgnoreCase(Character.toString(sec1.charAt(i)))) {

							mapa = ValidacionArriba(mapa, Direction.A.toString(), j, i);

							if (secFound == 2) {
								break;
							}
						}

						if (Character.toString(sec2.charAt(i))
								.equalsIgnoreCase(Character.toString(sec1.charAt(i - 1)))) {

							mapa = ValidacionIzquierda(mapa, Direction.AI.toString(), j, i);

							if (secFound == 2) {
								break;
							}
						}

						if (Character.toString(sec2.charAt(i))
								.equalsIgnoreCase(Character.toString(sec1.charAt(i + 1)))) {
							mapa = ValidacionDerecha(mapa, Direction.AD.toString(), j, i);
							if (secFound == 2) {
								break;
							}
						}

					}

				}

			}

			if (secFound == 2) {
				break;
			}

		}

		mutants.setCadena(secuenciaCompleta);
		if (secFound == 2) {
			mutants.setResultado(1);
			mutants = service.registerEntry(mutants);
			return true;
		} else {
			mutants.setResultado(2);
			mutants = service.registerEntry(mutants);
			return false;
		}

	}

	public  int ValidacionHorizontal(String cadena) {
		String TTTT = "TTTT";
		String GGGG = "GGGG";
		String AAAA = "AAAA";
		String CCCC = "CCCC";

		if (cadena.contains(TTTT)) {
			secFound++;
		}

		if (cadena.contains(GGGG)) {
			secFound++;
		}

		if (cadena.contains(AAAA)) {
			secFound++;
		}
		if (cadena.contains(CCCC)) {
			secFound++;
		}

		return secFound;
	}

	public  Map<String, List<Coordinate>> ValidacionArriba(Map<String, List<Coordinate>> mapa, String direction,
			int fil, int col) {

		List<Coordinate> coordinateList = new ArrayList<Coordinate>();

		if (mapa.containsKey(direction + " " + fil + " " + (col))) {
			coordinateList = mapa.remove(direction + " " + fil + " " + (col));
			if (coordinateList.size() >= 2) {
				coordinateList.add(new Coordinate(fil, col));
				coordinateList.add(new Coordinate((fil + 1), col));
				mapa.put(direction + " " + (fil + 1) + " " + (col), coordinateList);
				secFound++;
			} else {
				coordinateList.add(new Coordinate(fil, (col)));
				mapa.put(direction + " " + (fil + 1) + " " + col, coordinateList);
			}
		} else {
			coordinateList = new ArrayList<Coordinate>();
			coordinateList.add(new Coordinate(fil, (col)));
			mapa.put(direction + " " + (fil + 1) + " " + (col), coordinateList);
		}
		return mapa;
	}

	public  Map<String, List<Coordinate>> ValidacionDerecha(Map<String, List<Coordinate>> mapa, String direction,
			int fil, int col) {

		List<Coordinate> coordinateList = new ArrayList<Coordinate>();
		if (mapa.containsKey(direction + " " + fil + " " + (col + 1))) {
			coordinateList = mapa.remove(direction + " " + fil + " " + (col + 1));
			if (coordinateList.size() >= 2) {
				coordinateList.add(new Coordinate(fil, (col + 1)));
				coordinateList.add(new Coordinate((fil + 1), col));
				mapa.put(direction + " " + (fil + 1) + " " + (col), coordinateList);
				secFound++;
			} else {
				coordinateList.add(new Coordinate(fil, (col + 1)));
				mapa.put(direction + " " + (fil + 1) + " " + (col), coordinateList);
			}
		} else {
			coordinateList = new ArrayList<Coordinate>();
			coordinateList.add(new Coordinate(fil, (col + 1)));
			mapa.put(direction + " " + (fil + 1) + " " + (col), coordinateList);
		}

		return mapa;

	}

	public  Map<String, List<Coordinate>> ValidacionIzquierda(Map<String, List<Coordinate>> mapa,
			String direction, int fil, int col) {

		List<Coordinate> coordinateList = new ArrayList<Coordinate>();

		if (mapa.containsKey(direction + " " + fil + " " + (col - 1))) {
			coordinateList = mapa.remove(direction + " " + fil + " " + (col - 1));
			if (coordinateList.size() >= 2) {
				coordinateList.add(new Coordinate(fil, (col - 1)));
				coordinateList.add(new Coordinate((fil + 1), col));
				mapa.put(direction + " " + (fil + 1) + " " + (col), coordinateList);
				secFound++;
			} else {
				coordinateList.add(new Coordinate(fil, (col - 1)));
				mapa.put(direction + " " + (fil + 1) + " " + (col), coordinateList);
			}
		} else {
			coordinateList = new ArrayList<Coordinate>();
			coordinateList.add(new Coordinate(fil, (col - 1)));
			mapa.put(direction + " " + (fil + 1) + " " + (col), coordinateList);
		}
		return mapa;
	}

	public AverageMutants getAverageMutants() {
		
		AverageMutants avg=new AverageMutants();
		avg=service.averageMutants();
		
		return avg;
	}
	
}
