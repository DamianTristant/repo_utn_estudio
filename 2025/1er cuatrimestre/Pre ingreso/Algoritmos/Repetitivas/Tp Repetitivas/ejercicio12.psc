Algoritmo ejercicio12
	Definir cont , num Como Entero;
	cont = 0;
	
	Escribir "Ingre el numero que desea saber si es primo o no:";
	leer num;
	
	Para i<-1 Hasta num Con Paso 1 Hacer
		si num mod i == 0 Entonces
			cont = cont + 1;
		FinSi
	FinPara
	
	si cont = 2 Entonces
		Escribir "El numero es primo";
	sino
		Escribir "El numero no es primo";
	FinSi
	
FinAlgoritmo
