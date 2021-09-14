package com.example.CRUD_relacion_tablas2.asignatura.infrastructure.dto.output;

import com.example.CRUD_relacion_tablas2.asignatura.domain.AsignaturaEntity;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.output.EstudianteOutputDto;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.repository.EstudianteRepository;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.repository.ProfesorRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AsignaturaSimpleOutputDto {

    String id;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;

    EstudianteOutputDto estudianteOutputDto;

    public AsignaturaSimpleOutputDto(AsignaturaEntity asignaturaEntity, EstudianteRepository estudianteRepository,
                                     PersonaRepository personaRepository, ProfesorRepository profesorRepository){
        if(asignaturaEntity==null)
            return;
        setAsignatura(asignaturaEntity.getAsignatura());
        setComents(asignaturaEntity.getComents());
        setInitial_date(asignaturaEntity.getInitial_date());
        setFinish_date(asignaturaEntity.getFinish_date());
        setId(asignaturaEntity.getId());

        setEstudianteOutputDto(new EstudianteOutputDto(estudianteRepository.getById(asignaturaEntity.getEstudianteEntity().getId()),
                personaRepository,profesorRepository));

    }
}
