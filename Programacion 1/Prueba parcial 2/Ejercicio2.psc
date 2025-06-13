Algoritmo Ejercicio2
	Definir l Como Entero
	Dimension l[100]
	Definir n Como Entero
	l[0] = 1
	l[1] = 1
	l[2] = 1
	l[3] = 2
	l[4] = 1
	n = 5
	Escribir s(l,n)
FinAlgoritmo

Funcion r <- s(l,n)
	Definir r Como Entero
	r = 0
	si (n==0) Entonces
		r=0
	SiNo
		r = l[n-1] + s(l, n-1)
		
	FinSi
FinFuncion

