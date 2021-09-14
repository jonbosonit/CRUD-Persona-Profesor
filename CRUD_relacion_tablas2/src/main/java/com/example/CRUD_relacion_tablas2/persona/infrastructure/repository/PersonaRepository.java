package com.example.CRUD_relacion_tablas2.persona.infrastructure.repository;

import com.example.CRUD_relacion_tablas2.persona.domain.PersonaEntity;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output.PersonaOutputDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Integer> {
    Optional<PersonaEntity> findById(int id);
    List<PersonaEntity> findByUser(String user);
    public List<PersonaOutputDto> getData(HashMap<String, Object> conditions);

}
