package com.tup.programacion3.entities;

import com.tup.programacion3.enums.Rol;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;


@Entity
@Table(name = "usuario")
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
    @Column(unique = true) // Agrega restricción única para buscar por mail cómodamente
    private String mail;
    private String celular;
    private String contrasenia;
    @Enumerated(EnumType.STRING) // Guarda el enum como texto plano ("ADMIN"/"USUARIO")
    private Rol rol;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id") // Crea la clave foránea en la tabla pedido
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

