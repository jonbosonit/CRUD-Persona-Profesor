package com.example.CRUD_relacion_tablas2.persona.infrastructure.controller;

import com.example.CRUD_relacion_tablas2.persona.aplication.PersonaService;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.input.PersonaInputDto;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output.PersonaOutputDto;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output.PersonaSimpleOutputDto;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v0/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    EntityManager em;

    public static final String GREATER_THAN="greater";
    public static final String LESS_THAN="less";
    public static final String EQUAL="equal";

    @GetMapping("/get")
    public List<PersonaOutputDto> getData(
            @RequestParam(required = false, name = "user") String user,
            @RequestParam(required = false, name = "city") String city,
            @RequestParam(required = false, name = "surname") String surname,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date created_date,
            @RequestParam(required = false) String dateCondition,
            @RequestParam(required = false) String orden,
            @RequestParam(name = "page") Integer page
            ) {
        HashMap<String, Object> data = new HashMap<>();

        data.put("page", page);
        if(user != null)data.put("user",user);
        if(city != null)data.put("city",city);
        if(surname != null)data.put("surname",surname);
        if(!GREATER_THAN.equals(dateCondition) && !LESS_THAN.equals(dateCondition) && !EQUAL.equals(dateCondition))
            dateCondition=GREATER_THAN;
        if(dateCondition!=null){
            data.put("created_date", created_date);
            data.put("dateCondition", dateCondition);
        }
        if(orden != null){
            data.put("orden", orden);
        }



        return personaRepository.getData(data);
    }

    @PostMapping
    public PersonaSimpleOutputDto anadirPersona(@RequestBody @Valid PersonaInputDto personaInputDto){
        return personaService.save(personaInputDto);
    }

    @GetMapping("/all")
    List<PersonaOutputDto> getAllPersonas(){
        return personaService.findAll();
    }

    @GetMapping("/id/{id}")
    PersonaSimpleOutputDto getPersonasById(@PathVariable int id,
                                           @RequestParam(required = false, defaultValue = "SIMPLE") String type){
        return personaService.findById(id,type);
    }

    @GetMapping("/user/{user}")
    List<PersonaOutputDto> getPersonasByUser(@PathVariable String user){
        return personaService.findByUser(user);
    }

    @PutMapping("/{id}")
    PersonaOutputDto updatePersona(@PathVariable int id, @RequestBody @Valid PersonaInputDto personaInputDto){
        return personaService.updateById(id,personaInputDto);
    }

    @DeleteMapping("/{id}")
    PersonaOutputDto deletePersonaById(@PathVariable int id){
        return personaService.deleteById(id);
    }

}
