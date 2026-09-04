package com.tup.programacion3.repositories;

import com.tup.programacion3.entities.Producto;
import java.util.List;

public interface ProductoRepository extends BaseRepository<Producto> {
    List<Producto> filtrarPorCategoria(Long categoriaId);
}
