package com.tup.programacion3;

import com.tup.programacion3.entities.*;
import com.tup.programacion3.enums.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DEL TP: PROGRAMACIÓN FUNCIONAL ===\n");

        // 1. INSTANCIAR CATEGORIAS (Usando SuperBuilder)
        Categoria catHerramientas = Categoria.builder().id(1L).nombre("Herramientas").descripcion("Herramientas de mano").build();
        Categoria catMateriales = Categoria.builder().id(2L).nombre("Materiales").descripcion("Insumos de construcción").build();

        // 2. INSTANCIAR 10 PRODUCTOS (Modificamos algunos stocks y disponibilidades para probar las funciones)
        Producto p1 = Producto.builder().id(101L).nombre("Martillo").precio(150.0).descripcion("Acero forjado").stock(50).disponible(true).build();
        Producto p2 = Producto.builder().id(102L).nombre("Serrucho").precio(280.0).descripcion("Corte madera").stock(3).disponible(true).build(); // STOCK BAJO (< 5)
        Producto p3 = Producto.builder().id(103L).nombre("Formón").precio(120.0).descripcion("Carpintería").stock(15).disponible(false).build(); // NO DISPONIBLE
        Producto p4 = Producto.builder().id(104L).nombre("Escuadra").precio(200.0).descripcion("Metálica").stock(30).disponible(true).build();
        Producto p5 = Producto.builder().id(105L).nombre("Lijadora").precio(1200.0).descripcion("Eléctrica").stock(2).disponible(true).build();  // STOCK BAJO (< 5)
        Producto p6 = Producto.builder().id(106L).nombre("Tornillos (x100)").precio(45.0).descripcion("Fix para madera").stock(200).disponible(true).build();
        Producto p7 = Producto.builder().id(107L).nombre("Cola Vinílica").precio(85.0).descripcion("Pegamento madera").stock(40).disponible(true).build();
        Producto p8 = Producto.builder().id(108L).nombre("Laca 1L").precio(320.0).descripcion("Poliuretánica").stock(25).disponible(true).build();
        Producto p9 = Producto.builder().id(109L).nombre("Guantes").precio(90.0).descripcion("Protección de vaqueta").stock(100).disponible(false).build(); // NO DISPONIBLE
        Producto p10 = Producto.builder().id(110L).nombre("Gafas").precio(75.0).descripcion("Policarbonato").stock(4).disponible(true).build();   // STOCK BAJO (< 5)

        // Guardamos los productos en una lista para procesarlos con Streams
        List<Producto> listaProductos = List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);

        // 3. INSTANCIAR USUARIO
        Usuario user = Usuario.builder()
                .id(1L).nombre("Damián").apellido("Tristant").mail("damian@taller.com")
                .celular("12345678").contrasenia("secreta123").rol(Rol.USUARIO)
                .build();

        // 4. INSTANCIAR PEDIDO Y AGREGAR DETALLES
        Pedido pedido1 = Pedido.builder().id(501L).formaPago(FormaPago.EFECTIVO).build();
        pedido1.addDetallePedido(3, p1); // 3 Martillos
        pedido1.addDetallePedido(4, p6); // 4 Cajas de tornillos
        pedido1.addDetallePedido(2, p7); // 2 Colas Vinílicas

        user.addPedido(pedido1);

        // =========================================================================
        // RESOLUCIÓN DE LOS PUNTOS CON PROGRAMACIÓN FUNCIONAL (STREAMS)
        // =========================================================================

        // PUNTO 2: Mostrar por consola productos disponibles
        System.out.println("--- [PUNTO 2] PRODUCTOS DISPONIBLES ---");
        listaProductos.stream()
                .filter(Producto::getDisponible) // Filtra dejando pasar solo los que tienen disponible == true
                .forEach(prod -> System.out.println(" -> " + prod.getNombre() + " ($" + prod.getPrecio() + ")"));

        // PUNTO 3: Cantidad total de ítems que tiene un pedido (Suma de las cantidades)
        System.out.println("\n--- [PUNTO 3] CANTIDAD TOTAL DE ITEMS EN EL PEDIDO #" + pedido1.getId() + " ---");
        int totalItems = pedido1.getDetalles().stream()
                .mapToInt(DetallePedido::getCantidad) // Extrae la cantidad entera de cada línea de detalle
                .sum();                               // Suma de forma declarativa

        System.out.println("El pedido tiene un total de: " + totalItems + " ítems.");
        System.out.println("Monto total calculado con Stream en entidad: $" + pedido1.getTotal());

        // PUNTO 4: Detectar productos que tengan menos de 5 como valor en stock
        System.out.println("\n--- [PUNTO 4] ALERTA: PRODUCTOS CON BAJO STOCK (MENOS DE 5 UNIDADES) ---");
        listaProductos.stream()
                .filter(prod -> prod.getStock() < 5) // Filtra los productos cuyo stock es estrictamente menor a 5
                .forEach(prod -> System.out.println(" -> [¡STOCK BAJO!] Producto: " + prod.getNombre() + " | Stock actual: " + prod.getStock()));
    }
}
