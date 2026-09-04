package com.tup.programacion3;

import com.tup.programacion3.entities.*;
import com.tup.programacion3.repositories.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // Inicializamos los repositorios usando los constructores limpios
    private static final BaseRepository<Categoria> categoriaRepo = new CategoriaRepositoryImpl();
    private static final ProductoRepository productoRepo = new ProductoRepositoryImpl();

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN INICIALIZADO CON JPA ===");
        int opcion = 0;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarCategoria();
                case 2 -> listarCategorias();
                case 3 -> buscarCategoriaPorId();
                case 4 -> darBajaCategoria();
                case 5 -> registrarProducto();
                case 6 -> listarProductos();
                case 7 -> buscarProductoPorId();
                case 8 -> darBajaProducto();
                case 9 -> filtrarProductosPorCategoriaJPQL();
                case 10 -> {
                    System.out.println("\nCerrando el EntityManagerFactory de forma segura...");
                    JPAUtil.shutdown(); // Cerramos la factoría global al salir
                    System.out.println("Sistema apagado correctamente. ¡Hasta luego!");
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 10);
    }

    private static void mostrarMenu() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("               MENÚ DE OPCIONES (PARCIAL 2)       ");
        System.out.println("--------------------------------------------------");
        System.out.println("1. Registrar nueva Categoría");
        System.out.println("2. Listar Categorías activas");
        System.out.println("3. Buscar Categoría por ID");
        System.out.println("4. Dar de baja una Categoría (Lógica)");
        System.out.println("5. Registrar nuevo Producto");
        System.out.println("6. Listar Productos activos");
        System.out.println("7. Buscar Producto por ID");
        System.out.println("8. Dar de baja un Producto (Lógica)");
        System.out.println("9. Filtrar Productos por Categoría (JPQL - HU-09)");
        System.out.println("10. Salir del sistema");
        System.out.println("--------------------------------------------------");
    }

    // === MÓDULO CATEGORÍAS ===

    private static void registrarCategoria() {
        System.out.println("\n--- Registrar nueva Categoría ---");
        String nombre = leerStringObligatorio("Nombre de la categoría: ");
        String descripcion = leerStringObligatorio("Descripción: ");

        Categoria cat = Categoria.builder().nombre(nombre).descripcion(descripcion).build();
        categoriaRepo.guardar(cat);
        System.out.println("¡Categoría guardada con éxito!");
    }

    private static void listarCategorias() {
        System.out.println("\n--- Listado de Categorías Activas ---");
        List<Categoria> lista = categoriaRepo.listarActivos();
        if (lista.isEmpty()) {10
            System.out.println("No hay categorías activas registradas.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void buscarCategoriaPorId() {
        System.out.println("\n--- Buscar Categoría por ID ---");
        Long id = (long) leerEntero("Ingrese el ID a buscar: ");
        // Recomendación 4: Manejo correcto del Optional
        Optional<Categoria> op = categoriaRepo.buscarPorId(id);
        if (op.isPresent() && !op.get().isEliminado()) {
            System.out.println("Encontrada: " + op.get());
        } else {
            System.out.println("Categoría no encontrada o dada de baja.");
        }
    }

    private static void darBajaCategoria() {
        System.out.println("\n--- Dar de baja Categoría ---");
        Long id = (long) leerEntero("Ingrese el ID de la categoría a desactivar: ");
        // Recomendación 4: Evaluamos el retorno boolean
        if (categoriaRepo.eliminarLogico(id)) {
            System.out.println("¡Categoría desactivada con éxito (Baja Lógica)!");
        } else {
            System.out.println("Error: No se encontró ninguna categoría activa con ese ID.");
        }
    }

    // === MÓDULO PRODUCTOS ===

    private static void registrarProducto() {
        System.out.println("\n--- Registrar nuevo Producto ---");
        List<Categoria> categorias = categoriaRepo.listarActivos();
        if (categorias.isEmpty()) {
            System.out.println("Error: Debe registrar al menos una categoría antes de crear un producto.");
            return;
        }

        System.out.println("Categorías disponibles:");
        categorias.forEach(c -> System.out.println("ID: " + c.getId() + " - " + c.getNombre()));

        Long catId = (long) leerEntero("Seleccione el ID de la categoría para el producto: ");
        Optional<Categoria> catOp = categoriaRepo.buscarPorId(catId);

        if (catOp.isEmpty() || catOp.get().isEliminado()) {
            System.out.println("Categoría inválida o inexistente.");
            return;
        }

        String nombre = leerStringObligatorio("Nombre del producto: ");
        Double precio = leerDoublePositivo("Precio: ");
        String descripcion = leerStringObligatorio("Descripción: ");
        int stock = leerEnteroPositivo("Stock inicial: ");

        Producto prod = Producto.builder()
                .nombre(nombre)
                .precio(precio)
                .descripcion(descripcion)
                .stock(stock)
                .disponible(true)
                .build();

        // Guardamos el producto y actualizamos la relación en la categoría
        productoRepo.guardar(prod);
        Categoria categoria = catOp.get();
        categoria.addProducto(prod);
        categoriaRepo.guardar(categoria); // El merge actualizará la tabla intermedia o relación

        System.out.println("¡Producto registrado y asociado con éxito!");
    }

    private static void listarProductos() {
        System.out.println("\n--- Listado de Productos Activos ---");
        List<Producto> lista = productoRepo.listarActivos();
        if (lista.isEmpty()) {
            System.out.println("No hay productos activos en el catálogo.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void buscarProductoPorId() {
        System.out.println("\n--- Buscar Producto por ID ---");
        Long id = (long) leerEntero("Ingrese el ID del producto: ");
        Optional<Producto> op = productoRepo.buscarPorId(id);
        if (op.isPresent() && !op.get().getDisponible()) { // Ajustado según tu bandera disponible/eliminado
            System.out.println("Encontrado: " + op.get());
        } else if (op.isPresent()) {
            System.out.println("Encontrado: " + op.get());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void darBajaProducto() {
        System.out.println("\n--- Dar de baja Producto ---");
        Long id = (long) leerEntero("Ingrese el ID del producto a desactivar: ");
        if (productoRepo.eliminarLogico(id)) {
            System.out.println("¡Producto dado de baja con éxito!");
        } else {
            System.out.println("Error: No se encontró ningún producto activo con ese ID.");
        }
    }

    private static void filtrarProductosPorCategoriaJPQL() {
        System.out.println("\n--- Filtrar Productos por Categoría (HU-09 - JPQL) ---");
        Long catId = (long) leerEntero("Ingrese el ID de la categoría: ");
        List<Producto> filtrados = productoRepo.filtrarPorCategoria(catId);

        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron productos activos asociados a esa categoría.");
        } else {
            System.out.println("Productos encontrados:");
            filtrados.forEach(System.out::println);
        }
    }

    // === MÉTODOS DE VALIDACIÓN Y ROBUSTEZ (Recomendación 3) ===

    private static String leerStringObligatorio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("Error: El campo no puede quedar vacío.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int num = Integer.parseInt(scanner.nextLine().trim());
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número entero válido.");
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        while (true) {
            int num = leerEntero(mensaje);
            if (num >= 0) return num;
            System.out.println("Error: El número debe ser cero o positivo.");
        }
    }

    private static Double leerDoublePositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                Double num = Double.parseDouble(scanner.nextLine().trim());
                if (num >= 0) return num;
                System.out.println("Error: El precio debe ser un número positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido (Ej: 1500.50).");
            }
        }
    }
}
