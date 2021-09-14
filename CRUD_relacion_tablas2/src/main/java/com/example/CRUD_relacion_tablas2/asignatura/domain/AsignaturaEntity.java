package com.example.CRUD_relacion_tablas2.asignatura.domain;

import com.example.CRUD_relacion_tablas2.asignatura.infrastructure.dto.input.AsignaturaInputDto;
import com.example.CRUD_relacion_tablas2.estudiante.domain.EstudianteEntity;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.repository.EstudianteRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "asignatura")
public class AsignaturaEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    String id;

    @Column
    String asignatura;
    @Column
    String coments;
    @Column
    Date initial_date;
    @Column
    Date finish_date;

    @JoinColumn(name = "id_estudiante")
    @ManyToOne(fetch = FetchType.LAZY)
    EstudianteEntity estudianteEntity;

    public void getAsignatura(AsignaturaInputDto asignaturaInputDto, EstudianteRepository estudianteRepository){
        if(asignaturaInputDto.getAsignautra()!=null) setAsignatura(asignaturaInputDto.getAsignautra());
        if(asignaturaInputDto.getComents()!=null) setComents(asignaturaInputDto.getComents());
        if(asignaturaInputDto.getInitial_date()!=null) setInitial_date(asignaturaInputDto.getInitial_date());
        if(asignaturaInputDto.getFinish_date()!=null) setFinish_date(asignaturaInputDto.getFinish_date());
        setEstudianteEntity(estudianteRepository.findById(asignaturaInputDto.getId_estudiante()).get());
    }
}
