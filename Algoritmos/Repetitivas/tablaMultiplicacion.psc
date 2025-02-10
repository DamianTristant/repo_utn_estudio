Algoritmo tablaMultiplicacion
	Definir num, multiplicador, producto Como Entero;
	Escribir "Ingrese el numero que desear saber su tabla:";
	Leer num;
	
	Para multiplicador <- 1 Hasta 10 Con Paso 1 Hacer
		producto = num * multiplicador;
		Escribir num, " x ", multiplicador, " = ", producto;
	FinPara
	
FinAlgoritmo
