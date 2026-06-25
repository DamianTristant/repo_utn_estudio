package com.tup.programacion3.repositories;

import com.tup.programacion3.JPAUtil;
import com.tup.programacion3.entities.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProductoRepositoryImpl extends BaseRepositoryImpl<Producto> implements ProductoRepository {

    // Eliminamos el constructor que recibía el EntityManager manual
    public ProductoRepositoryImpl() {
        super(Producto.class);
    }

    @Override
    public List<Producto> filtrarPorCategoria(Long categoriaId) {
        // Cada método abre y cierra su propio EntityManager
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT p FROM Producto p JOIN Categoria c ON p MEMBER OF c.productos " +
                    "WHERE c.id = :categoriaId AND p.eliminado = false";

            TypedQuery<Producto> query = em.createQuery(jpql, Producto.class);
            query.setParameter("categoriaId", categoriaId);
            return query.getResultList();
        } finally {
            // Garantizamos el cierre para no dejar conexiones colgadas en H2
            em.close();
        }
    }
}
