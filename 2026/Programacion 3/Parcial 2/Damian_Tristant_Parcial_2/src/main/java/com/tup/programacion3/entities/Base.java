package com.tup.programacion3.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
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
@MappedSuperclass // Le indica a JPA que herede sus columnas a las tablas hijas
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-incremental en la base de datos
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

