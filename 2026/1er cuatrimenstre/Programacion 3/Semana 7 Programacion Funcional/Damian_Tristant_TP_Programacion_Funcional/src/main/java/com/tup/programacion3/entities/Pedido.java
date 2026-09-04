package com.tup.programacion3.entities;

import com.tup.programacion3.enums.Estado;
import com.tup.programacion3.enums.FormaPago;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Pedido extends Base implements Calculable {
    @Builder.Default
    private LocalDate fecha = LocalDate.now();

    @Builder.Default
    private Estado estado = Estado.PENDIENTE;

    private Double total;
    private FormaPago formaPago;

    @Builder.Default
    private Set<DetallePedido> detalles = new HashSet<>();

    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido nuevoDetalle = DetallePedido.builder()
                .cantidad(cantidad)
                .producto(producto)
                .build();
        this.detalles.add(nuevoDetalle);
        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().equals(producto)) {
                return detalle;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido detalleEncontrado = findDetallePedidoByProducto(producto);
        if (detalleEncontrado != null) {
            this.detalles.remove(detalleEncontrado);
            calcularTotal();
        }
    }

    @Override
    public void calcularTotal() {
        // Transformamos el Set de detalles en un Stream, extraemos cada subtotal y los sumamos
        this.total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }
}

