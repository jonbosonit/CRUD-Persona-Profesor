package com.example.CRUD_relacion_tablas2.profesor.aplication;

import com.example.CRUD_relacion_tablas2.exception.ErrorException;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import com.example.CRUD_relacion_tablas2.profesor.domain.ProfesorEntity;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.input.ProfesorInputDto;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.dto.output.ProfesorOutputDto;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfesorServiceImpl implements ProfesorService{

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    PersonaRepository personaRepository;

    /*@Autowired
    EstudianteRepository estudianteRepository;*/

    @Override
    public ProfesorOutputDto save(ProfesorInputDto profesorInputDto) {
        ProfesorEntity profesorEntity = new ProfesorEntity();
        profesorEntity.getProfesor(profesorInputDto,personaRepository);
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesorRepository.save(profesorEntity),personaRepository);
        return profesorOutputDto;
    }

    @Override
    public List<ProfesorOutputDto> findAll() {
        List<ProfesorOutputDto> profesorOutputDtos;
        profesorOutputDtos = profesorRepository.findAll().stream().map(p ->
                new ProfesorOutputDto(p,personaRepository)).collect(Collectors.toList());
        return profesorOutputDtos;
    }

    @Override
    public ProfesorOutputDto findById(String id) {
        ProfesorEntity profesorEntity = profesorRepository.findById(id).orElseThrow(()->
                new ErrorException("Profesor con el id: " + id + " no encontrado."));
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesorEntity,personaRepository);
        return profesorOutputDto;
    }

    @Override
    public List<ProfesorOutputDto> findByBranch(String branch) {
        List<ProfesorEntity> profesores = profesorRepository.findByBranch(branch);
        if (!profesores.isEmpty()) {
            List<ProfesorOutputDto> profesorOutputDtos = new ArrayList<>();
            for (ProfesorEntity p : profesores) {
                profesorOutputDtos.add(new ProfesorOutputDto(p,personaRepository));
            }
            return profesorOutputDtos;
        } else {
            throw new ErrorException("Profesor con el campo branch: " + branch + " no encontrado.");
        }
    }

    @Override
    public ProfesorOutputDto updateById(ProfesorInputDto profesorInputDto,String id){
        ProfesorEntity profesorEntityNuevo = profesorRepository.findById(id).orElseThrow(() ->
                new ErrorException("Profesor con el id: " + id + " no encontrado."));
        //profesorValido(profesorNuevo);
        profesorEntityNuevo.getProfesor(profesorInputDto,personaRepository);
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesorRepository.save(profesorEntityNuevo),personaRepository);
        return  profesorOutputDto;
    }

    @Override
    public ProfesorOutputDto deleteById(String id){
        ProfesorEntity profesorEntity = profesorRepository.findById(id).orElseThrow(() ->
                new ErrorException("Profesor con el id: " + id + " no encontrado."));
        profesorRepository.deleteById(id);
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesorEntity,personaRepository);
        return  profesorOutputDto;
    }
}
