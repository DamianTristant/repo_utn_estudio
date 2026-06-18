package com.tup.programacion3.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "detalle_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true) // Cambiado a true para ver datos de Base
public class DetallePedido extends Base {
    private int cantidad;
    private Double subtotal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "producto_id")
    @ToString.Exclude
    private Producto producto;

    // Método para calcular el subtotal multiplicando cantidad * precio del producto
    private void calcularSubtotal() {
        if (this.producto != null && this.producto.getPrecio() != null) {
            this.subtotal = this.cantidad * this.producto.getPrecio();
        } else {
            this.subtotal = 0.0;
        }
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        calcularSubtotal();
    }
}


