package com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProfesorInputDto {

    int id_persona;
    //String id_estudiante;

    String coments;

    @NotNull(message = "El campo branch de la entity Profesor no puede ser nulo.")
    String branch;

}
