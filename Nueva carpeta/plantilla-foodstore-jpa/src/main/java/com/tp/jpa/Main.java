package com.tp.jpa;

import com.tp.jpa.model.enums.EstadoPedido;
import com.tp.jpa.model.*;
import com.tp.jpa.model.enums.FormaPago;
import com.tp.jpa.model.enums.Rol;
import com.tp.jpa.repository.CategoriaRepository;
import com.tp.jpa.repository.PedidoRepository;
import com.tp.jpa.repository.ProductoRepository;
import com.tp.jpa.repository.UsuarioRepository;
import com.tp.jpa.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

/**
 * Clase principal: menu de consola del sistema Food Store.
 * Orden de uso natural: Categorias -> Productos -> Usuarios -> Pedidos.
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final CategoriaRepository categoriaRepo = new CategoriaRepository();
    private static final ProductoRepository productoRepo = new ProductoRepository();
    private static final UsuarioRepository usuarioRepo = new UsuarioRepository();
    private static final PedidoRepository pedidoRepo = new PedidoRepository();

    public static void main(String[] args) {
        // Ejecutamos la precarga de datos antes de mostrar el menu
        inicializarDatosBase();
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("===== FOOD STORE - menu PRINCIPAL =====");
            System.out.println("1. Gestionar Categorias");
            System.out.println("2. Gestionar Productos");
            System.out.println("3. Gestionar Usuarios");
            System.out.println("4. Gestionar Pedidos");
            System.out.println("5. Reportes");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1": menuCategorias(); break;
                case "2": menuProductos(); break;
                case "3": menuUsuarios(); break;
                case "4": menuPedidos(); break;
                case "5": menuReportes(); break;
                case "0": salir = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
        JPAUtil.close();
        System.out.println("Aplicación finalizada.");
    }

    // ── Submenus ─────────────────────────────────────────────────

    private static void menuCategorias() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTION DE Categorias ---");
            System.out.println("1. Alta de Categoria");
            System.out.println("2. Modificar Categoria");
            System.out.println("3. Baja logica (Eliminar)");
            System.out.println("4. Listado de Categorias");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1":
                    System.out.print("Ingrese el nombre de la nueva Categoria: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Ingrese la descripcion: ");
                    String desc = sc.nextLine().trim();

                    if (nombre.isEmpty()) {
                        System.out.println("⚠️ El nombre no puede estar vacío.");
                        break;
                    }

                    Categoria nueva = Categoria.builder().nombre(nombre).descripcion(desc).build();
                    try {
                        categoriaRepo.guardar(nueva);
                        System.out.println("✅ Categoria guardada con éxito. ID asignado: " + nueva.getId());
                    } catch (Exception e) {
                        System.out.println("⚠️ Error al guardar: Compruebe que el nombre no esté duplicado.");
                    }
                    break;

                case "2":
                    System.out.print("Ingrese el ID de la Categoria a modificar: ");
                    try {
                        Long idMod = Long.parseLong(sc.nextLine().trim());
                        Optional<Categoria> catOpt = categoriaRepo.buscarPorId(idMod);

                        if (catOpt.isPresent() && !catOpt.get().isEliminado()) {
                            Categoria cat = catOpt.get();
                            System.out.print("Nuevo nombre [" + cat.getNombre() + "]: ");
                            String nuevoNom = sc.nextLine().trim();
                            System.out.print("Nueva descripcion [" + cat.getDescripcion() + "]: ");
                            String nuevaDesc = sc.nextLine().trim();

                            if (!nuevoNom.isEmpty()) cat.setNombre(nuevoNom);
                            if (!nuevaDesc.isEmpty()) cat.setDescripcion(nuevaDesc);

                            categoriaRepo.guardar(cat);
                            System.out.println("✅ Categoria actualizada con éxito.");
                        } else {
                            System.out.println("⚠️ Categoria no encontrada o eliminada.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ ID inválido.");
                    }
                    break;

                case "3":
                    System.out.print("Ingrese el ID de la Categoria a eliminar (Baja logica): ");
                    try {
                        Long idBaja = Long.parseLong(sc.nextLine().trim());
                        boolean eliminado = categoriaRepo.eliminarLogico(idBaja);
                        if (eliminado) {
                            System.out.println("✅ Categoria dada de baja de forma logica.");
                        } else {
                            System.out.println("⚠️ No se encontró la Categoria con ese ID.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ ID inválido.");
                    }
                    break;

                case "4":
                    System.out.println("\n=== LISTADO DE Categorias ACTIVAS ===");
                    List<Categoria> activas = categoriaRepo.listarActivos();
                    if (activas.isEmpty()) {
                        System.out.println("No hay Categorias activas registradas.");
                    } else {
                        for (Categoria c : activas) {
                            System.out.println("ID: " + c.getId() + " | Nombre: " + c.getNombre() + " | descripcion: " + c.getDescripcion());
                        }
                    }
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuProductos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTION DE PRODUCTOS ---");
            System.out.println("1. Alta de Producto");
            System.out.println("2. Modificar Producto");
            System.out.println("3. Baja logica (Eliminar)");
            System.out.println("4. Listado de Productos");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1":
                    System.out.println("\n--- ALTA DE PRODUCTO ---");
                    // Primero mostramos las Categorias para que el usuario elija una válida
                    List<Categoria> categoriasDisponibles = categoriaRepo.listarActivos();
                    if (categoriasDisponibles.isEmpty()) {
                        System.out.println("⚠️ No puede crear productos si no existen Categorias primero.");
                        break;
                    }

                    System.out.println("Categorias disponibles:");
                    for (Categoria cat : categoriasDisponibles) {
                        System.out.println("ID: " + cat.getId() + " - " + cat.getNombre());
                    }
                    System.out.print("Seleccione el ID de la Categoria: ");

                    try {
                        Long catId = Long.parseLong(sc.nextLine().trim());
                        Optional<Categoria> catOpt = categoriaRepo.buscarPorId(catId);

                        if (catOpt.isEmpty() || catOpt.get().isEliminado()) {
                            System.out.println("⚠️ Categoria no válida o eliminada.");
                            break;
                        }

                        Categoria categoriaSeleccionada = catOpt.get();

                        System.out.print("Nombre del producto: ");
                        String nombre = sc.nextLine().trim();
                        System.out.print("Precio: ");
                        double precio = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("descripcion: ");
                        String desc = sc.nextLine().trim();
                        System.out.print("Stock inicial: ");
                        int stock = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("URL de la Imagen (o deje vacío): ");
                        String imagen = sc.nextLine().trim();

                        if (nombre.isEmpty() || precio <= 0 || stock < 0) {
                            System.out.println("⚠️ Datos inválidos. Verifique nombre, precio y stock.");
                            break;
                        }

                        // Construimos el Producto con Builder de Lombok
                        Producto nuevoProducto = Producto.builder()
                                .nombre(nombre)
                                .precio(precio)
                                .descripcion(desc)
                                .stock(stock)
                                .imagen(imagen.isEmpty() ? "default.png" : imagen)
                                .disponible(stock > 0)
                                .build();

                        // Sincronizamos la relación bidireccional en memoria según el método de tu clase Categoria
                        categoriaSeleccionada.addProducto(nuevoProducto);

                        // Al guardar la Categoria en cascada (CascadeType.ALL), se guarda el producto automáticamente
                        categoriaRepo.guardar(categoriaSeleccionada);
                        System.out.println("✅ Producto '" + nombre + "' creado con éxito asignado a '" + categoriaSeleccionada.getNombre() + "'.");

                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Error: Ingrese un valor numérico válido para ID, precio o stock.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.println("\n--- MODIFICAR PRODUCTO ---");
                    System.out.print("Ingrese el ID del producto a modificar: ");
                    try {
                        Long prodId = Long.parseLong(sc.nextLine().trim());
                        Optional<Producto> prodOpt = productoRepo.buscarPorId(prodId);

                        if (prodOpt.isPresent() && !prodOpt.get().isEliminado()) {
                            Producto prod = prodOpt.get();
                            System.out.print("Nuevo nombre [" + prod.getNombre() + "]: ");
                            String nuevoNom = sc.nextLine().trim();
                            System.out.print("Nuevo precio [" + prod.getPrecio() + "]: ");
                            String nuevoPrecioStr = sc.nextLine().trim();
                            System.out.print("Nueva descripcion [" + prod.getDescripcion() + "]: ");
                            String nuevaDesc = sc.nextLine().trim();
                            System.out.print("Nuevo stock [" + prod.getStock() + "]: ");
                            String nuevoStockStr = sc.nextLine().trim();

                            if (!nuevoNom.isEmpty()) prod.setNombre(nuevoNom);
                            if (!nuevoPrecioStr.isEmpty()) prod.setPrecio(Double.parseDouble(nuevoPrecioStr));
                            if (!nuevaDesc.isEmpty()) prod.setDescripcion(nuevaDesc);
                            if (!nuevoStockStr.isEmpty()) {
                                int stockNuevo = Integer.parseInt(nuevoStockStr);
                                prod.setStock(stockNuevo);
                                prod.setDisponible(stockNuevo > 0);
                            }

                            productoRepo.guardar(prod);
                            System.out.println("✅ Producto actualizado con éxito.");
                        } else {
                            System.out.println("⚠️ Producto no encontrado o eliminado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Entrada numérica inválida.");
                    }
                    break;

                case "3":
                    System.out.print("Ingrese el ID del producto a dar de baja: ");
                    try {
                        Long idBaja = Long.parseLong(sc.nextLine().trim());
                        boolean eliminado = productoRepo.eliminarLogico(idBaja);
                        if (eliminado) {
                            System.out.println("✅ Producto dado de baja (logica) con éxito.");
                        } else {
                            System.out.println("⚠️ No se encontró el producto con ese ID.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ ID inválido.");
                    }
                    break;

                case "4":
                    System.out.println("\n=== LISTADO DE PRODUCTOS ACTIVOS ===");
                    List<Producto> productos = productoRepo.listarActivos();
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos activos registrados.");
                    } else {
                        for (Producto p : productos) {
                            System.out.println("ID: " + p.getId() +
                                    " | Nombre: " + p.getNombre() +
                                    " | Precio: $" + p.getPrecio() +
                                    " | Stock: " + p.getStock() +
                                    " | Disponible: " + (p.getDisponible() ? "SÍ" : "NO"));
                        }
                    }
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuUsuarios() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTION DE USUARIOS ---");
            System.out.println("1. Alta de Usuario");
            System.out.println("2. Modificar Usuario");
            System.out.println("3. Baja logica (Eliminar)");
            System.out.println("4. Listado de Usuarios");
            System.out.println("5. Buscar por Mail");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1":
                    System.out.println("\n--- ALTA DE USUARIO ---");
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine().trim();
                    System.out.print("Mail: ");
                    String mail = sc.nextLine().trim();
                    System.out.print("Celular: ");
                    String celular = sc.nextLine().trim();
                    System.out.print("Contraseña: ");
                    String pass = sc.nextLine().trim();
                    System.out.print("¿Es Administrador? (S/N): ");
                    String esAdmin = sc.nextLine().trim().toUpperCase();

                    if (nombre.isEmpty() || apellido.isEmpty() || mail.isEmpty() || pass.isEmpty()) {
                        System.out.println("⚠️ Error: Nombre, Apellido, Mail y Contraseña son campos obligatorios.");
                        break;
                    }

                    Rol rolElegido = esAdmin.equals("S") ? Rol.ADMIN : Rol.USUARIO;

                    Usuario nuevoUsuario = Usuario.builder()
                            .nombre(nombre)
                            .apellido(apellido)
                            .mail(mail)
                            .celular(celular)
                            .contrasenia(pass)
                            .rol(rolElegido)
                            .build();

                    try {
                        usuarioRepo.guardar(nuevoUsuario);
                        System.out.println("✅ Usuario registrado con éxito. ID asignado: " + nuevoUsuario.getId());
                    } catch (Exception e) {
                        System.out.println("⚠️ Error al guardar: Es muy probable que el correo electrónico ya esté registrado.");
                    }
                    break;

                case "2":
                    System.out.println("\n--- MODIFICAR USUARIO ---");
                    System.out.print("Ingrese el ID del usuario a modificar: ");
                    try {
                        Long userId = Long.parseLong(sc.nextLine().trim());
                        Optional<Usuario> userOpt = usuarioRepo.buscarPorId(userId);

                        if (userOpt.isPresent() && !userOpt.get().isEliminado()) {
                            Usuario usuario = userOpt.get();
                            System.out.print("Nuevo nombre [" + usuario.getNombre() + "]: ");
                            String nuevoNom = sc.nextLine().trim();
                            System.out.print("Nuevo apellido [" + usuario.getApellido() + "]: ");
                            String nuevoApe = sc.nextLine().trim();
                            System.out.print("Nuevo celular [" + usuario.getCelular() + "]: ");
                            String nuevoCel = sc.nextLine().trim();
                            System.out.print("Nueva contraseña [***]: ");
                            String nuevaPass = sc.nextLine().trim();

                            if (!nuevoNom.isEmpty()) usuario.setNombre(nuevoNom);
                            if (!nuevoApe.isEmpty()) usuario.setApellido(nuevoApe);
                            if (!nuevoCel.isEmpty()) usuario.setCelular(nuevoCel);
                            if (!nuevaPass.isEmpty()) usuario.setContrasenia(nuevaPass);

                            usuarioRepo.guardar(usuario);
                            System.out.println("✅ Datos del usuario actualizados con éxito.");
                        } else {
                            System.out.println("⚠️ Usuario no encontrado o inactivo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ ID inválido.");
                    }
                    break;

                case "3":
                    System.out.print("Ingrese el ID del usuario a dar de baja: ");
                    try {
                        Long idBaja = Long.parseLong(sc.nextLine().trim());
                        boolean eliminado = usuarioRepo.eliminarLogico(idBaja);
                        if (eliminado) {
                            System.out.println("✅ Usuario desactivado (baja logica) con éxito.");
                        } else {
                            System.out.println("⚠️ No se encontró un usuario activo con ese ID.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ ID inválido.");
                    }
                    break;

                case "4":
                    System.out.println("\n=== LISTADO DE USUARIOS ACTIVOS ===");
                    List<Usuario> usuarios = usuarioRepo.listarActivos();
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios activos registrados.");
                    } else {
                        for (Usuario u : usuarios) {
                            System.out.println("ID: " + u.getId() +
                                    " | " + u.getApellido() + ", " + u.getNombre() +
                                    " | Mail: " + u.getMail() +
                                    " | Rol: " + u.getRol());
                        }
                    }
                    break;

                case "5":
                    System.out.println("\n--- BUSCAR USUARIO POR MAIL ---");
                    System.out.print("Ingrese el correo electrónico exacto: ");
                    String correoBuscar = sc.nextLine().trim();

                    Optional<Usuario> busquedaOpt = usuarioRepo.buscarPorMail(correoBuscar);
                    if (busquedaOpt.isPresent()) {
                        Usuario u = busquedaOpt.get();
                        System.out.println("✅ Usuario encontrado:");
                        System.out.println("ID: " + u.getId());
                        System.out.println("Nombre Completo: " + u.getNombre() + " " + u.getApellido());
                        System.out.println("Celular: " + (u.getCelular() == null ? "No registrado" : u.getCelular()));
                        System.out.println("Rol en el sistema: " + u.getRol());
                    } else {
                        System.out.println("❌ No se encontró ningún usuario activo con el mail: " + correoBuscar);
                    }
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuPedidos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTION DE PEDIDOS ---");
            System.out.println("1. Alta de Pedido (Simular Compra)");
            System.out.println("2. Cambiar Estado de Pedido");
            System.out.println("3. Baja logica (Eliminar)");
            System.out.println("4. Listado General de Pedidos");
            System.out.println("5. Filtrar Pedidos por Usuario");
            System.out.println("6. Filtrar Pedidos por Estado");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1":
                    System.out.println("\n--- SIMULACIÓN DE COMPRA (ALTA DE PEDIDO) ---");
                    System.out.print("Ingrese el ID del Usuario que realiza la compra: ");
                    try {
                        Long userId = Long.parseLong(sc.nextLine().trim());
                        Optional<Usuario> userOpt = usuarioRepo.buscarPorId(userId);
                        if (userOpt.isEmpty() || userOpt.get().isEliminado()) {
                            System.out.println("⚠️ Usuario no válido o inactivo.");
                            break;
                        }
                        Usuario usuario = userOpt.get();

                        // Creamos la instancia base del pedido usando el Builder de Lombok
                        System.out.println("Seleccione Forma de Pago: 1-EFECTIVO, 2-TARJETA, 3-TRANSFERENCIA");
                        System.out.print("Opción pago: ");
                        String opPago = sc.nextLine().trim();
                        FormaPago formaPago = FormaPago.EFECTIVO;
                        if (opPago.equals("2")) formaPago = FormaPago.TARJETA;
                        if (opPago.equals("3")) formaPago = FormaPago.TRANSFERENCIA;

                        Pedido nuevoPedido = Pedido.builder()
                                .formaPago(formaPago)
                                .estado(EstadoPedido.PENDIENTE)
                                .build();

                        // Bucle interactivo para armar el carrito de compras (detalles)
                        boolean agregando = true;
                        while (agregando) {
                            System.out.println("\nProductos disponibles en la tienda:");
                            List<Producto> productos = productoRepo.listarActivos();
                            for (Producto p : productos) {
                                System.out.println("ID: " + p.getId() + " | " + p.getNombre() + " | Precio: $" + p.getPrecio() + " | Stock: " + p.getStock());
                            }
                            System.out.print("Ingrese el ID del producto a agregar (o 0 para finalizar el carrito): ");
                            Long prodId = Long.parseLong(sc.nextLine().trim());

                            if (prodId == 0) {
                                agregando = false;
                                break;
                            }

                            Optional<Producto> prodOpt = productoRepo.buscarPorId(prodId);
                            if (prodOpt.isPresent() && !prodOpt.get().isEliminado()) {
                                Producto producto = prodOpt.get();
                                System.out.print("Ingrese la cantidad para '" + producto.getNombre() + "': ");
                                int cantidad = Integer.parseInt(sc.nextLine().trim());

                                if (cantidad <= 0) {
                                    System.out.println("⚠️ La cantidad debe ser mayor a cero.");
                                    continue;
                                }
                                if (cantidad > producto.getStock()) {
                                    System.out.println("⚠️ Stock insuficiente. Solo quedan " + producto.getStock() + " unidades.");
                                    continue;
                                }

                                // Modificamos el stock del producto en la base de datos
                                producto.setStock(producto.getStock() - cantidad);
                                if (producto.getStock() == 0) {
                                    producto.setDisponible(false);
                                }
                                productoRepo.guardar(producto);

                                // Usamos el método ayudante del profesor en la entidad Pedido
                                nuevoPedido.addDetallePedido(cantidad, producto);
                                System.out.println("🛒 Añadido al carrito con éxito.");
                            } else {
                                System.out.println("⚠️ Producto no encontrado.");
                            }
                        }

                        if (nuevoPedido.getDetalles().isEmpty()) {
                            System.out.println("❌ No se agregaron productos. Pedido cancelado.");
                            break;
                        }

                        // Vinculamos el pedido al usuario según el modelo bidireccional en memoria
                        usuario.addPedido(nuevoPedido);

                        // Guardamos el usuario y por cascada (CascadeType.ALL) se persiste el Pedido con todos sus detalles.
                        usuarioRepo.guardar(usuario);
                        System.out.println("✅ Pedido creado con éxito. Total de la orden: $" + nuevoPedido.getTotal());

                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Error: Ingrese un número válido.");
                    }
                    break;

                case "2":
                    System.out.println("\n--- CAMBIAR ESTADO DE PEDIDO ---");
                    System.out.print("Ingrese el ID del pedido: ");
                    try {
                        Long pedId = Long.parseLong(sc.nextLine().trim());
                        Optional<Pedido> pedOpt = pedidoRepo.buscarPorId(pedId);

                        if (pedOpt.isPresent() && !pedOpt.get().isEliminado()) {
                            Pedido pedido = pedOpt.get();
                            System.out.println("Estado actual: " + pedido.getEstado());
                            System.out.println("Seleccione el nuevo estado: 1-PENDIENTE, 2-PREPARACION, 3-ENVIADO, 4-ENTREGADO, 5-CANCELADO");
                            System.out.print("Opcion: ");
                            String opEst = sc.nextLine().trim();

                            switch (opEst) {
                                case "1": pedido.setEstado(EstadoPedido.PENDIENTE); break;
                                case "2": pedido.setEstado(EstadoPedido.PREPARACION); break;
                                case "3": pedido.setEstado(EstadoPedido.ENVIADO); break;
                                case "4": pedido.setEstado(EstadoPedido.ENTREGADO); break;
                                case "5": pedido.setEstado(EstadoPedido.CANCELADO); break;
                                default: System.out.println("⚠️ Opción inválida. No se alteró el estado.");
                            }

                            pedidoRepo.guardar(pedido);
                            System.out.println("✅ Estado del pedido actualizado a: " + pedido.getEstado());
                        } else {
                            System.out.println("⚠️ Pedido no encontrado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ ID inválido.");
                    }
                    break;

                case "3":
                    System.out.print("Ingrese el ID del pedido a eliminar (baja logica): ");
                    try {
                        Long idBaja = Long.parseLong(sc.nextLine().trim());
                        boolean eliminado = pedidoRepo.eliminarLogico(idBaja);
                        if (eliminado) {
                            System.out.println("✅ Pedido eliminado de forma logica.");
                        } else {
                            System.out.println("⚠️ No se encontró el pedido.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ ID inválido.");
                    }
                    break;

                case "4":
                    System.out.println("\n=== LISTADO GENERAL DE PEDIDOS ACTIVOS ===");
                    List<Pedido> pedidos = pedidoRepo.listarActivos();
                    if (pedidos.isEmpty()) {
                        System.out.println("No hay pedidos registrados en el sistema.");
                    } else {
                        for (Pedido p : pedidos) {
                            System.out.println("ID Pedido: " + p.getId() + " | Fecha: " + p.getFecha() + " | Estado: " + p.getEstado() + " | Total: $" + p.getTotal());
                        }
                    }
                    break;

                case "5":
                    System.out.print("Ingrese el ID del Usuario para ver sus pedidos: ");
                    try {
                        Long uId = Long.parseLong(sc.nextLine().trim());
                        List<Pedido> pedidosUser = usuarioRepo.buscarPedidosPorUsuario(uId);

                        System.out.println("\n=== PEDIDOS DEL USUARIO ID " + uId + " ===");
                        if (pedidosUser.isEmpty()) {
                            System.out.println("Este usuario no registra pedidos activos.");
                        } else {
                            for (Pedido p : pedidosUser) {
                                System.out.println("ID Pedido: " + p.getId() + " | Fecha: " + p.getFecha() + " | Estado: " + p.getEstado() + " | Total: $" + p.getTotal());
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ ID inválido.");
                    }
                    break;

                case "6":
                    System.out.println("Seleccione el estado a buscar: 1-PENDIENTE, 2-PREPARACION, 3-ENVIADO, 4-ENTREGADO, 5-CANCELADO");
                    System.out.print("Opcion: ");
                    String opBusc = sc.nextLine().trim();
                    EstadoPedido estBusc = EstadoPedido.PENDIENTE;
                    if (opBusc.equals("2")) estBusc = EstadoPedido.PREPARACION;
                    if (opBusc.equals("3")) estBusc = EstadoPedido.ENVIADO;
                    if (opBusc.equals("4")) estBusc = EstadoPedido.ENTREGADO;
                    if (opBusc.equals("5")) estBusc = EstadoPedido.CANCELADO;

                    List<Pedido> pedidosFiltrados = pedidoRepo.buscarPorEstado(estBusc);
                    System.out.println("\n=== PEDIDOS CON ESTADO " + estBusc + " ===");
                    if (pedidosFiltrados.isEmpty()) {
                        System.out.println("No se encontraron pedidos activos en este estado.");
                    } else {
                        for (Pedido p : pedidosFiltrados) {
                            System.out.println("ID Pedido: " + p.getId() + " | Total: $" + p.getTotal() + " | Pago: " + p.getFormaPago());
                        }
                    }
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuReportes() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- SUBmenu DE REPORTES ---");
            System.out.println("1. Listar Productos por Categoria");
            System.out.println("2. Listar Pedidos por Usuario");
            System.out.println("3. Listar Pedidos por Estado");
            System.out.println("4. Calcular Total Facturado (General)");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1":
                    System.out.println("\n--- PRODUCTOS POR Categoria ---");
                    List<Categoria> cats = categoriaRepo.listarActivos();
                    if (cats.isEmpty()) {
                        System.out.println("No hay Categorias registradas.");
                        break;
                    }

                    for (Categoria c : cats) {
                        System.out.println("\n📁 Categoria: " + c.getNombre() + " (ID: " + c.getId() + ")");
                        List<Producto> prods = categoriaRepo.buscarProductosPorCategoria(c.getId());
                        if (prods.isEmpty()) {
                            System.out.println("   (No hay productos activos en esta Categoria)");
                        } else {
                            for (Producto p : prods) {
                                System.out.println("   🔹 [ID: " + p.getId() + "] " + p.getNombre() + " - $" + p.getPrecio());
                            }
                        }
                    }
                    break;

                case "2":
                    System.out.println("\n--- PEDIDOS POR USUARIO ---");
                    System.out.print("Ingrese el ID del Usuario: ");
                    try {
                        Long uId = Long.parseLong(sc.nextLine().trim());
                        Optional<Usuario> uOpt = usuarioRepo.buscarPorId(uId);
                        if (uOpt.isEmpty() || uOpt.get().isEliminado()) {
                            System.out.println("⚠️ Usuario no encontrado o inactivo.");
                            break;
                        }

                        System.out.println("Historial de pedidos para: " + uOpt.get().getNombre() + " " + uOpt.get().getApellido());
                        List<Pedido> pedidosUser = usuarioRepo.buscarPedidosPorUsuario(uId);
                        if (pedidosUser.isEmpty()) {
                            System.out.println("Este usuario no registra compras.");
                        } else {
                            for (Pedido p : pedidosUser) {
                                System.out.println("   🔸 Pedido ID: " + p.getId() + " | Fecha: " + p.getFecha() + " | Estado: " + p.getEstado() + " | Total: $" + p.getTotal());
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ ID inválido.");
                    }
                    break;

                case "3":
                    System.out.println("\n--- PEDIDOS POR ESTADO ---");
                    System.out.println("Seleccione estado: 1-PENDIENTE, 2-PREPARACION, 3-ENVIADO, 4-ENTREGADO, 5-CANCELADO");
                    System.out.print("Opcion: ");
                    String opEst = sc.nextLine().trim();
                    com.tp.jpa.model.enums.EstadoPedido estBusc = com.tp.jpa.model.enums.EstadoPedido.PENDIENTE;
                    if (opEst.equals("2")) estBusc = com.tp.jpa.model.enums.EstadoPedido.PREPARACION;
                    if (opEst.equals("3")) estBusc = com.tp.jpa.model.enums.EstadoPedido.ENVIADO;
                    if (opEst.equals("4")) estBusc = com.tp.jpa.model.enums.EstadoPedido.ENTREGADO;
                    if (opEst.equals("5")) estBusc = com.tp.jpa.model.enums.EstadoPedido.CANCELADO;

                    List<Pedido> pFiltrados = pedidoRepo.buscarPorEstado(estBusc);
                    System.out.println("\nListado de pedidos en estado [" + estBusc + "]:");
                    if (pFiltrados.isEmpty()) {
                        System.out.println("No hay pedidos en este estado.");
                    } else {
                        for (Pedido p : pFiltrados) {
                            System.out.println("   📦 Pedido ID: " + p.getId() + " | Total: $" + p.getTotal() + " | Pago: " + p.getFormaPago());
                        }
                    }
                    break;

                case "4":
                    System.out.println("\n--- TOTAL RECAUDADO / FACTURADO ---");
                    List<Pedido> todosLosPedidos = pedidoRepo.listarActivos();
                    double acumuladorFacturado = 0.0;
                    int contadorEntregados = 0;

                    for (Pedido p : todosLosPedidos) {
                        // Consideramos facturado solo lo que ya se entregó con éxito
                        if (p.getEstado() == com.tp.jpa.model.enums.EstadoPedido.ENTREGADO) {
                            acumuladorFacturado += p.getTotal();
                            contadorEntregados++;
                        }
                    }
                    System.out.println("=========================================");
                    System.out.println(" 📊 Cantidad de pedidos entregados: " + contadorEntregados);
                    System.out.println(" 💰 TOTAL FACTURADO NETO: $" + acumuladorFacturado);
                    System.out.println("=========================================");
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void inicializarDatosBase() {
        // Si no hay Categorias creadas, meto las iniciales para demostración
        if (categoriaRepo.listarActivos().isEmpty()) {
            System.out.println("--- Cargando Categorias bases para demostración ---");
            categoriaRepo.guardar(Categoria.builder().nombre("Pizzas").descripcion("Pizzas artesanales al horno de barro").build());
            categoriaRepo.guardar(Categoria.builder().nombre("Hamburguesas").descripcion("Hamburguesas caseras con papas").build());
            categoriaRepo.guardar(Categoria.builder().nombre("Bebidas").descripcion("Gaseosas y aguas saborizadas").build());
            System.out.println("--- Categorias cargadas con éxito ---");
        }
    }

}
