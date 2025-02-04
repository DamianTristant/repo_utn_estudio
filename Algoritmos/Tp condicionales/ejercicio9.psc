Algoritmo ejercicio9
	Definir mes Como Caracter;
	Definir importe, totalFinal Como Real;
	totalFinal=0;
	
	
	Escribir "Ingrese el importe del producto:";
	Leer importe;
	Escribir "Ingrese el mes que lo compro:";
	Leer mes;
	
	Segun mes Hacer
		"septiembre","octubre","noviembre":
			totalFinal = importe - 500;
			Escribir "El monto final con el descuento es: ", totalFinal;
		De Otro Modo:
			Escribir "El monto final es: ", importe;
	FinSegun
	
FinAlgoritmo
