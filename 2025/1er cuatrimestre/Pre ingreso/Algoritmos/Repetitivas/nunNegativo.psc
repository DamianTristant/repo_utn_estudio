Algoritmo nunNegativo
	Definir num, iterador Como Entero;
	Definir hayNegativo Como Logico;
	
	hayNegativo = Falso;
	
	para iterador <- 1 Hasta 5 Con Paso 1 Hacer
		Escribir "Ingrese un numero";
		Leer num;
		
		si num < 0 Entonces
			hayNegativo = Verdadero;
		FinSi
	FinPara
	
	si hayNegativo == Verdadero Entonces
		Escribir "Hay numeros negativos";
	SiNo
		Escribir "No hay numeros negativos"
	FinSi
	
FinAlgoritmo
