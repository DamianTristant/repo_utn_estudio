Algoritmo ejercicio2
	Definir num, digitos Como Entero;
	digitos = 0;
	
	
	Escribir "Ingrese un numero:";
	Leer num;
	
	Mientras num <> 0 Hacer
		num = trunc(num/10);
		digitos = digitos + 1;
		
	FinMientras
	
	Escribir "El numero seleccionado tiene ", digitos, " digitos";
	
	
FinAlgoritmo
