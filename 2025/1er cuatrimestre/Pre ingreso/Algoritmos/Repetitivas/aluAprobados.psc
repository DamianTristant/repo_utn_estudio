Algoritmo aluAprobados
	
	Definir rep , cantAlu Como Entero;
	Definir nota Como Real;
	
	rep = 1;
	cantAlu = 0;
	
	Mientras rep = 1 Hacer
		Escribir "Ingrese la nota:";
		Leer nota;
		
		si nota >= 3 Entonces
			cantAlu <- cantAlu + 1;
			Escribir "Alumno aprobado";
		SiNo
			Escribir "Alumno reprobado";
		FinSi
		
		Escribir "Marque la opcion deseada";
		Escribir "1) Agregar otro alumno";
		Escribir "2) Terminar programa";
		Leer rep;
		
	FinMientras
	
	Escribir "La cant de alumnos aprobados es de: ", cantAlu;
	
FinAlgoritmo
