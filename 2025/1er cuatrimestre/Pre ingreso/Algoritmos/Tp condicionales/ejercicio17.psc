Algoritmo ejercicio17
	Definir dia, mes, año Como Real;
	Definir fecha_correcta Como Logico;
	Definir n_mes Como Caracter;
	
	Escribir "Ingrese el día";
	Leer dia;
	Escribir "Ingrese el mes";
	Leer mes;
	Escribir "Ingrese el año";
	Leer año;
	
	Si dia >= 1 y dia <= 28 Entonces
		Si mes >= 1 y mes <= 12 Entonces
			fecha_correcta = Verdadero;
		SiNo
			fecha_correcta = Falso;
		FinSi
	SiNo
		Si dia = 30 Entonces
			si mes = 4 o mes = 6 o mes = 9 o mes = 11;
				fecha_correcta = Verdadero;
			SiNo
				fecha_correcta = Falso;
			FinSi
		SiNo
			Si dia = 31 Entonces
				Si mes = 1 o mes = 3 o mes = 5 o mes = 7 o mes = 8 o mes = 10 o mes = 12 Entonces
					fecha_correcta = Verdadero;
				SiNo
					fecha_correcta = Falso;
				FinSi
			SiNo
				Si dia = 29 Entonces
					// Se comprueba si el año es bisiesto
					si año MOD 4 = 0 Entonces
						si mes = 2 Entonces;
							fecha_correcta = Verdadero;
						SiNo
							fecha_correcta = Falso;
						FinSi
					SiNo
						fecha_correcta = Falso;
					FinSi
				SiNo
					fecha_correcta = Falso;
				FinSi
			FinSi
		FinSi
	FinSi
	
	segun mes Hacer
		1:
			n_mes = "enero"
		2:
			n_mes = "febrero";
		3:
			n_mes = "marzo";
		4:
			n_mes = "abril";
		5:
			n_mes = "mayo";
		6:
			n_mes = "junio";
		7:
			n_mes = "julio";
		8:
			n_mes = "agosto";
		9:
			n_mes = "septiembre";
		10:
			n_mes = "octubre";
		11:
			n_mes = "noviembre";
		12:
			n_mes = "diciembre";
		De Otro Modo:
			Escribir "Mes incorrecto";
	FinSegun
	
	
	Si fecha_correcta = Verdadero Entonces
		Escribir dia, " de ", n_mes, " de ", año;
	SiNo
		Escribir "La fecha ingresada es incorrecta";
	FinSi
FinAlgoritmo
