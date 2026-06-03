package com.tup.programacion3;

import com.tup.programacion3.entities.*;
import com.tup.programacion3.enums.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ARRANCANDO PRUEBAS DEL TP - COLECCIONES ===");

        // 1. CREACIÓN DE CATEGORIAS
        Categoria catHerramientas = new Categoria(1L, "Herramientas", "Herramientas de mano y taller");
        Categoria catMateriales = new Categoria(2L, "Materiales", "Insumos y materiales de construcción");

        // 2. CREACIÓN DE PRODUCTOS (Cargamos los artículos de prueba)
        Producto p1 = new Producto(101L, "Martillo Galponero", 150.0, "Martillo de acero forjado", 50, "martillo.jpg", true);
        Producto p2 = new Producto(102L, "Serrucho de Costilla", 280.0, "Serrucho profesional para madera", 20, "serrucho.jpg", true);
        Producto p3 = new Producto(103L, "Formón 1 Pulgada", 120.0, "Formón para carpintería", 15, "formon.jpg", true);
        Producto p4 = new Producto(104L, "Escuadra de Combinación", 200.0, "Escuadra metálica regulable", 30, "escuadra.jpg", true);
        Producto p5 = new Producto(105L, "Lijadora Orbital", 1200.0, "Lijadora eléctrica de palma", 8, "lijadora.jpg", true);

        Producto p6 = new Producto(106L, "Tornillos Fix 4x40 (x100)", 45.0, "Tornillos para madera", 200, "tornillos.jpg", true);
        Producto p7 = new Producto(107L, "Cola Vinílica 1Kg", 85.0, "Pegamento para madera de alta resistencia", 40, "cola.jpg", true);
        Producto p8 = new Producto(108L, "Laca Poliuretánica 1L", 320.0, "Laca brillante para protección", 25, "laca.jpg", true);
        Producto p9 = new Producto(109L, "Tarugos de Madera 8mm (x50)", 15.0, "Tarugos estriados", 500, "tarugos.jpg", true);
        Producto p10 = new Producto(110L, "Tinta para Madera Nogal", 60.0, "Tinta universal concentrada", 60, "tinta.jpg", true);

        // Asociamos los productos a sus categorias
        catHerramientas.addProducto(p1);
        catHerramientas.addProducto(p2);
        catHerramientas.addProducto(p3);
        catHerramientas.addProducto(p4);
        catHerramientas.addProducto(p5);

        catMateriales.addProducto(p6);
        catMateriales.addProducto(p7);
        catMateriales.addProducto(p8);
        catMateriales.addProducto(p9);
        catMateriales.addProducto(p10);

        // 3. CREACIÓN DEL USUARIO (CLIENTE)
        Usuario cliente = new Usuario(1L, "damian_carpintero", "123456", "damian@taller.com", Rol.USUARIO);

        // 4. CREACIÓN DEL PEDIDO
        Pedido pedido1 = new Pedido(5001L, FormaPago.EFECTIVO);

        // Agregamos ítems al pedido usando el método logico
        System.out.println("\n--- Agregando productos al pedido ---");
        pedido1.addDetallePedido(2, p1);  // 2 Martillos ($150 c/u = $300)
        pedido1.addDetallePedido(1, p2);  // 1 Serrucho ($280 c/u = $280)
        pedido1.addDetallePedido(3, p7);  // 3 Colas Vinílicas ($85 c/u = $255)
        pedido1.addDetallePedido(5, p9);  // 5 Paquetes de tarugos ($15 c/u = $75)

        // Vinculamos el pedido al usuario
        cliente.addPedido(pedido1);

        // Mostramos el estado inicial del pedido (con el toString)
        System.out.println(cliente);
        System.out.println(pedido1);
        System.out.println("Detalle de los artículos en el carrito:");
        for (DetallePedido detalle : pedido1.getDetalles()) {
            System.out.println(" -> " + detalle);
        }

        // 5. PROBAMOS LA BUSQUEDA EN LA COLECCIÓN
        System.out.println("\n--- Probando método Buscar Detalle por Producto ---");
        DetallePedido detalleBuscado = pedido1.findDetallePedidoByProducto(p2); // Buscamos el serrucho
        if (detalleBuscado != null) {
            System.out.println("¡Encontrado!: " + detalleBuscado);
        } else {
            System.out.println("El producto no está en el pedido.");
        }

        // 6. PROBAMOS LA ELIMINACIÓN DE UN ELEMENTO DEL SET
        System.out.println("\n--- Probando método Eliminar Detalle por Producto ---");
        System.out.println("Eliminando 'Serrucho de Costilla' del pedido...");
        pedido1.deleteDetallePedidoByProducto(p2); // Sacamos el serrucho

        // Mostramos cómo quedó el pedido recalculado automáticamente
        System.out.println("\n--- Estado final del pedido después de la eliminación ---");
        System.out.println(pedido1);
        System.out.println("Artículos que quedaron en el carrito:");
        for (DetallePedido detalle : pedido1.getDetalles()) {
            System.out.println(" -> " + detalle);
        }
    }
}
