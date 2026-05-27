Algoritmo ejercicio9
	Definir contInicial, contFinal, numUsuario, suma Como Real;
	contInicial = 0;
	suma = 0;
	
	Escribir "Ingrese el numero maximo que desear saber su promedio:"
	Leer contFinal;
	
	Mientras contInicial <= contFinal Hacer
		Escribir "Ingrese numeros:";
		Leer numUsuario;
		suma = suma + numUsuario;
		contInicial = contInicial +1;
	FinMientras
	
	Escribir "A llegado al maximo ingresado y el promedio es: ", suma/contFinal;
	
FinAlgoritmo
