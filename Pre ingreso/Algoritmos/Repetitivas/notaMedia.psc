Algoritmo notaMedia
	Definir cantAlumnos, iterador Como Entero;
	Definir calificacion, promedio, minima Como Real;
	promedio = 0;
	minima = 100;
	
	Escribir "Ingrese la cantidad de alumnos:";
	Leer cantAlumnos;
	
	Para iterador <- 1 Hasta cantAlumnos Con Paso 1 Hacer
		Escribir "Ingrese la nota del alumno:";
		Leer calificacion;
		
		promedio = promedio + calificacion;
		
		si calificacion < minima Entonces
			minima = calificacion;
		FinSi
	FinPara
	
	Escribir "La calificacion promedio es: ", promedio/cantAlumnos;
	Escribir "La calificacion menor es: ", minima;
	
FinAlgoritmo
