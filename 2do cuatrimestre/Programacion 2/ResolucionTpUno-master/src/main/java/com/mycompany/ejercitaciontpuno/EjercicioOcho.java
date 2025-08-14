package com.mycompany.ejercitaciontpuno;

import java.util.Scanner;

public class EjercicioOcho {

    public static void main(String[] args) {
        // Declaramos input 
        Scanner input = new Scanner(System.in);
        
        // Declaramos variables
        int num1, num2;
           
        // Pedimos los datos al usuario
        System.out.println("Ingrese dos numeros enteros");
        System.out.println("Numero 1: ");
        num1 = Integer.parseInt(input.nextLine());
        System.out.println("Numero 2: ");
        num2 = Integer.parseInt(input.nextLine());
        
        // Mostramos el resultado por pantalla
        System.out.println(num1 + " / " + num2 + " = " + ((double) num1 / num2));
    }
    
}
