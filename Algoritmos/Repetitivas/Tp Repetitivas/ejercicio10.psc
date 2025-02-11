Algoritmo ejercicio10
	Definir numUsuario, factorial Como Entero;
	Definir procedimiento Como Caracter;
	factorial=1;
	procedimiento = ' ';
	
	Escribir "Ingrese el numero del cual desea saber su factorial:";
	Leer numUsuario;
	
	para i<-1 Hasta numUsuario Con Paso 1 Hacer
		factorial = factorial*i;
		
		si i<numUsuario Entonces
			procedimiento = procedimiento+ConvertirATexto(i)+"x";
		SiNo
			procedimiento = procedimiento+ConvertirATexto(i);
		FinSi
	FinPara
	
	Escribir "Factorial de " ,numUsuario,"! = ",procedimiento, " = ", factorial;
FinAlgoritmo
