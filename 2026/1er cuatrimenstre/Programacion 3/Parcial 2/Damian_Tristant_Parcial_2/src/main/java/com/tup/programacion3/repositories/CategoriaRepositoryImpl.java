package com.tup.programacion3.repositories;

import com.tup.programacion3.entities.Categoria;


public class CategoriaRepositoryImpl extends BaseRepositoryImpl<Categoria> implements CategoriaRepository {
    public CategoriaRepositoryImpl() {
        super(Categoria.class);
    }
}
