package com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output;

import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.output.EstudianteOutputDto;
import com.example.CRUD_relacion_tablas2.persona.domain.PersonaEntity;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.output.ProfesorOutputDto;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.repository.ProfesorRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonaOutputDto extends PersonaSimpleOutputDto {

    EstudianteOutputDto estudianteOutputDto;
    ProfesorOutputDto profesorOutputDto;


    public PersonaOutputDto(PersonaEntity personaEntity, PersonaRepository personaRepository, ProfesorRepository profesorRepository){
        super(personaEntity);
        this.estudianteOutputDto=new EstudianteOutputDto(personaEntity.getEstudianteEntity(),personaRepository,profesorRepository);
        this.profesorOutputDto=new ProfesorOutputDto(personaEntity.getProfesorEntity(),personaRepository);
    }

}
