package com.example.CRUD_relacion_tablas2.profesor.aplication;

import com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.input.ProfesorInputDto;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.output.ProfesorOutputDto;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDto save(ProfesorInputDto profesorInputDto);
    List<ProfesorOutputDto> findAll();
    ProfesorOutputDto findById(String id);
    List<ProfesorOutputDto> findByBranch(String branch);
    ProfesorOutputDto updateById(ProfesorInputDto profesorInputDto,String id);
    ProfesorOutputDto deleteById(String id);
}
