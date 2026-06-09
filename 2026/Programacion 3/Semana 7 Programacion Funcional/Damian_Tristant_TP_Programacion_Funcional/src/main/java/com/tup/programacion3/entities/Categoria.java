package com.tup.programacion3.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true) // Incluye los datos de Base en el toString

public class Categoria extends Base {
    private String nombre;
    private String descripcion;
    @Builder.Default
    @ToString.Exclude // Evita bucles infinitos al imprimir
    private Set<Producto> productos = new HashSet<>();


    // Método para agregar un producto a esta categoría
    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }
}
