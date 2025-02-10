Algoritmo ejercicio5
	Definir numUsu, numAzar, cantVeces Como Entero;
	cantVeces = 0;
	numAzar = azar(10);
	
	Repetir
		Escribir "Ingrese un numero entre 0 y 9:";
		Leer numUsu;
		cantVeces = cantVeces +1;
	Mientras Que  numUsu <> numAzar
	
	Escribir "Acerto en el intento ", cantVeces;
	
	
	
	
	
FinAlgoritmo
