Algoritmo Ejercicio1
	Definir f Como Caracter
	f = "holaMundoCruel"
	Escribir c(f)
FinAlgoritmo

Funcion  r <- c(f)
	Definir l,i Como Entero
	Definir r Como Caracter
	r = f
	l = Longitud(f)
	para i = 0 Hasta l Con Paso 5 Hacer
		r = Subcadena(f,i,l)
	FinPara
FinFuncion
