Algoritmo ejercicio3
	Definir num1, num2, suma Como Entero;
	suma = 0;
	
	Escribir "Ingrese el 1er numero:";
	leer num1;
	Escribir "Ingrese el 2do numero:";
	leer num2;
	
	Mientras num1<num2-1 Hacer
		num1 = num1 + 1;
		suma = suma + num1;
	FinMientras
	
	Escribir "La suma total entra ambos numeros es: ", suma;
	
	
	
FinAlgoritmo
