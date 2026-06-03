package com.tup.programacion3.entities;
import com.tup.programacion3.enums.Estado;
import com.tup.programacion3.enums.FormaPago;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
public class Pedido extends Base implements Calculable {
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Set<DetallePedido> detalles; // Relación 1 a m  con DetallePedido

    // Constructor vacío
    public Pedido() {
        super();
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.total = 0.0;
        this.detalles = new HashSet<>();
    }

    // Constructor con parámetros
    public Pedido(Long id, FormaPago formaPago) {
        super(id, false, java.time.LocalDateTime.now());
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.formaPago = formaPago;
        this.total = 0.0;
        this.detalles = new HashSet<>();
    }

    // Métodos de la lógica del negocio
    // 1. Agregar un detalle al pedido
    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido nuevoDetalle = new DetallePedido(cantidad, producto);
        this.detalles.add(nuevoDetalle);
        calcularTotal(); // Cada vez que metemos un producto, recalculamos el total de la factura
    }

    // 2. Buscar un detalle según el producto
    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().equals(producto)) {
                return detalle;
            }
        }
        return null;
    }

    // 3. Borrar un detalle según el producto
    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido detalleEncontrado = findDetallePedidoByProducto(producto);
        if (detalleEncontrado != null) {
            this.detalles.remove(detalleEncontrado);
            calcularTotal(); // Recalculamos al sacar un producto
        }
    }

    // Implementación de la interfaz Calculable
    @Override
    public void calcularTotal() {
        double suma = 0.0;
        for (DetallePedido detalle : detalles) {
            suma += detalle.getSubtotal();
        }
        this.total = suma;
    }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Double getTotal() { return total; }
    // No hacemos setter de total para proteger el cálculo automático

    public FormaPago getFormaPago() { return formaPago; }
    public void setFormaPago(FormaPago formaPago) { this.formaPago = formaPago; }

    public Set<DetallePedido> getDetalles() { return detalles; }
    public void setDetalles(Set<DetallePedido> detalles) { this.detalles = detalles; }

    // Sobreescrituras de control
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(fecha, pedido.fecha) && Objects.equals(total, pedido.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fecha, total);
    }

    @Override
    public String toString() {
        return "Pedido{id=" + getId() + ", fecha=" + fecha + ", estado=" + estado + ", total=" + total + ", formaPago=" + formaPago + ", items=" + detalles.size() + "}";
    }
}

