package com.mycompany.ejercitaciontpuno;

import java.util.Scanner;

public class EjercicioCinco {

    public static void main(String[] args) {
        // Declaramos input 
        Scanner input = new Scanner(System.in);
        
        // Declaramos variables num1, num2
        int num1;
        int num2;
        
        // Leemos los datos del usuario
        System.out.println("Ingrese dos numeros enteros");
        System.out.println("Numero 1: ");
        num1 = Integer.parseInt(input.nextLine());
        System.out.println("Numero 2: ");
        num2 = Integer.parseInt(input.nextLine());
        
        // Mostramos los datos por pantalla
        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
        System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
        System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
        System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
        
    }
    
}
