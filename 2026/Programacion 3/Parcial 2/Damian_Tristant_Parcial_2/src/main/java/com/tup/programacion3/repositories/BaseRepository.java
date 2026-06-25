package com.tup.programacion3.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    T guardar(T entidad);
    Optional<T> buscarPorId(Long id);
    List<T> listarActivos();
    boolean eliminarLogico(Long id);
}
