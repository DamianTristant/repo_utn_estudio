Algoritmo ejercicio15
	
		Definir numInicial, numUsu, suma Como Entero;
		suma = 0;
		numInicial = 0;
		
		Escribir "Ingrese un numero hasta el cual desea sumar desde 0:";
		Leer numUsu;
		
		Mientras numInicial<=numUsu Hacer
			numInicial = numInicial + 1;
			suma = suma + numInicial;
		FinMientras
		
		Escribir "La suma entre 0 y el numero elegido es: ", suma;
		
		
FinAlgoritmo
