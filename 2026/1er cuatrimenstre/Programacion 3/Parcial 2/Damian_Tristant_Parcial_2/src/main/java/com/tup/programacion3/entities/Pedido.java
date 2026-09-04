package com.tup.programacion3.entities;

import com.tup.programacion3.enums.Estado;
import com.tup.programacion3.enums.FormaPago;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;


@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Pedido extends Base { // Nota: Si quitaste Calculable, asegurate de que compile bien
    @Builder.Default
    private LocalDate fecha = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Estado estado = Estado.PENDIENTE;

    private Double total;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    @ToString.Exclude // Agregado para evitar StackOverflow con DetallePedido
    private Set<DetallePedido> detalles = new HashSet<>();

    public void addDetallePedido(int cantidad, Producto producto) {
        // Creamos el objeto de forma segura usando sus setters para que se autocalcule el subtotal
        DetallePedido nuevoDetalle = new DetallePedido();
        nuevoDetalle.setProducto(producto);
        nuevoDetalle.setCantidad(cantidad);

        this.detalles.add(nuevoDetalle);
        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto() != null && detalle.getProducto().equals(producto)) {
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

    public void calcularTotal() {
        this.total = detalles.stream()
                .mapToDouble(d -> d.getSubtotal() != null ? d.getSubtotal() : 0.0)
                .sum();
    }
}

