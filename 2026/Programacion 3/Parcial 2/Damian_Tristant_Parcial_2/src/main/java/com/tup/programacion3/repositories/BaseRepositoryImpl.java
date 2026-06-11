package com.tup.programacion3.repositories;

import jakarta.persistence.EntityManager;
import java.util.List;
public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {
    protected EntityManager em;
    private final Class<T> claseEntidad;

    public BaseRepositoryImpl(EntityManager em, Class<T> claseEntidad) {
        this.em = em;
        this.claseEntidad = claseEntidad;
    }

    @Override
    public void guardar(T entidad) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(entidad);
        em.getTransaction().commit();
    }

    @Override
    public T buscarPorId(Long id) {
        T entidad = em.find(claseEntidad, id);
        // Si la entidad está marcada como eliminada lógica, no la exponemos
        if (entidad != null) {
            try {
                // Usamos reflexión para chequear el método isEliminado de la clase Base
                boolean eliminado = (boolean) entidad.getClass().getMethod("isEliminado").invoke(entidad);
                if (eliminado) return null;
            } catch (Exception e) {
                // Si no tiene el método, se retorna igual
            }
        }
        return entidad;
    }

    @Override
    public List<T> listarActivos() {
        // Consulta dinámica usando el nombre de la clase actual (solo activos: eliminado = false)
        String jpql = "SELECT e FROM " + claseEntidad.getSimpleName() + " e WHERE e.eliminado = false";
        return em.createQuery(jpql, claseEntidad).getResultList();
    }

    @Override
    public void eliminarLogico(Long id) {
        T entidad = em.find(claseEntidad, id);
        if (entidad != null) {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            try {
                // Invocamos el setter para hacer la baja lógica de forma genérica
                entidad.getClass().getMethod("setEliminado", boolean.class).invoke(entidad, true);
                em.merge(entidad);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw new RuntimeException("Error al ejecutar la baja lógica", e);
            }
        } else {
            throw new IllegalArgumentException("El ID provisto no existe en el sistema.");
        }
    }
}
