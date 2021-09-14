package com.example.CRUD_relacion_tablas2.estudiante.infrastructure.controller;

import com.example.CRUD_relacion_tablas2.estudiante.aplicaiton.EstudianteService;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.input.EstudianteInputDto;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.output.EstudianteOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v0/estudiante")
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    @PostMapping
    public EstudianteOutputDto anadirEstudiante(@RequestBody @Valid EstudianteInputDto estudianteInputDto){
        return estudianteService.save(estudianteInputDto);
    }

    @GetMapping("/all")
    public List<EstudianteOutputDto> getAllEstudiantes(){
        return estudianteService.findAll();
    }

    @GetMapping("/id/{id}")
    public EstudianteOutputDto getEstudianteById(@PathVariable String id){
        return estudianteService.findById(id);
    }

    @PutMapping("/{id}")
    public EstudianteOutputDto updateEstudiante(@PathVariable String id,
                                                @RequestBody @Valid EstudianteInputDto estudianteInputDto){
        return estudianteService.updateById(id, estudianteInputDto);
    }

    @DeleteMapping("/{id}")
    public EstudianteOutputDto deleteEstudianteById(@PathVariable String id){
        return estudianteService.deleteById(id);
    }
}
