package com.tup.programacion3.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Permite usar el patrón Builder en clases con herencia
public abstract class Base {
    private Long id;
    private boolean eliminado;
    @lombok.Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();




    // Sobreescritura de Equals y HashCode
    // Comparo los objetos por su ID único

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;                              //Si el id en null, entonces el hascode devuelve un 0 y no se rompe el programa
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                                       //Si ocupan el mismo lugar, son identicos
        if (obj == null || getClass() != obj.getClass()) return false;      //Si el obj esta vacio o es de otra clase, son distintos
        Base base = (Base) obj;                                             //casteo el obj y lo guarda en una variable para poder traer su id y compararlo
        return id != null && id.equals(base.id);                            //Son iguales si sus id coinciden
    }

}

