Algoritmo concesionaria
	definir cantKm, montoFinal Como Real;
	Escribir "Indique cuanto Km recorrio para saber su costo final:";
	leer cantKm;
	
	si cantKm <= 300 Entonces
		montoFinal = 3000;
		Escribir "Su monto a pagar es: ", montoFinal;
	SiNo
		si cantKm <= 1000 Entonces
			montoFinal = (cantKm - 300) * 181.5 + 3000;
			Escribir "Su monto a pagar es: ", montoFinal;
		SiNo
			si cantKm > 1000 Entonces
				montoFinal = (cantKm - 1000) * 121 + 3000;
				Escribir "Su monto a pagar es: ", montoFinal;
			FinSi
			
		FinSi
	FinSi
FinAlgoritmo
