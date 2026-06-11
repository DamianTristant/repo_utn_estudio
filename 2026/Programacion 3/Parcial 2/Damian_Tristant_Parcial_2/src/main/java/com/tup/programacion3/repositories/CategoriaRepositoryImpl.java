package com.tup.programacion3.repositories;

import com.tup.programacion3.entities.Categoria;
import jakarta.persistence.EntityManager;

public class CategoriaRepositoryImpl extends BaseRepositoryImpl<Categoria> implements CategoriaRepository {
    public CategoriaRepositoryImpl(EntityManager em) {
        super(em, Categoria.class);
    }
}
