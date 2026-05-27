Algoritmo ejercicio14
	Definir montoMinimo Como Entero;
	Definir total,cantHoras, cantLitros Como Real;
	
	montoMinimo = 400;
	
	Escribir "Ingrese la cantidad de horas que uso el vehiculo:";
	Leer cantHoras;
	
	si cantHoras <= 2 Entonces
		total = montoMinimo;
		Escribir "El total a abonar es: ", total;
	SiNo
		si cantHoras > 2 Entonces
			Escribir "Ingrese cuantos litro de combustible consumio:";
			Leer cantLitros;
			total = ((cantHoras*60)*5.20) + (cantLitros * 40);
			Escribir "El total a abonar es: ",total;
		FinSi
	FinSi
	
	
FinAlgoritmo
