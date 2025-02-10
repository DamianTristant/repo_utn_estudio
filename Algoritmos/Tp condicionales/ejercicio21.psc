Algoritmo ejercicio21
	Definir modContratacion Como Caracter;
	Definir cantHoras , cantHorasEstandar, sueldo, valorHora, montoVentas, valorHoraExtra Como Real;
	
	sueldo=0;
	cantHorasEstandar = 40;
	valorHora=3500;
	valorHoraExtra=5250;
	
	Escribir "Ingrese la cantidad de horas trabajadas:";
	Leer cantHoras;
	Escribir "Ingrese el monto total de ventas:";
	Leer montoVentas;
	
	Escribir "Ingrese su tipo de contratacion (a,b,c):";
	Escribir "a) Comisión";
	Escribir "b) Salario fijo + comisión";
	Escribir "c) Salario fijo";
	Leer modContratacion;
	
	si modContratacion == 'a' Entonces
		sueldo = montoVentas * 0.40
		Escribir "Su sueldo es de: $",sueldo;
	FinSi
	
	si modContratacion == 'b' Entonces
		si cantHoras <=40 Entonces
			sueldo = (cantHoras * valorHora) + (montoVentas * 0.25);
			Escribir "Su sueldo es de: $",sueldo;
		SiNo
			Escribir "La cantidad de horas no coincide con su tipo de contratacion";
		FinSi
	FinSi
	
	si modContratacion == 'c' Entonces
		si cantHoras<=40 Entonces
			sueldo = cantHoras * valorHora;
			Escribir "Su sueldo es: $",sueldo;
		SiNo
			si cantHoras>40 Entonces
				sueldo = (cantHorasEstandar * valorHora) + ((cantHoras - cantHorasEstandar)*valorHoraExtra);
				Escribir "Su sueld es: $",sueldo;
			FinSi
		FinSi
	FinSi
	
	
	
	
FinAlgoritmo
