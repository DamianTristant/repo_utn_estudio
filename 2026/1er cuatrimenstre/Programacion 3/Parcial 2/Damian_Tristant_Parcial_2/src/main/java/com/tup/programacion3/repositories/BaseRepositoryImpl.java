package com.tup.programacion3.repositories;

import com.tup.programacion3.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {

    private final Class<T> claseEntidad;

    public BaseRepositoryImpl(Class<T> claseEntidad) {
        this.claseEntidad = claseEntidad;
    }

    @Override
    public T guardar(T entidad) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Usamos merge() en lugar de persist() para que cree o actualice de forma inteligente
            T entidadGuardada = em.merge(entidad);
            tx.commit();
            return entidadGuardada;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            // Garantizamos el cierre del EntityManager en un bloque finally
            em.close();
        }
    }

    @Override
    public Optional<T> buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Retornamos un Optional<T> para evitar NullPointerException
            T entidad = em.find(claseEntidad, id);
            return Optional.ofNullable(entidad);
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> listarActivos() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT e FROM " + claseEntidad.getSimpleName() + " e WHERE e.eliminado = false";
            return em.createQuery(jpql, claseEntidad).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminarLogico(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T entidad = em.find(claseEntidad, id);
            if (entidad != null) {
                // Usamos reflexión para setear el campo 'eliminado' dinámicamente en la clase abstracta
                claseEntidad.getMethod("setEliminado", boolean.class).invoke(entidad, true);
                em.merge(entidad);
                tx.commit();
                return true; // Retornamos true si se pudo realizar la baja
            }
            return false; // Retornamos false si el ID no existía
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }
}