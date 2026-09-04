package com.tup.programacion3.entities;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

public class Categoria extends Base {
    private String nombre;
    private String descripcion;
    private Set<Producto> productos; //Relacion 1 a m

    public Categoria(){
        super();
        this.productos = new HashSet<>(); //Inicializo el ser para evitar nullpointerExeption
    }

    public Categoria(Long id, String nombre, String descripcion) {
        super(id, false, java.time.LocalDateTime.now()); // Pasamos los datos a Base
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new HashSet<>(); // El Set siempre arranca vacío al crear la categoría
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    // Método para agregar un producto a esta categoría
    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }
}
