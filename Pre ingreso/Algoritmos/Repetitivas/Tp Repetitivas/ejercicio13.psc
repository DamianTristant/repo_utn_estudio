Algoritmo ejercicio13
	Definir num, digito , menordigito Como Entero;
	digito = 0;
	menordigito = 10;
	
	Escribir "Ingrese un numero:";
	Leer num;
	
	Mientras num <> 0 Hacer
		digito = num %10;
		si digito<menordigito Entonces
			menordigito = digito;
		FinSi
		num	= trunc(num/10);
	FinMientras
	
	Escribir "El menor digito es: ", menordigito;
	
FinAlgoritmo
