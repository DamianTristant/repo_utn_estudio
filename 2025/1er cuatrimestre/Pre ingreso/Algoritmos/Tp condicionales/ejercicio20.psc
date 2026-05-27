Algoritmo ejercicio20
	Definir cantTorDefec, cantTorBuenos Como Entero;
	
	
	Escribir "Ingrese la produccion del operario para saber su grado de eficiciencia:";
	Escribir "Cantidad tornillos defectuosos:";
	Leer cantTorDefec;
	Escribir "Cantidad tornillos Buenos:";
	Leer cantTorBuenos;
	
	si cantTorDefec > 200 y cantTorBuenos < 10000 Entonces
		Escribir "El operario es de grado 5";
	SiNo
		si cantTorDefec < 200 y cantTorBuenos < 10000 Entonces
			Escribir "El operario es de grado 6";
		SiNo
			si cantTorDefec > 200 y cantTorBuenos > 10000 Entonces
				Escribir "El operario es de grado 7";
			SiNo
				si cantTorDefec < 200 y cantTorBuenos > 10000 Entonces
					Escribir "El operario es de grado 8";
				FinSi
			FinSi
		FinSi
	FinSi
	
FinAlgoritmo
