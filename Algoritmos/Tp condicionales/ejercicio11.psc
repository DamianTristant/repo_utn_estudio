Algoritmo ejercicio11
	Definir calif1,calif2,calif3 Como Entero;
	Definir varAux1, varAux2, varAux3 Como Logico;
	
	varAux1 = Falso;
	varAux2 = Falso;
	varAux3 = Falso;
	
	Escribir "Ingrese la nota N`1:";
	Leer calif1;
	Escribir "Ingrese la nota N`2:";
	Leer calif2;
	Escribir "Ingrese la nota N`3:";
	Leer calif3;
	
	si calif1 >=1 y calif1 <=10 Entonces
		varAux1 = Verdadero;
	FinSi
	
	si calif2 >=1 y calif2 <=10 Entonces
		varAux2 = Verdadero;
	FinSi
	
	si calif3 >=1 y calif3 <=10 Entonces
		varAux3 = Verdadero;
	FinSi
	
	si varAux1 y varAux2 y varAux3 == Verdadero Entonces
		Escribir "Todas las calificaciones son validas";
	SiNo
		Escribir "Alguna de las calificaciones no son validas";
	FinSi
	
	
FinAlgoritmo
