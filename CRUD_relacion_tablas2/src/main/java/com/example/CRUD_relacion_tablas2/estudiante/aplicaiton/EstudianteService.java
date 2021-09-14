package com.example.CRUD_relacion_tablas2.estudiante.aplicaiton;

import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.input.EstudianteInputDto;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.output.EstudianteOutputDto;

import java.util.List;

public interface EstudianteService {
    EstudianteOutputDto save(EstudianteInputDto estudianteInputDto);
    List<EstudianteOutputDto> findAll();
    EstudianteOutputDto findById(String id);
    EstudianteOutputDto updateById(String id, EstudianteInputDto estudianteInputDto);
    EstudianteOutputDto deleteById(String id);
}
