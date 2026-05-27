package SistemaStock;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Instanciamos un inventario
        Inventario inv = new Inventario();
        
        // Agregamos 5 productos al inventario
        inv.agregarProducto(new Producto("001","Smart TV 50",200000,6,CategoriaProducto.ELECTRONICA));
        inv.agregarProducto(new Producto("002","Hamburguesas x4",2500,10,CategoriaProducto.ALIMENTOS));
        inv.agregarProducto(new Producto("003","Pantalon negro hombre",45000,4,CategoriaProducto.ROPA));
        inv.agregarProducto(new Producto("004","Pan kg",1500,8,CategoriaProducto.ALIMENTOS));
        inv.agregarProducto(new Producto("005","Silla de madera UN",50000,7,CategoriaProducto.HOGAR));
        
        // Listamos los productos agregados
        inv.listarProductos();
        
        // Listamos el producto con id 001
        System.out.println("\nProducto id 001: ");
        Producto productoPorId = inv.buscarProductoPorId("001"); // Utilizamos el metodo de inventario para buscar por id
        productoPorId.mostrarInfo(); // Mostramos la info del producto encontrado
        
        // Filtramos productos por categoria ROPA
        System.out.println("\nProductos filtrados");
        ArrayList<Producto> productosPorCategoria = inv.filtrarPorCategoria(CategoriaProducto.ROPA); // Llamamos al metodo de inventario para filtrar por categoria
        for(Producto p : productosPorCategoria) { // Recorremos los productos filtrados
            p.mostrarInfo(); // Mostramos los resultados
        }
        
        // Eliminamos un producto por id
        System.out.println("\nEliminado producto id 005");
        inv.eliminarProducto("005"); // Llamamos al metodo de inventario para eliminar por id
        System.out.println("\nInventario actualizado: ");
        inv.listarProductos(); // Mostramos el inventario actualizado
        
        // Actualizamos el stock del producto con id 003
        inv.actualizarStock("003", 10);
        
        // Mostramos el total de productos que tenemos en nuestro inventario
        System.out.println("\nStock disponible: " + inv.obtenerTotalStock());
        
        // Mostramos el producto con mayor stock
        System.out.println("\nProducto con mayor Stock: ");
        inv.productoConMayorStock().mostrarInfo();
        
        // Filtramos por precio entre 1000 y 3000
        System.out.println("\nProductos entre $1000 y $3000");
        ArrayList<Producto> productosFiltradosPorPrecio = inv.filtrarProductosPorPrecio(1000, 3000); // Utilizamos el metodo de filtrar por precio de la clase inventario
        for(Producto p: productosFiltradosPorPrecio){ // Recorremos los productos filtrados
            p.mostrarInfo(); // Mostramos la info de cada producto
        }
        
        // Mostramos categorias disponibles
        System.out.println("\nCategorias disponibles: ");
        inv.mostrarCategoriasDisponibles();
           
    }
    
}
