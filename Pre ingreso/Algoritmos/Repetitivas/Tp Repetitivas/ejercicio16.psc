Algoritmo ejercicio16
	definir num , long Como Entero;
	Definir text, aux Como Caracter;
	
	Escribir "Ingresa un numero:";
	Leer num;
	
	text = ConvertirATexto(num);
	long = Longitud(text);
	aux = '';
	
	mientras long >= 0 Hacer
		aux = aux + Subcadena(text,long,long);
		long = long -1;
	FinMientras
	
	Escribir "El numero ",num, " invertido es ", ConvertirANumero(aux);
	
	
	
	
FinAlgoritmo
