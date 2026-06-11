package com.tup.programacion3.repositories;

import com.tup.programacion3.entities.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProductoRepositoryImpl extends BaseRepositoryImpl<Producto> implements ProductoRepository {

    public ProductoRepositoryImpl(EntityManager em){
        super(em, Producto.class);
    }

    @Override
    public List<Producto> buscarPorCategoria(Long categoriaId) {
        // Consulta JPQL uniendo tablas por el atributo de relación con filtro de activos
        String jpql = "SELECT p FROM Categoria c JOIN c.productos p " +
                "WHERE c.id = :categoriaId AND p.eliminado = false AND c.eliminado = false";

        TypedQuery<Producto> query = em.createQuery(jpql, Producto.class);
        query.setParameter("categoriaId", categoriaId);

        return query.getResultList();
    }
}
