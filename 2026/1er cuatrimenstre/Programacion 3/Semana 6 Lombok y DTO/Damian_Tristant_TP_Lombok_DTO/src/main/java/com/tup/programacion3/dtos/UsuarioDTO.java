package com.tup.programacion3.dtos;

public record UsuarioDTO(
        Long id,
        String nombre,
        String apellido,
        String mail,
        String celular
) {
    /*
     Al ser un record, no lleva llaves ni codigo adentro
     Java genera automaticamente un constructor que recibe los
     metodos de lectura con el mismo nombre que las variables
    */
}
