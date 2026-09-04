package com.tup.programacion3.entities;

import com.tup.programacion3.enums.Rol;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Usuario extends Base {
    @EqualsAndHashCode.Include
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;
    private Rol rol;

    @Builder.Default
    @ToString.Exclude
    private Set<Pedido> pedidos = new HashSet<>();

    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    // Método para transformar este Usuario en un DTO seguro (ocultando contraseña y rol)
    public com.tup.programacion3.dtos.UsuarioDTO toDTO() {
        return new com.tup.programacion3.dtos.UsuarioDTO(
                this.getId(),
                this.nombre,
                this.apellido,
                this.mail,
                this.celular
        );
    }
}

