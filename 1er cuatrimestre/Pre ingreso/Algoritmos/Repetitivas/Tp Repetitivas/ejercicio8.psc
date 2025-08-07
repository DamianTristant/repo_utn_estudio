Algoritmo ejercicio8
	Definir numComparar,contInicial,contFinal,numPositivo,numNegativo,numImpar,numPar Como Entero;
	contInicial = 0;
	numPositivo = 0;
	numNegativo = 0;
	numImpar = 0;
	numPar = 0;
	
	Escribir "Indique cuantos numeros desea comparar:";
	Leer contFinal;
	
	Mientras contInicial<=contFinal Hacer
		Escribir "Ingrese numeros:";
		Leer numComparar;
		
		si numComparar < 0 Entonces
			numNegativo = numNegativo +1;
		SiNo
			si numComparar > 0 y numComparar mod 2 == 0 Entonces
				numPositivo = numPositivo +1;
				numPar = numPar +1;
			SiNo
				si numComparar > 0 y numComparar mod 2 <> 0 Entonces
					numPositivo = numPositivo + 1;
					numImpar = numImpar +1;
				FinSi
			FinSi
		FinSi
		contInicial = contInicial + 1;
	FinMientras
	
	Escribir "Usted a ingresado:";
	Escribir numPositivo, " numeros positivos";
	Escribir numNegativo, " numeros negativos";
	Escribir numPar, " numeros pares";
	Escribir numImpar, " numeros impares";
	
FinAlgoritmo
