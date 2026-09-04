package com.tup.programacion3.entities;
import java.util.Objects;

public class DetallePedido {
    private int cantidad;
    private Double subtotal;
    private Producto producto; // Relación hacia Producto

    // Constructor vacío
    public DetallePedido() {}

    // Constructor con parámetros (Solo cantidad y producto, el subtotal se calcula solo)
    public DetallePedido(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        calcularSubtotal(); // Lógica para calcular el subTotal
    }

    // Método para calcular el subtotal multiplicando cantidad * precio del producto
    private void calcularSubtotal() {
        if (this.producto != null && this.producto.getPrecio() != null) {
            this.subtotal = this.cantidad * this.producto.getPrecio();
        } else {
            this.subtotal = 0.0;
        }
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public Double getSubtotal() { return subtotal; }
    // No hacemos setter de subtotal para que nadie lo pueda modificar desde afuera

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) {
        this.producto = producto;
        calcularSubtotal();
    }

    // Sobreescritura de Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedido que = (DetallePedido) o;
        return Objects.equals(producto, que.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto);
    }

    // Sobreescritura de ToString
    @Override
    public String toString() {
        return "DetallePedido{cantidad=" + cantidad + ", subtotal=" + subtotal + ", producto=" + (producto != null ? producto.getNombre() : "null") + "}";
    }
}


