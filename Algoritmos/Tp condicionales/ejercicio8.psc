Algoritmo ejercicio8
	Definir palabra, signoExcla,signoInte Como Caracter;
	signoExcla = '!';
	signoInte = '?';
	
	Escribir "Ingrese una palabra:";
	Leer palabra;
	
	si Longitud(palabra) == 4 Entonces
		Escribir concatenar(palabra,signoExcla);
	SiNo
		Escribir Concatenar(palabra,signoInte);
	FinSi
	
FinAlgoritmo
