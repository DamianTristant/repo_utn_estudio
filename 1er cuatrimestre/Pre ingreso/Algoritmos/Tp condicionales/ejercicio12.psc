Algoritmo ejercicio12
	Definir palabra, letraComparar Como Caracter;
	
	letraComparar = 'a';
	
	Escribir "Ingrese una palabra:";
	Leer palabra;
	
	si Subcadena(palabra,0,0) == letraComparar Entonces
		Escribir "Correcto";
	SiNo
		Escribir "Incorrecto";
	FinSi
	
	
	
FinAlgoritmo
