Algoritmo ejercicio4
	Definir vc,vs Como Entero
	vs = 3
	Dimension vc[vs]
	vc[0] = 4
	vc[1] = 6
	vc[2] = 1
	Escribir c(vc,vs)
FinAlgoritmo
Funcion m <- c(vc,vs)
	Definir m,i Como Entero
	m = vc[0]
	Para i = 0 Hasta (m-4) Hacer
		si(vc[i]>m) Entonces
			m = vc[i]
		FinSi
	FinPara
FinFuncion

	

