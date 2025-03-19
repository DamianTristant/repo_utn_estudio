Algoritmo ejercicio13
	Definir palabra Como Caracter;
	Escribir "Ingrese una palabra:";
	Leer palabra;
	
	si Subcadena(palabra,0,0) == Subcadena(palabra,Longitud(palabra)-1,Longitud(palabra)-1) Entonces
		Escribir "Correcto"
	SiNo
		Escribir "Incorrecto"
	FinSi
		
	
FinAlgoritmo
