/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp6_ejercicio_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author enfer
 */
public class Biblioteca {

    private String nombre;
    private List<Libro> libros;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();

    }

    public List<Libro> getLibros() {
        return Collections.unmodifiableList(libros);
    }

    public void agregarLibro(String isbn, String titulo,
                        int anioPublicacion, Autor autor) {
        if (!isbn.equals("") && isbn != null && !titulo.equals("") 
                && titulo != null && anioPublicacion > 0 && autor != null) {
            libros.add(new Libro(isbn, titulo, anioPublicacion, autor));
        }

    }

    public void listarLibros() {
        System.out.println("La lista de libros de la biblioteca" + nombre + " es: ");
        for (Libro libro : libros) {
            libro.mostrarInfo();
        }
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public void eliminarLibro(String isbn) {
        if (isbn != null) {
            Libro libroEliminar = this.buscarLibroPorIsbn(isbn);
            if (libroEliminar != null) {
                libros.remove(libroEliminar);
            }
        }
    }


    public int obtenerCantidadLibros(){
        return libros.size();
    }
    
    public void mostrarAutoresDisponibles(){
        for(Libro libro: libros){
            System.out.println(libro.getAutor().mostrarInfo());
        }
    }
    
    public List<Libro> filtrarLibrosPorAnio(int anio){
        List<Libro> libroPorAnio=new ArrayList();
        for(Libro libro:libros){
            if(libro.getAnioPublicacion()==anio){
                libroPorAnio.add(libro);
            }     
        }
        return Collections.unmodifiableList(libroPorAnio);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
