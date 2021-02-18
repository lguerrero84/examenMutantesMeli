# examenMutantesMeli

#### Examen Mutantes MELI

El presente es con el fin de explicar cómo se realizó el desarrollo de la solución y como se puede consumir los servicios web que se expusieron:

### Desarrollo

En primer lugar se especifica que el desarrollo fue realizado en Java utilizando el Framework de Spring y con maven como repositorio,
se utilizó conexión a una base de datos creada en Amazon de tipo RDS Mysql y la conexión se realizó por medio de JPA.


En la fase de desarrollo se realizó una separación de clases de acuerdo al propósito utilizando las anotaciones de spring, es decir se separaron las clases que realizan la conexión hacia la base de datos (respository) de la que tienen la lógica del aplicativo (service), asi tambien como la separación de las clase controller , las credenciales hacia la base de datos se encuentran en el archivo application.yaml.

Para la lógica de la aplicación, se utilizaron dos ciclos for para recorrer dos posiciones del arreglo al tiempo, se va analizando directamente del arreglo cada coincidencia en alguno de los sentidos indicados (horizontal, diagonal, hacia arriba o abajo) y en caso de encontrar las dos secuencias que identifican al mutante, se termina los dos ciclos de recorrido, esto permite recorrer solo una vez todo el arreglo.

La base de datos consta de una sola tabla:

![melitabla](https://user-images.githubusercontent.com/43051783/108377878-77af9a80-71d2-11eb-8528-606bd859c117.jpg)


Se desplego el jar en una instancia EC2 de AWS y se creo un APIGataway también en nube para enlazar el enpoint y que fuera consumida a través de este.

A continuación se muestra la arquitectura utilizada en la nube de aws.

![arquitectura](https://user-images.githubusercontent.com/43051783/108292110-75612800-7161-11eb-88d8-57e6382783e5.jpg)


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


