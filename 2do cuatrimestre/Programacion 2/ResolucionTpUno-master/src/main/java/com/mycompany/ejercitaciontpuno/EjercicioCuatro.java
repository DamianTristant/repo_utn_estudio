package com.mycompany.ejercitaciontpuno;

import java.util.Scanner;

public class EjercicioCuatro {

    public static void main(String[] args) {
        // Declarmos input de tipo Scanner para poder leer datos del usuario
        Scanner input = new Scanner(System.in);
        
        // Declaramos variables
        String nombre;
        int edad;
        
        // Pedimos al usuario ingresar los datos
        System.out.println("Ingrese su nombre");
        nombre = input.nextLine();
        System.out.println("Ingrese su edad");
        edad = Integer.parseInt(input.nextLine()); 
        
        // Mostramos los datos por pantalla
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        
    }
    
}
