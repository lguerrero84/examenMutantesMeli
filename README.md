# examenMutantesMeli

### Examen Mutantes MELI

El presente es con el fin de explicar cómo se realizó el desarrollo de la solución y como se puede consumir los servicios web que se expusieron:

## Desarrollo

En primer lugar se especifica que el desarrollo fue realizado en Java utilizando el Framework de Spring y con maven como repositorio,
se utilizó conexión a una base de datos creada en Amazon de tipo RDS Mysql y la conexión se realizó por medio de JPA.

Se desplego el jar en una instancia EC2 de AWS y se creo un APIGataway también en nube para enlazar el enpoint y que fuera consumida a través de este.

El servicio consta de dos métodos, el primero (mutants) que es un servicio POST y que se encarga enviar una Array para que sea evaluado por el back,
el enpoint es el siguiente:

https://u1y5ong0sl.execute-api.us-east-1.amazonaws.com/mutants/mutants

la forma en que se deben enviar los datos es:

{
    "dna":["CTGCGA","CATTGC","ATATGT","CGAATG","CCACTA","TCACTG"]
}


El segundo metodo (states) corresponde a una peticion GET que devuelve informacion sobre cuantos humanos o mutantes se han analizado,
el endpoint para este segundo metodo es:

https://u1y5ong0sl.execute-api.us-east-1.amazonaws.com/mutants/stats

y devuelve una respuesta como la siguiente:

{
    "count_mutant_dna": 4,
    "count_human_dna": 3,
    "ratio": 1.0
}


