package com.tup.programacion3;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    // Definimos una única factoría para toda la aplicación
    private static final EntityManagerFactory emf;

    static {
        try {
            // Debe coincidir exactamente con el name de tu persistence.xml
            emf = Persistence.createEntityManagerFactory("miUnidad");
        } catch (Throwable ex) {
            System.err.println("Error al inicializar el EntityManagerFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Método global para obtener la factoría
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    // Método para cerrar la factoría cuando se apague el sistema
    public static void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}