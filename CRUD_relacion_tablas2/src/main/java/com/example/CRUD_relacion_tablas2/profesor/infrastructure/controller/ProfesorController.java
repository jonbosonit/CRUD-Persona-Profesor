package com.example.CRUD_relacion_tablas2.profesor.infrastructure.controller;

import com.example.CRUD_relacion_tablas2.profesor.aplication.ProfesorService;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.input.ProfesorInputDto;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.output.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v0/profesor")
public class ProfesorController {

    @Autowired
    ProfesorService profesorService;

    @PostMapping
    public ProfesorOutputDto anadirProfesor(@RequestBody @Valid ProfesorInputDto profesorInputDto){
        return profesorService.save(profesorInputDto);
    }

    @GetMapping("/all")
    public List<ProfesorOutputDto> getAllProfesores(){
        return profesorService.findAll();
    }

    @GetMapping("/id/{id}")
    public ProfesorOutputDto getProfesorById(@PathVariable String id){
        return profesorService.findById(id);
    }

    @GetMapping("/branch/{branch}")
    public List<ProfesorOutputDto> getProfesorByBranch(@PathVariable String branch){
        return profesorService.findByBranch(branch);
    }

    @PutMapping("/{id}")
    public ProfesorOutputDto updateProfesorById(@RequestBody @Valid ProfesorInputDto profesorInputDto, @PathVariable String id){
        return profesorService.updateById(profesorInputDto,id);
    }

    @DeleteMapping("/{id}")
    public ProfesorOutputDto deleteProfesorById(@PathVariable String id){
        return profesorService.deleteById(id);
    }

}
