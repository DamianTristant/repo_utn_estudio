package com.tup.programacion3;

import com.tup.programacion3.entities.*;
import com.tup.programacion3.enums.*;
import com.tup.programacion3.dtos.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== ARRANCANDO PRUEBAS DEL TP: LOMBOK Y DTO ===");

        // 1. INSTANCIAR 3 CATEGORIAS (Usando SuperBuilder)
        Categoria catHerramientas = Categoria.builder().id(1L).nombre("Herramientas").descripcion("Herramientas de mano").build();
        Categoria catMateriales = Categoria.builder().id(2L).nombre("Materiales").descripcion("Insumos de construcción").build();
        Categoria catSeguridad = Categoria.builder().id(3L).nombre("Seguridad").descripcion("Elementos de protección EPP").build();

        // 2. INSTANCIAR 10 PRODUCTOS (Usando SuperBuilder)
        Producto p1 = Producto.builder().id(101L).nombre("Martillo").precio(150.0).descripcion("Acero forjado").stock(50).disponible(true).build();
        Producto p2 = Producto.builder().id(102L).nombre("Serrucho").precio(280.0).descripcion("Corte madera").stock(20).disponible(true).build();
        Producto p3 = Producto.builder().id(103L).nombre("Formón").precio(120.0).descripcion("Carpintería").stock(15).disponible(true).build();
        Producto p4 = Producto.builder().id(104L).nombre("Escuadra").precio(200.0).descripcion("Metálica").stock(30).disponible(true).build();
        Producto p5 = Producto.builder().id(105L).nombre("Lijadora").precio(1200.0).descripcion("Eléctrica").stock(8).disponible(true).build();
        Producto p6 = Producto.builder().id(106L).nombre("Tornillos (x100)").precio(45.0).descripcion("Fix para madera").stock(200).disponible(true).build();
        Producto p7 = Producto.builder().id(107L).nombre("Cola Vinílica").precio(85.0).descripcion("Pegamento madera").stock(40).disponible(true).build();
        Producto p8 = Producto.builder().id(108L).nombre("Laca 1L").precio(320.0).descripcion("Poliuretánica").stock(25).disponible(true).build();
        Producto p9 = Producto.builder().id(109L).nombre("Guantes").precio(90.0).descripcion("Protección de vaqueta").stock(100).disponible(true).build();
        Producto p10 = Producto.builder().id(110L).nombre("Gafas").precio(75.0).descripcion("Policarbonato").stock(80).disponible(true).build();

        // Guardamos los productos en una lista para mostrar el listado completo
        List<Producto> listaProductos = List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);

        // 3. INSTANCIAR 2 USUARIOS (Usando SuperBuilder)
        Usuario user1 = Usuario.builder()
                .id(1L).nombre("Damián").apellido("Tristant").mail("damian@taller.com")
                .celular("12345678").contrasenia("secreta123").rol(Rol.USUARIO)
                .build();

        Usuario user2 = Usuario.builder()
                .id(2L).nombre("Pedro").apellido("Gomez").mail("pedro@correo.com")
                .celular("87654321").contrasenia("admin2026").rol(Rol.ADMIN)
                .build();

        // 4. INSTANCIAR 3 PEDIDOS (Con al menos 2 detalles y cada uno usando el Builder interno)
        Pedido pedido1 = Pedido.builder().id(501L).formaPago(FormaPago.EFECTIVO).build();
        pedido1.addDetallePedido(2, p1); // 2 Martillos
        pedido1.addDetallePedido(3, p7); // 3 Colas Vinílicas

        Pedido pedido2 = Pedido.builder().id(502L).formaPago(FormaPago.TARJETA).build();
        pedido2.addDetallePedido(1, p5); // 1 Lijadora
        pedido2.addDetallePedido(5, p6); // 5 Cajas de tornillos

        Pedido pedido3 = Pedido.builder().id(503L).formaPago(FormaPago.TRANSFERENCIA).build();
        pedido3.addDetallePedido(2, p9);  // 2 Guantes
        pedido3.addDetallePedido(2, p10); // 2 Gafas

        // Vinculamos los pedidos a los usuarios correspondientes
        user1.addPedido(pedido1);
        user1.addPedido(pedido2); // user1 tiene 2 pedidos (Es el que más tiene)
        user2.addPedido(pedido3); // user2 tiene 1 pedido

        // EJECUCIÓN DE LAS VERIFICACIONES SEGUN ENUNCIADO
        // PUNTO 4: Mostrar un producto aislado y la lista completa usando toString de Lombok
        System.out.println("\n--- [PUNTO 4] MOSTRANDO UN PRODUCTO AISLADO ---");
        System.out.println(p1);

        System.out.println("\n--- [PUNTO 4] LISTADO DE TODOS LOS PRODUCTOS CARGADOS ---");
        for (Producto prod : listaProductos) {
            System.out.println(" -> " + prod);
        }

        // PUNTO 4: Mostrar los pedidos del usuario con mayor cantidad de pedidos (que deberia ser el user 1)
        System.out.println("\n--- [PUNTO 4] PEDIDOS DEL USUARIO CON MAS COMPRAS (" + user1.getNombre() + ") ---");
        for (Pedido ped : user1.getPedidos()) {
            System.out.println(" -> " + ped);
            System.out.println("    Detalles de este pedido:");
            for (DetallePedido det : ped.getDetalles()) {
                System.out.println("       * " + det);
            }
        }

        // PUNTO 5: Probar la sobreescritura de Equals de Lombok con un producto clonado por nombre
        System.out.println("\n--- [PUNTO 5] PRUEBA DEL MÉTODO EQUALS (LOMBOK) ---");
        // Creamos un producto con ID diferente pero con el mismo nombre "Martillo"
        Producto productoClonado = Producto.builder().id(999L).nombre("Martillo").precio(9999.9).build();
        System.out.println("Instancia nueva a comparar: " + productoClonado);

        System.out.println("Comparando contra la colección de productos existentes:");
        for (Producto prod : listaProductos) {
            if (prod.equals(productoClonado)) {
                System.out.println(" [¡COINCIDENCIA ENCONTRADA!] '" + productoClonado.getNombre() + "' (ID " + productoClonado.getId() + ") es igual a: " + prod);
            }
        }

        // PUNTO 6: Demostracion del uso de Record y DTO para ocultar informacion sensible
        System.out.println("\n--- [PUNTO 6] USO DE DTO (OCULTANDO CONTRASEÑA Y ROL) ---");
        System.out.println("Entidad Usuario Original (Con datos sensibles expuestos):");
        System.out.println(user1);

        // Transformamos el usuario en un Record DTO inmutable
        UsuarioDTO clienteDTO = user1.toDTO();
        System.out.println("\nObjeto UsuarioDTO Transmitido (Seguro):");
        System.out.println(clienteDTO);
        System.out.println("Probando lectura individual desde el Record -> Nombre: " + clienteDTO.nombre() + ", Mail: " + clienteDTO.mail());

    }
}
