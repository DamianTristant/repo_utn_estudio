package com.tp.jpa.repository;

import com.tp.jpa.model.Producto;

/**
 * Repositorio de Producto. Hereda todo el CRUD de BaseRepository; no
 * requiere queries adicionales.
 *
 * Nota de diseño: la búsqueda de productos por Categoria NO vive aquí porque
 * la relación Categoria–Producto es unidireccional y la dueña es Categoria
 * (es Categoria quien posee el Set<Producto>). Producto no conoce su
 * Categoria, por lo que esa consulta se ubica en CategoriaRepository.
 */
public class ProductoRepository extends BaseRepository<Producto> {

    public ProductoRepository() {
        super(Producto.class);
    }
}
