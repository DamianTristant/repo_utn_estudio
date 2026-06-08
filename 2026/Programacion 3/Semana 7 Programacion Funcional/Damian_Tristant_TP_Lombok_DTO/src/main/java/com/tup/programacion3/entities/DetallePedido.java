package com.tup.programacion3.entities;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetallePedido {
    private int cantidad;
    private Double subtotal;

    @EqualsAndHashCode.Include
    private Producto producto; // Relación hacia Producto


    // Custom Builder para que calcule el subtotal automáticamente al construir el objeto
    public static class DetallePedidoBuilder {
        public DetallePedido build() {
            Double calculado = 0.0;
            if (this.producto != null && this.producto.getPrecio() != null) {
                calculado = this.cantidad * this.producto.getPrecio();
            }
            return new DetallePedido(this.cantidad, calculado, this.producto);
        }
    }

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


