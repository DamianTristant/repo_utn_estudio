package com.tup.programacion3;

import com.tup.programacion3.entities.Categoria;
import com.tup.programacion3.entities.Producto;
import com.tup.programacion3.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
        EntityManager em = emf.createEntityManager();

        // Instanciamos nuestros repositorios profesionales
        CategoriaRepository catRepo = new CategoriaRepositoryImpl(em);
        ProductoRepository prodRepo = new ProductoRepositoryImpl(em);

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        System.out.println("=== SISTEMA DE GESTIÓN DE PEDIDOS: REPOSITORIOS JPA ===");

        do {
            System.out.println("\n------------------------------------------------");
            System.out.println("MÓDULO DE CONTROL: SELECCIONE UNA OPCIÓN");
            System.out.println("1. Registrar nueva Categoría (HU-01)");
            System.out.println("2. Listar Categorías activas (HU-02)");
            System.out.println("3. Modificar una Categoría (HU-03)");
            System.out.println("4. Dar de baja una Categoría [Lógica] (HU-04)");
            System.out.println("5. Registrar nuevo Producto (HU-05)");
            System.out.println("6. Listar Productos activos (HU-06)");
            System.out.println("7. Modificar un Producto (HU-07)");
            System.out.println("8. Dar de baja un Producto [Lógica] (HU-08)");
            System.out.println("9. Filtrar productos por Categoría [JPQL] (HU-09)");
            System.out.println("10. Salir de la aplicación");
            System.out.print("Ingrese opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1: // HU-01: Alta Categoría
                    System.out.print("Ingrese nombre de la categoría: ");
                    String nomCat = scanner.nextLine();
                    System.out.print("Ingrese descripción de la categoría: ");
                    String descCat = scanner.nextLine();

                    Categoria nuevaCat = Categoria.builder().nombre(nomCat).descripcion(descCat).build();
                    catRepo.guardar(nuevaCat);
                    System.out.println("[ÉXITO] Categoría guardada con ID asignado: " + nuevaCat.getId());
                    break;

                case 2: // HU-02: Listar Categorías
                    System.out.println("\n--- LISTADO DE CATEGORÍAS ACTIVAS ---");
                    List<Categoria> categorias = catRepo.listarActivos();
                    if (categorias.isEmpty()) {
                        System.out.println("No hay categorías activas registradas.");
                    } else {
                        categorias.forEach(c -> System.out.println("ID: " + c.getId() + " | Nombre: " + c.getNombre() + " | Desc: " + c.getDescripcion()));
                    }
                    break;

                case 3: // HU-03: Modificar Categoría
                    System.out.print("Ingrese el ID de la categoría a modificar: ");
                    Long idCatMod = Long.parseLong(scanner.nextLine());
                    Categoria catMod = catRepo.buscarPorId(idCatMod);
                    if (catMod == null) {
                        System.out.println("[ERROR] La categoría solicitada no existe o está dada de baja.");
                    } else {
                        System.out.print("Nuevo nombre (actual: " + catMod.getNombre() + "): ");
                        catMod.setNombre(scanner.nextLine());
                        System.out.print("Nueva descripción (actual: " + catMod.getDescripcion() + "): ");
                        catMod.setDescripcion(scanner.nextLine());
                        catRepo.guardar(catMod);
                        System.out.println("[ÉXITO] Categoría actualizada correctamente.");
                    }
                    break;

                case 4: // HU-04: Baja lógica Categoría
                    System.out.print("Ingrese el ID de la categoría a eliminar: ");
                    Long idCatBaja = Long.parseLong(scanner.nextLine());
                    try {
                        catRepo.eliminarLogico(idCatBaja);
                        System.out.println("[ÉXITO] La categoría fue dada de baja lógicamente de forma correcta.");
                    } catch (Exception e) {
                        System.out.println("[ERROR] " + e.getMessage());
                    }
                    break;

                case 5: // HU-05: Alta Producto
                    System.out.println("\nCategorías disponibles para asociar el producto:");
                    catRepo.listarActivos().forEach(c -> System.out.println(" -> ID: " + c.getId() + " | " + c.getNombre()));
                    System.out.print("Seleccione ID de Categoría: ");
                    Long idCatProd = Long.parseLong(scanner.nextLine());
                    Categoria catAsociada = catRepo.buscarPorId(idCatProd);

                    if (catAsociada == null) {
                        System.out.println("[ERROR] Categoría no válida. Operación cancelada.");
                        break;
                    }

                    System.out.print("Nombre del producto: ");
                    String nomProd = scanner.nextLine();
                    System.out.print("Precio: ");
                    Double precioProd = Double.parseDouble(scanner.nextLine());
                    System.out.print("Descripción: ");
                    String descProd = scanner.nextLine();
                    System.out.print("Stock inicial: ");
                    int stockProd = Integer.parseInt(scanner.nextLine());

                    Producto nuevoProd = Producto.builder()
                            .nombre(nomProd).precio(precioProd)
                            .descripcion(descProd).stock(stockProd)
                            .disponible(true).build();

                    // Al añadir el producto a la lista de la categoría, JPA asocia la clave foránea en la BD
                    catAsociada.addProducto(nuevoProd);
                    catRepo.guardar(catAsociada);
                    System.out.println("[ÉXITO] Producto añadido correctamente al catálogo.");
                    break;

                case 6: // HU-06: Listar Productos
                    System.out.println("\n--- CATALOGO DE PRODUCTOS ACTIVAS ---");
                    List<Producto> productos = prodRepo.listarActivos();
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos activos en el sistema.");
                    } else {
                        productos.forEach(p -> System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombre() + " | Precio: $" + p.getPrecio() + " | Stock: " + p.getStock()));
                    }
                    break;

                case 7: // HU-07: Modificar Producto
                    System.out.print("Ingrese el ID del producto a modificar: ");
                    Long idProdMod = Long.parseLong(scanner.nextLine());
                    Producto prodMod = prodRepo.buscarPorId(idProdMod);
                    if (prodMod == null) {
                        System.out.println("[ERROR] El producto no existe o está inactivo.");
                    } else {
                        System.out.print("Nuevo nombre (actual: " + prodMod.getNombre() + "): ");
                        prodMod.setNombre(scanner.nextLine());
                        System.out.print("Nuevo precio (actual: $" + prodMod.getPrecio() + "): ");
                        prodMod.setPrecio(Double.parseDouble(scanner.nextLine()));
                        System.out.print("Nuevo stock (actual: " + prodMod.getStock() + "): ");
                        prodMod.setStock(Integer.parseInt(scanner.nextLine()));
                        prodRepo.guardar(prodMod);
                        System.out.println("[ÉXITO] Producto actualizado.");
                    }
                    break;

                case 8: // HU-08: Baja lógica Producto
                    System.out.print("Ingrese el ID del producto a dar de baja: ");
                    Long idProdBaja = Long.parseLong(scanner.nextLine());
                    try {
                        prodRepo.eliminarLogico(idProdBaja);
                        System.out.println("[ÉXITO] El producto fue retirado del catálogo activo.");
                    } catch (Exception e) {
                        System.out.println("[ERROR] " + e.getMessage());
                    }
                    break;

                case 9: // HU-09: Consulta JPQL Personalizada
                    System.out.println("\nCategorías registradas:");
                    catRepo.listarActivos().forEach(c -> System.out.println(" -> ID: " + c.getId() + " | " + c.getNombre()));
                    System.out.print("Seleccione el ID de la categoría para filtrar productos: ");
                    Long idCatFiltro = Long.parseLong(scanner.nextLine());

                    List<Producto> prodFiltrados = prodRepo.buscarPorCategoria(idCatFiltro);
                    System.out.println("\n--- RESULTADO CONSULTA JPQL (PRODUCTOS DE LA CATEGORÍA) ---");
                    if (prodFiltrados.isEmpty()) {
                        System.out.println("La categoría seleccionada no posee productos activos asociados.");
                    } else {
                        prodFiltrados.forEach(p -> System.out.println(" -> [PRODUCTO] ID: " + p.getId() + " | Nombre: " + p.getNombre() + " | Precio: $" + p.getPrecio() + " | Stock: " + p.getStock()));
                    }
                    break;

                case 10:
                    System.out.println("Cerrando sesión y apagando sistema...");
                    break;

                default:
                    System.out.println("Opción incorrecta. Intente nuevamente.");
                    break;
            }
        } while (opcion != 10);

        em.close();
        emf.close();
        scanner.close();
        System.out.println("=== SISTEMA APAGADO DE FORMA CORRECTA ===");
    }
}
