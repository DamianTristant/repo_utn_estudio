Algoritmo ejercicio10
	Definir num1, num2 Como Entero;
	
	Escribir "Ingrese el primer numero:";
	Leer num1;
	Escribir "Ingrese el segundo numero:";
	Leer num2;
	
	si num1 mod 2 == 0 y num2 mod 2 == 0 Entonces
		Escribir "Ambos numeros son par";
	SiNo
		si num1 mod 2 == 0 o num2 mod 2 == 0 Entonces
			Escribir "Uno de ellos no es par"
		SiNo
			Escribir "Los numeros no son pares"
		FinSi
	FinSi
	
FinAlgoritmo
