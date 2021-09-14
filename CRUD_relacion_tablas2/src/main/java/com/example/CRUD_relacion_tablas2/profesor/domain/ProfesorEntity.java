package com.example.CRUD_relacion_tablas2.profesor.domain;

import com.example.CRUD_relacion_tablas2.estudiante.domain.EstudianteEntity;
import com.example.CRUD_relacion_tablas2.persona.domain.PersonaEntity;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.input.ProfesorInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profesor")
public class ProfesorEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    String id;

    @Column
    String coments;

    @Column
    String branch;

    @JoinColumn(name = "id_persona")
    @OneToOne(fetch = FetchType.LAZY)
    PersonaEntity personaEntity;

    @OneToMany(mappedBy = "profesorEntity", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    List<EstudianteEntity> estudianteEntities;

    public void getProfesor(ProfesorInputDto profesorInputDto, PersonaRepository personaRepository){
        if(profesorInputDto.getBranch() != null) setBranch(profesorInputDto.getBranch());
        if(profesorInputDto.getComents() != null) setComents(profesorInputDto.getComents());

        setPersonaEntity(personaRepository.findById(profesorInputDto.getId_persona()).get());

    }

}
