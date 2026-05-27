Algoritmo ejercicio18
	definir num1, num2, mcd, maximo, iterador Como Entero;
	iterador = 1;
	maximo = 1;
	
	Escribir "Ingrese el primer numero:";
	Leer num1;
	Escribir "Ingrese el segundo numero:";
	Leer num2;
	
	Mientras iterador <= num1 Hacer
		si num1 mod iterador == 0 y num2 mod iterador == 0 Entonces
			si iterador > maximo Entonces
				maximo = iterador; 
			FinSi
		FinSi
		iterador = iterador + 1;
	FinMientras
	
	Escribir "El maximo comun divisor es: ", maximo;
	
	
	
FinAlgoritmo
