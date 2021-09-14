package com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.output;

import com.example.CRUD_relacion_tablas2.estudiante.domain.EstudianteEntity;
import com.example.CRUD_relacion_tablas2.exception.ErrorException;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output.PersonaSimpleOutputDto;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.output.ProfesorOutputDto;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.repository.ProfesorRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstudianteOutputDto {

    String id;
    Integer num_hours_week;
    String branch;
    String coments;

    PersonaSimpleOutputDto personaSimpleOutputDto;
    ProfesorOutputDto profesorOutputDto;

    public EstudianteOutputDto(EstudianteEntity estudianteEntity, PersonaRepository personaRepository,
                               ProfesorRepository profesorRepository){
        if (estudianteEntity ==null)
            return;
        setBranch(estudianteEntity.getBranch());
        setComents(estudianteEntity.getComents());
        setNum_hours_week(estudianteEntity.getNum_hours_week());
        setId(estudianteEntity.getId());

        setPersonaSimpleOutputDto(new PersonaSimpleOutputDto(personaRepository.findById(estudianteEntity.getPersonaEntity().
                getId()).orElseThrow(() -> new ErrorException("Persona con el id: " +
                + estudianteEntity.getPersonaEntity().getId() + "no encontrada."))));

        setProfesorOutputDto(new ProfesorOutputDto(profesorRepository.findById(estudianteEntity.getProfesorEntity().
                getId()).orElseThrow(() -> new ErrorException("Profesor con el id: " +
                 estudianteEntity.getProfesorEntity().getId() + " no encontrado.")),personaRepository));


    }
}
