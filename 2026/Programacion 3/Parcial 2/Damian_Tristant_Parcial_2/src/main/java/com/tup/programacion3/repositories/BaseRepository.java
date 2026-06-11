package com.tup.programacion3.repositories;

import java.util.List;

public interface BaseRepository<T> {
    void guardar(T entidad);
    T buscarPorId(Long id);
    List<T> listarActivos();
    void eliminarLogico(Long id);
}
