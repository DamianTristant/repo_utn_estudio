Algoritmo ejercicio11
	Definir contInicial, contFinal, numUsuario, numMin, numMax Como Entero;
	contInicial = 0;
	numMin = 10000;
	numMax = 0;
	
	Escribir "Indique la cantidad maxima de numeros que desea comparar:"
	Leer contFinal;
	
	Mientras contInicial<=contFinal Hacer
		Escribir "Ingrese numeros:";
		Leer numUsuario;
		
		si numUsuario<numMin y numUsuario<numMax Entonces
			numMin = numUsuario;
		SiNo
			si numUsuario>numMax Entonces
				numMax = numUsuario;
			FinSi
			
		FinSi
		contInicial = contInicial +1 ;
	FinMientras
	
	Escribir "El mayor valor ingresado es: ", numMax;
	Escribir "El menor valor ingresado es: ", numMin;
	
	
FinAlgoritmo
