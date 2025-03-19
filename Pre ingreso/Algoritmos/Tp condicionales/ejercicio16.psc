Algoritmo ejercicio16
	Definir num1,num2, total Como Entero;
	Definir seleccion Como Caracter;
	
	Escribir "Ingrese el primer numero:";
	Leer num1;
	Escribir "Ingrese el segundo numero:";
	Leer num2;
	
	Escribir "Que operacion desea realizar?";
	Escribir "Ingrese los siguientes codigos:";
	Escribir "S: Suma";
	Escribir "R: Resta";
	Escribir "M: Multiplicacion";
	Escribir "D: Division";
	Leer seleccion;
	
	Segun seleccion Hacer
		's':
			total = num1 + num2;
			Escribir "Su seleccion fue SUMA y el total es:", total;
		'r':
			total = num1 -  num2;
			Escribir "Su seleccion fue RESTA y el total es:", total;
		'm':
			total = num1 * num2;
			Escribir "Su seleccion fue MULTIPLICACION y el total es:", total;
		'd':
			total = num1 / num2;
			Escribir "Su seleccion fue DIVISION y el total es:", total;
		De Otro Modo:
			Escribir "Su seleccion no es correcta";
	FinSegun
	
FinAlgoritmo
