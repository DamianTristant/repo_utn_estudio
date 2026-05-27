Algoritmo numPositivo
	Definir num Como Entero;
	Definir opcionUsu Como Caracter;
	
	Repetir
		
		Escribir "Ingrese un numero:"
		Leer num;
		
		si num > 0 Entonces
			Escribir "Numero positivo";
		SiNo
			Escribir "Numero negativo";
		FinSi
		
		Escribir "Desea continuar?";
		Escribir "Ingrese c para continuar";
		Escribir "Ingrese s para salir";
		Leer opcionUsu;
		
	Hasta Que opcionUsu = 's' o opcionUsu = 'S'
	
FinAlgoritmo
