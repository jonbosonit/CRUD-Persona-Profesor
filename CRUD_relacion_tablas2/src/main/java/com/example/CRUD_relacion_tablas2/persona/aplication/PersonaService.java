package com.example.CRUD_relacion_tablas2.persona.aplication;

import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.input.PersonaInputDto;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output.PersonaOutputDto;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output.PersonaSimpleOutputDto;

import java.util.List;

public interface PersonaService {
    PersonaSimpleOutputDto save(PersonaInputDto personaInputDto);
    List<PersonaOutputDto> findAll();
    PersonaSimpleOutputDto findById(int id,String type);
    List<PersonaOutputDto> findByUser(String user);
    PersonaOutputDto deleteById(int id);
    PersonaOutputDto updateById(int id, PersonaInputDto personaInputDto);
    //List<PersonaOutputDto> getData(HashMap<String, Object> conditions);
}
