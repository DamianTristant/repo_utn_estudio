Algoritmo ejercicio4
	definir caracter1,caracter2, caracterUsuario Como Caracter;
	Escribir "Ingrese un caracter";
	leer caracterUsuario
	
	caracter1 = 's';
	caracter2 = 'n';
	
	si caracterUsuario == caracter1 Entonces
		Escribir "Correcto";
	SiNo
		si caracterUsuario == caracter2 Entonces
			Escribir "Correcto";
		SiNo
			Escribir "Incorrecto";
		FinSi
	FinSi
FinAlgoritmo
