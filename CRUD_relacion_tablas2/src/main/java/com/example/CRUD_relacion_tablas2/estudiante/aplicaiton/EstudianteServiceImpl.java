package com.example.CRUD_relacion_tablas2.estudiante.aplicaiton;

import com.example.CRUD_relacion_tablas2.estudiante.domain.EstudianteEntity;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.input.EstudianteInputDto;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.dto.output.EstudianteOutputDto;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.repository.EstudianteRepository;
import com.example.CRUD_relacion_tablas2.exception.ErrorException;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstudianteServiceImpl implements EstudianteService{

    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    public EstudianteOutputDto save(EstudianteInputDto estudianteInputDto) {

        EstudianteEntity estudianteEntity = new EstudianteEntity();
        estudianteEntity.getEstudiante(estudianteInputDto,personaRepository,profesorRepository);

        EstudianteOutputDto estudianteOutputDto = new EstudianteOutputDto(estudianteRepository.save(estudianteEntity),
                personaRepository,profesorRepository);

        return estudianteOutputDto;
    }

    @Override
    public List<EstudianteOutputDto> findAll() {
        List<EstudianteOutputDto> estudianteOutputDtos;

        estudianteOutputDtos = estudianteRepository.findAll().stream().map(e ->
                new EstudianteOutputDto(e,personaRepository,profesorRepository)).collect(Collectors.toList());

        return estudianteOutputDtos;
    }

    @Override
    public EstudianteOutputDto findById(String id) {

        EstudianteEntity estudianteEntity = estudianteRepository.findById(id).orElseThrow(()->
                new ErrorException("Estudiante con el id: " + id + " no encontrado."));

        EstudianteOutputDto estudianteOutputDto = new EstudianteOutputDto(estudianteEntity,personaRepository,profesorRepository);

        return estudianteOutputDto;
    }

    @Override
    public EstudianteOutputDto updateById(String id, EstudianteInputDto estudianteInputDto) {

        EstudianteEntity estudianteEntityNuevo = estudianteRepository.findById(id).orElseThrow(() ->
                new ErrorException("Estudiante con el id: " + id + " no encontrado."));

        estudianteEntityNuevo.getEstudiante(estudianteInputDto,personaRepository,profesorRepository);

        EstudianteOutputDto estudianteOutputDto = new EstudianteOutputDto(estudianteRepository.save(estudianteEntityNuevo)
                ,personaRepository,profesorRepository);

        return  estudianteOutputDto;
    }

    @Override
    public EstudianteOutputDto deleteById(String id) {

        EstudianteEntity estudianteEntity = estudianteRepository.findById(id).orElseThrow(() ->
                new ErrorException("Estudiante con el id: " + id + " no encontrado."));

        estudianteRepository.deleteById(id);
        EstudianteOutputDto estudianteOutputDto = new EstudianteOutputDto(estudianteEntity,personaRepository,profesorRepository);
        return  estudianteOutputDto;
    }
}
