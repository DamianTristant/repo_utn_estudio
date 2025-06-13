Algoritmo Ejercicio3
	Definir N,A,B Como Entero
	N = 5
	Dimension A[N]
	Dimension B[N]
	c(A,B,N)
	Escribir cp(A,B,N)
	
FinAlgoritmo

SubProceso c(v1 Por Referencia, v2, N Por Valor)
	Definir i Como Entero
	para i = 0 Hasta n-1 Con Paso 1 Hacer
		v1[i] = i + i + i
	FinPara
	Para i=0 Hasta n-1 Con Paso 1 Hacer
		v2[i] = i * 2
	FinPara
FinSubProceso

Funcion z <- cp(v1, v2 , N)
	Definir ct, i Como Entero
	
	Definir  z como Cadena
	ct = 0
	
	para i=0 Hasta N-1 Con Paso 1 Hacer
		si (v1[0] == v1[i]) y (v1[0] == v2[i]) Entonces
			ct = ct +1
			N = N-ct
		FinSi
	FinPara
	
	z = ConvertirATexto(ct)
	Segun v1[0] Hacer
		1:
			z = "Verdadero"
		2:
			z = "2"
		3:
			z = "Falso"
	FinSegun
FinFuncion
