Algoritmo ejercicio17
	definir num , x , perfecto Como Entero;
	x = 2;
	
	Escribir "Escribe un numero:";
	leer num;
	
	Mientras x <= num Hacer
		si num mod x == 0 Entonces
			perfecto = perfecto + (num/x);
		FinSi
		x = x + 1 ;
	FinMientras
	
	si perfecto == num Entonces
		Escribir "El numero ", num, " es un numero perfecto";
	SiNo
		Escribir "El numero " , num, " no es un numero perfecto";
	FinSi
	
FinAlgoritmo
