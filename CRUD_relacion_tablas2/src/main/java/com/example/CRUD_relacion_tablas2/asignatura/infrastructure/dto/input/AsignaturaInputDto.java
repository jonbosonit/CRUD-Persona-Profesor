package com.example.CRUD_relacion_tablas2.asignatura.infrastructure.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class AsignaturaInputDto {

    String id_estudiante;

    String asignautra;
    String coments;

    @DateTimeFormat
    @NotNull(message = "El campo initial_date de la entity asignatura no puede ser nulo.")
    Date initial_date;

    @DateTimeFormat
    @NotNull
    Date finish_date;

}
