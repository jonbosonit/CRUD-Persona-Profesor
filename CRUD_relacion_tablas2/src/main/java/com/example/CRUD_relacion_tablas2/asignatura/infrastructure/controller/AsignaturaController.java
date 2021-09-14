package com.example.CRUD_relacion_tablas2.asignatura.infrastructure.controller;

import com.example.CRUD_relacion_tablas2.asignatura.aplication.AsignaturaService;
import com.example.CRUD_relacion_tablas2.asignatura.infrastructure.dto.input.AsignaturaInputDto;
import com.example.CRUD_relacion_tablas2.asignatura.infrastructure.dto.output.AsignaturaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v0/asignatura")
public class AsignaturaController {
    @Autowired
    AsignaturaService asignaturaService;

    @PostMapping
    public AsignaturaOutputDto anadirAsignatura(@RequestBody @Valid AsignaturaInputDto asignaturaInputDto){
        return asignaturaService.save(asignaturaInputDto);
    }

    @GetMapping("/all")
    public List<AsignaturaOutputDto> getAllAsignaturas(){
        return asignaturaService.findAll();
    }

    @GetMapping("/id/{id}")
    public AsignaturaOutputDto getAsignaturaById(@PathVariable String id){
        return asignaturaService.findById(id);
    }

    @PutMapping("/{id}")
    public AsignaturaOutputDto updateAsignatura(@PathVariable String id,
                                                @RequestBody @Valid AsignaturaInputDto asignaturaInputDto){
        return asignaturaService.updateById(id,asignaturaInputDto);
    }

    @DeleteMapping("/{id}")
    public AsignaturaOutputDto deleteAsignaturaById(@PathVariable String id){
        return asignaturaService.deleteById(id);
    }
}
