package com.example.CRUD_relacion_tablas2.asignatura.aplication;

import com.example.CRUD_relacion_tablas2.asignatura.infrastructure.dto.input.AsignaturaInputDto;
import com.example.CRUD_relacion_tablas2.asignatura.infrastructure.dto.output.AsignaturaOutputDto;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDto save(AsignaturaInputDto asignaturaInputDto);
    List<AsignaturaOutputDto> findAll();
    AsignaturaOutputDto findById(String id);
    AsignaturaOutputDto updateById(String id, AsignaturaInputDto asignaturaInputDto);
    AsignaturaOutputDto deleteById(String id);

}
