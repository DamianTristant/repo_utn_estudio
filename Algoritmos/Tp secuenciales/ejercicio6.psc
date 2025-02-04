Algoritmo ejercicio6
	Definir num1, num2 Como Entero;
	Definir suma, resta, multiplicacion, division, potencia, resto Como Entero;
	Escribir "Ingrese el primer numero:";
	leer num1;
	Escribir "Ingrese el segundo numero";
	Leer num2;
	
	suma = num1 + num2;
	resta = num1 - num2;
	multiplicacion = num1 * num2;
	division = num1 / num2;
	potencia = num1 ^ num2;
	resto = num1 MOD num2;
	
	Escribir "La suma de ambos numeros es: ", suma;
	Escribir "La resta de ambos numeros es: ", resta;
	Escribir "La multiplicacion de ambos numeros es: ", multiplicacion;
	Escribir "La division de ambos numeros es: ", division;
	Escribir "La potencia del primero elevado al segundo es: ", potencia;
	Escribir "El resto de dividir el primero entre el segundo es: ",resto;
	
FinAlgoritmo
