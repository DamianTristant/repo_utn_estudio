package com.tup.programacion3;

import com.tup.programacion3.entities.*;
import com.tup.programacion3.enums.*;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBAS DEL TP: PERSISTENCIA CON JPA ===");

        // 1. Inicializamos la fábrica de conexiones de JPA (usa el nombre de unidad definido en persistence.xml)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciamos una transacción para guardar los datos iniciales
            em.getTransaction().begin();

            // =========================================================================
            // PUNTO 4: INSTANCIAR Y PERSISTIR DATOS
            // =========================================================================

            // a. Crear 3 Categorías
            Categoria catHerramientas = Categoria.builder().nombre("Herramientas").descripcion("Herramientas de mano").build();
            Categoria catMateriales = Categoria.builder().nombre("Materiales").descripcion("Insumos de construcción").build();
            Categoria catSeguridad = Categoria.builder().nombre("Seguridad").descripcion("Elementos de protección EPP").build();

            // b. Crear 10 Productos
            Producto p1 = Producto.builder().nombre("Martillo").precio(150.0).descripcion("Acero forjado").stock(50).disponible(true).build();
            Producto p2 = Producto.builder().nombre("Serrucho").precio(280.0).descripcion("Corte madera").stock(20).disponible(true).build();
            Producto p3 = Producto.builder().nombre("Formón").precio(120.0).descripcion("Carpintería").stock(15).disponible(true).build();
            Producto p4 = Producto.builder().nombre("Escuadra").precio(200.0).descripcion("Metálica").stock(30).disponible(true).build();
            Producto p5 = Producto.builder().nombre("Lijadora").precio(1200.0).descripcion("Eléctrica").stock(8).disponible(true).build();
            Producto p6 = Producto.builder().nombre("Tornillos (x100)").precio(45.0).descripcion("Fix para madera").stock(200).disponible(true).build();
            Producto p7 = Producto.builder().nombre("Cola Vinílica").precio(85.0).descripcion("Pegamento madera").stock(40).disponible(true).build();
            Producto p8 = Producto.builder().nombre("Laca 1L").precio(320.0).descripcion("Poliuretánica").stock(25).disponible(true).build();
            Producto p9 = Producto.builder().nombre("Guantes").precio(90.0).descripcion("Protección de vaqueta").stock(100).disponible(true).build();
            Producto p10 = Producto.builder().nombre("Gafas").precio(75.0).descripcion("Policarbonato").stock(80).disponible(true).build();

            // Asociamos los productos a sus categorías
            catHerramientas.addProducto(p1); catHerramientas.addProducto(p2); catHerramientas.addProducto(p3); catHerramientas.addProducto(p4); catHerramientas.addProducto(p5);
            catMateriales.addProducto(p6); catMateriales.addProducto(p7); catMateriales.addProducto(p8);
            catSeguridad.addProducto(p9); catSeguridad.addProducto(p10);

            // Persistimos las categorías (al tener CascadeType.PERSIST, guarda los productos de adentro automáticamente)
            em.persist(catHerramientas);
            em.persist(catMateriales);
            em.persist(catSeguridad);

            // c. Crear 2 Usuarios
            Usuario user1 = Usuario.builder()
                    .nombre("Damián").apellido("Tristant").mail("damian@taller.com")
                    .celular("12345678").contrasenia("secreta123").rol(Rol.USUARIO)
                    .build();

            Usuario user2 = Usuario.builder()
                    .nombre("Pedro").apellido("Gomez").mail("pedro@correo.com")
                    .celular("87654321").contrasenia("admin2026").rol(Rol.ADMIN)
                    .build();

            // d. Crear 3 Pedidos (con al menos 2 detalles pedido por cada uno)
            Pedido pedido1 = Pedido.builder().formaPago(FormaPago.EFECTIVO).build();
            pedido1.addDetallePedido(2, p1); // 2 Martillos
            pedido1.addDetallePedido(3, p7); // 3 Colas Vinílicas

            Pedido pedido2 = Pedido.builder().formaPago(FormaPago.TARJETA).build();
            pedido2.addDetallePedido(1, p5); // 1 Lijadora
            pedido2.addDetallePedido(5, p6); // 5 Cajas de tornillos

            Pedido pedido3 = Pedido.builder().formaPago(FormaPago.TRANSFERENCIA).build();
            pedido3.addDetallePedido(2, p9);  // 2 Guantes
            pedido3.addDetallePedido(2, p10); // 2 Gafas

            // Vinculamos los pedidos a los usuarios
            user1.addPedido(pedido1);
            user1.addPedido(pedido2);
            user2.addPedido(pedido3);

            // Persistimos los usuarios (gracias a CascadeType.ALL se guardan los pedidos y detalles en cascada)
            em.persist(user1);
            em.persist(user2);

            // Confirmamos y guardamos todo de forma efectiva en el archivo H2
            em.getTransaction().commit();
            System.out.println("\n>>> [PUNTO 4] ¡Datos iniciales guardados con éxito en H2!");

            // =========================================================================
            // PUNTO 5: ACTUALIZAR AL MENOS 2 PRODUCTOS
            // =========================================================================
            em.getTransaction().begin();

            // Traemos el producto Martillo usando su ID asignado en la BD (1L)
            Producto productoA = em.find(Producto.class, 1L);
            if (productoA != null) {
                productoA.setPrecio(175.50); // Cambiamos el precio por inflación
                productoA.setStock(45);      // Actualizamos el stock
            }

            // Traemos el producto Lijadora (su ID debería ser el 5L)
            Producto productoB = em.find(Producto.class, 5L);
            if (productoB != null) {
                productoB.setPrecio(1350.0); // Actualizamos precio
                productoB.setStock(6);       // Se vendieron un par
            }

            em.getTransaction().commit();
            System.out.println("\n>>> [PUNTO 5] ¡Productos actualizados correctamente en la base de datos!");

            // =========================================================================
            // PUNTO 6 Y 7: BUSCAR USUARIOS
            // =========================================================================
            System.out.println("\n--- [PUNTO 6] BUSCAR USUARIO POR ID (1L) ---");
            Usuario usuarioPorId = em.find(Usuario.class, 1L);
            System.out.println("Usuario Encontrado: " + (usuarioPorId != null ? usuarioPorId.getNombre() + " " + usuarioPorId.getApellido() : "No existe"));

            System.out.println("\n--- [PUNTO 7] BUSCAR USUARIO POR MAIL (pedro@correo.com) ---");
            // Usamos una consulta JPQL para buscar por un atributo específico diferente al ID primary key
            Usuario usuarioPorMail = em.createQuery("SELECT u FROM Usuario u WHERE u.mail = :email", Usuario.class)
                    .setParameter("email", "pedro@correo.com")
                    .getSingleResult();
            System.out.println("Usuario Encontrado: " + usuarioPorMail.getNombre() + " " + usuarioPorMail.getApellido() + " | Rol: " + usuarioPorMail.getRol());

            // =========================================================================
            // PUNTO 8: BORRAR 1 PRODUCTO
            // =========================================================================
            em.getTransaction().begin();

            System.out.println("\n--- [PUNTO 8] BORRANDO EL PRODUCTO 'Formón' (ID 3L) ---");
            Producto productoParaBorrar = em.find(Producto.class, 3L);
            if (productoParaBorrar != null) {
                // Removemos primero la referencia del producto de su categoría para que Hibernate no chille por integridad
                catHerramientas.getProductos().remove(productoParaBorrar);
                em.remove(productoParaBorrar);
            }

            em.getTransaction().commit();
            System.out.println(">>> ¡Producto eliminado de la Base de Datos!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Si algo falla, cancela los cambios para cuidar los datos
            }
            e.printStackTrace();
        } finally {
            // Cerramos de forma ordenada los recursos de persistencia
            em.close();
            emf.close();
            System.out.println("\n=== PRUEBAS CONCLUIDAS Y CONEXIÓN A BD CERRADA ===");
        }
    }
}
