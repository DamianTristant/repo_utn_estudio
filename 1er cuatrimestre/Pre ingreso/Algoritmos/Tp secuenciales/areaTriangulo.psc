Algoritmo areaTriangulo
	
	Definir Lado1, Lado2, angulo, area Como Real;
	Escribir " Ingrese los datos del triangulo:";
	Escribir "Lado 1:";
	Leer Lado1;
	Escribir "Lado 2:";
	Leer Lado2;
	Escribir "Angulo:";
	Leer angulo;
	
	area = (Lado1 * Lado2 * sen((pi/180)*angulo))/2;
	Escribir "El area del triangulo es: ", area;
	
	
	
FinAlgoritmo
