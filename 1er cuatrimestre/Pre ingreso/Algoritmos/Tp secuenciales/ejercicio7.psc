Algoritmo ejercicio7
	Definir area,perimetro,cateto1,cateto2, hipotenusa Como Real;
	Escribir "Ingrese el primer cateto:";
	Leer cateto1;
	Escribir "Ingrese el segundo cateto:";
	leer cateto2;
	
	hipotenusa = ((cateto1*cateto1)+(cateto2*cateto2))/2;
	perimetro = cateto1 + cateto2 + hipotenusa;
	area = (cateto1 * cateto2)/2;
	
	Escribir "El area es: ", area," y su perimetro es: ",perimetro;
FinAlgoritmo
