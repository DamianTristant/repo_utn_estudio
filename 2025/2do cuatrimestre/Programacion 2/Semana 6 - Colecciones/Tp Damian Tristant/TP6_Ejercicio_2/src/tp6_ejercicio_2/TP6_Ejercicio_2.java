/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tp6_ejercicio_2;

import java.util.List;

/**
 *
 * @author enfer
 */
public class TP6_Ejercicio_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Creamos una biblioteca
        Biblioteca biblioteca=new Biblioteca("Biblioteca Municipal");
        
        //Creamos Autores
        Autor laura=new Autor("1","Laura Mendez","Argentina");
        Autor diego= new Autor("2","Diego Salazar","Mexico");
        Autor sofia= new Autor("3","Sofia Pereira","Uruguay");
        
        //Agregamos los libros por composicion
        biblioteca.agregarLibro("A234","Logica de Programacion Moderna", 2021, laura);
        biblioteca.agregarLibro("H345", "Sistemas Distribuidos Esenciales", 2023, diego);
        biblioteca.agregarLibro("S234", "Arquitectura de Software", 2021, laura );
        biblioteca.agregarLibro("C234", "Patron de diseño con Java", 2020, sofia );
        biblioteca.agregarLibro("F234", "Estructura de Datos en Profundidad", 2024, diego);
        //Listamos los libros de la biblioteca
        System.out.println("Los libros de la biblioteca "+biblioteca.getNombre()+"son: ");
        biblioteca.listarLibros();
        System.out.println("-------------------------------------------------");
        //buscamos un libro por ISBN
        System.out.println("Buscamos un libro por isbn A234");
        biblioteca.buscarLibroPorIsbn("A234").mostrarInfo();
        System.out.println("........................................................");
        //buscamos los libros del mismo anio de publicacion
        
        int anioFiltro=2021;
        List<Libro> librosPorAnio=biblioteca.filtrarLibrosPorAnio(anioFiltro);
        
        System.out.println("Los libros encontrados del anio "+anioFiltro+" son: ");
        for(Libro libro:librosPorAnio){
            libro.mostrarInfo();
        }
        System.out.println("///////////////////////////////////////////77");
        //eliminamos un libro por isbn
        
        biblioteca.eliminarLibro("S234");
        
        //Listamos los libros despues de eliminar uno por isbn
        System.out.println("Imprimimos los libros despues de eliminar uno");
        biblioteca.listarLibros();
        //Imprimimos la cantidad total de libros en la biblioteca
        System.out.println("La cantidad total de libros en la biblioteca " +
                biblioteca.getNombre()+"son: "+biblioteca.obtenerCantidadLibros());
        //Listamos los autores de los libros de la biblioteca.
        System.out.println("Los autores de los libros de la biblioteca son: ");
        biblioteca.mostrarAutoresDisponibles();
    }
    
}
