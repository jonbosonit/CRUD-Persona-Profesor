package com.example.CRUD_relacion_tablas2.estudiante.domain;

import com.example.CRUD_relacion_tablas2.asignatura.domain.AsignaturaEntity;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.input.EstudianteInputDto;
import com.example.CRUD_relacion_tablas2.persona.domain.PersonaEntity;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import com.example.CRUD_relacion_tablas2.profesor.domain.ProfesorEntity;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.repository.ProfesorRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "estudiante")
public class EstudianteEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    String id;

    @Column
    Integer num_hours_week;

    @Column
    String coments;

    @Column
    String branch;

    @JoinColumn(name = "id_persona")
    @OneToOne(fetch = FetchType.LAZY)
    PersonaEntity personaEntity;

    @JoinColumn(name = "id_profesor")
    @ManyToOne(fetch = FetchType.LAZY)
    ProfesorEntity profesorEntity;

    @OneToMany(mappedBy = "estudianteEntity",cascade = {CascadeType.ALL},orphanRemoval = true,fetch = FetchType.LAZY)
    List<AsignaturaEntity> asignaturaEntities;


    public void getEstudiante(EstudianteInputDto estudianteInputDto, PersonaRepository personaRepository,
                              ProfesorRepository profesorRepository){
        if(estudianteInputDto.getBranch() != null) setBranch(estudianteInputDto.getBranch());
        if(estudianteInputDto.getComents() != null) setComents(estudianteInputDto.getComents());
        if(estudianteInputDto.getNum_hours_week() != null) setNum_hours_week(estudianteInputDto.getNum_hours_week());
        setPersonaEntity(personaRepository.findById(estudianteInputDto.getId_persona()).get());
        setProfesorEntity(profesorRepository.findById(estudianteInputDto.getId_profesor()).get());
    }
}
