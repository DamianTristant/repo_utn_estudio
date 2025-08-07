Algoritmo ejercicio6
	Definir nota1,nota2,nota3,promedio Como Real;
	Escribir "Ingrese la primer nota:";
	Leer nota1;
	Escribir "Ingrese la segunda nota:";
	Leer nota2;
	Escribir "Ingrese la tercera nota:";
	Leer nota3;
	
	promedio = (nota1 + nota2 + nota3) / 3;
	
	si promedio >= 70 Entonces
		Escribir "Esta aprobado";
	SiNo
		Escribir "No aprueba";
	FinSi
	
FinAlgoritmo
