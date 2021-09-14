package com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.output;

import com.example.CRUD_relacion_tablas2.exception.ErrorException;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output.PersonaSimpleOutputDto;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import com.example.CRUD_relacion_tablas2.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfesorOutputDto {

    String id;
    String coments;
    String branch;
    PersonaSimpleOutputDto personaSimpleOutputDto;

    public ProfesorOutputDto(ProfesorEntity profesorEntity, PersonaRepository personaRepository){
        if(profesorEntity ==null)
            return;
        setBranch(profesorEntity.getBranch());
        setComents(profesorEntity.getComents());
        setId(profesorEntity.getId());
        setPersonaSimpleOutputDto(new PersonaSimpleOutputDto(personaRepository.findById(profesorEntity.getPersonaEntity().
                getId()).orElseThrow(() -> new ErrorException("Persona con el id: " +
                + profesorEntity.getPersonaEntity().getId() + "no encontrada."))));
    }

}
