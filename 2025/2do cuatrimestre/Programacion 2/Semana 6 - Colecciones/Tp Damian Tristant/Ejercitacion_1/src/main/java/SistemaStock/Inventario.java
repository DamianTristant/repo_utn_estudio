package SistemaStock;

import java.util.ArrayList;

public class Inventario {

    // Instanciamos una coleccion para guardar los productos
    private ArrayList<Producto> productos;

    // Inicilizamos la coleccion en el constructor
    public Inventario() {
        this.productos = new ArrayList();
    }

    /**
     * Agrega un nuevo producto a la lista de productos.
     *
     * @param p El producto que se desea agregar.
     */
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    /**
     * Lista todos los productos disponibles mostrando su información por
     * consola.
     */
    public void listarProductos() {
        for (Producto p : productos) {
            p.mostrarInfo();
        }
    }

    /**
     * Busca un producto en la lista por su ID.
     *
     * @param id El identificador del producto a buscar.
     * @return El producto si se encuentra, o null si no existe.
     */
    public Producto buscarProductoPorId(String id) {
        Producto productoEncontrado = null;
        int i = 0;

        while (i < productos.size() && !this.productos.get(i).getId().equalsIgnoreCase(id)) {
            i++;
        }

        if (i < this.productos.size()) {
            productoEncontrado = this.productos.get(i);
        }

        return productoEncontrado;
    }

    /**
     * Elimina un producto de la lista según su ID.
     *
     * @param id El identificador del producto a eliminar.
     */
    public void eliminarProducto(String id) {
        Producto p = buscarProductoPorId(id);

        if (p != null) {
            this.productos.remove(p);
            System.out.println("El producto se elimino correctamente...");
        } else {
            System.out.println("ID no encontrado!");
        }
    }

    /**
     * Actualiza el stock de un producto existente.
     *
     * @param id El identificador del producto a actualizar.
     * @param nuevaCantidad La nueva cantidad de stock (debe ser mayor a 0).
     */
    public void actualizarStock(String id, int nuevaCantidad) {
        Producto p = buscarProductoPorId(id);

        if (p != null && nuevaCantidad > 0) {
            p.setCantidad(nuevaCantidad);
            System.out.println("El stock se actualizo con exito!");
        } else if (nuevaCantidad < 0) {
            System.out.println("Debe ingresar una cantidad positiva...");
        } else if (p == null) {
            System.out.println("ID no encontrado!");
        }
    }

    /**
     * Filtra y devuelve una lista de productos que pertenecen a una categoría
     * específica.
     *
     * @param categoria La categoría a filtrar.
     * @return Una lista de productos que pertenecen a la categoría
     * especificada.
     */
    public ArrayList<Producto> filtrarPorCategoria(CategoriaProducto categoria) {
        ArrayList<Producto> productosFiltrados = new ArrayList();

        for (Producto p : productos) {
            if (p.getCategoria() == categoria) {
                productosFiltrados.add(p);
            }
        }

        return productosFiltrados;
    }

    /**
     * Calcula y devuelve el total de stock de todos los productos.
     *
     * @return La suma total de unidades en stock.
     */
    public int obtenerTotalStock() {
        int totalStock = 0;
        for (Producto p : productos) {
            totalStock += p.getCantidad();
        }

        return totalStock;
    }

    /**
     * Busca y devuelve el producto con mayor cantidad en stock. Si hay varios
     * con el mismo stock máximo, devuelve el último encontrado.
     *
     * @return El producto con mayor stock, o null si la lista está vacía.
     */
    public Producto productoConMayorStock() {
        int maxStock = -1;
        Producto productoConMayorStock = null;
        for (Producto p : productos) {
            if (p.getCantidad() >= maxStock) {
                maxStock = p.getCantidad();
                productoConMayorStock = p;
            }
        }

        return productoConMayorStock;
    }

    /**
     * Filtra los productos cuyo precio se encuentra entre un mínimo y un máximo
     * (inclusive).
     *
     * @param min El precio mínimo.
     * @param max El precio máximo.
     * @return Una lista de productos cuyo precio está dentro del rango
     * especificado.
     */
    public ArrayList<Producto> filtrarProductosPorPrecio(double min, double max) {
        ArrayList<Producto> productosFiltrados = new ArrayList();

        for (Producto p : productos) {
            if (p.getPrecio() >= min && p.getPrecio() <= max) {
                productosFiltrados.add(p);
            }
        }

        return productosFiltrados;
    }

    /**
     * Muestra por consola todas las categorías de producto disponibles junto a
     * su descripción.
     */
    public void mostrarCategoriasDisponibles() {
        System.out.println("Las categorias disponibles son: ");
        for (CategoriaProducto c : CategoriaProducto.values()) {
            System.out.println("Categoria: " + c);
            System.out.println("Descripcion: " + c.getDescripcion());
        }
    }

}
