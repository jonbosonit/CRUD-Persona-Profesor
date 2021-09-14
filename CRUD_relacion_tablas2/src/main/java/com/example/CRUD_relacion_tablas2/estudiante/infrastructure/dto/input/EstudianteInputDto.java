package com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EstudianteInputDto {

    int id_persona;
    String id_profesor;

    @NotNull(message = "El campo de estudiante num_hours_week no puede ser nulo.")
    Integer num_hours_week;

    String coments;

    @NotNull(message = "El campo de estudiante branch no puede ser nulo.")
    String branch;
}
