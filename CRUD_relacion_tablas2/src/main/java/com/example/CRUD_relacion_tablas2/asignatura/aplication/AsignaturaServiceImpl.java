package com.example.CRUD_relacion_tablas2.asignatura.aplication;

import com.example.CRUD_relacion_tablas2.asignatura.domain.AsignaturaEntity;
import com.example.CRUD_relacion_tablas2.asignatura.infrastructure.dto.input.AsignaturaInputDto;
import com.example.CRUD_relacion_tablas2.asignatura.infrastructure.dto.output.AsignaturaOutputDto;
import com.example.CRUD_relacion_tablas2.asignatura.infrastructure.repository.AsignaturaRepository;
import com.example.CRUD_relacion_tablas2.estudiante.infrastructure.repository.EstudianteRepository;
import com.example.CRUD_relacion_tablas2.exception.ErrorException;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AsignaturaServiceImpl implements AsignaturaService{

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    public AsignaturaOutputDto save(AsignaturaInputDto asignaturaInputDto) {
        AsignaturaEntity asignaturaEntity = new AsignaturaEntity();
        asignaturaEntity.getAsignatura(asignaturaInputDto,estudianteRepository);

        AsignaturaOutputDto asignaturaOutputDto = new AsignaturaOutputDto(asignaturaRepository.save(asignaturaEntity),
                estudianteRepository, personaRepository, profesorRepository);

        return asignaturaOutputDto;
    }

    @Override
    public List<AsignaturaOutputDto> findAll() {
        List<AsignaturaOutputDto> asignaturaOutputDtos;

        asignaturaOutputDtos = asignaturaRepository.findAll().stream().map(a ->
                new AsignaturaOutputDto(a,estudianteRepository,personaRepository,profesorRepository)).collect(Collectors.toList());

        return asignaturaOutputDtos;
    }

    @Override
    public AsignaturaOutputDto findById(String id) {

        AsignaturaEntity asignaturaEntity = asignaturaRepository.findById(id).orElseThrow(()->
                new ErrorException("Asignatura con el id: " + id + " no encontrada."));

        AsignaturaOutputDto asignaturaOutputDto = new AsignaturaOutputDto(asignaturaEntity,estudianteRepository,
                personaRepository,profesorRepository);

        return  asignaturaOutputDto;
    }

    @Override
    public AsignaturaOutputDto updateById(String id, AsignaturaInputDto asignaturaInputDto) {

        AsignaturaEntity asignaturaEntityNueva = asignaturaRepository.findById(id).orElseThrow(()->
                new ErrorException("Asignatura con el id: " + id + " no encontrada."));

        asignaturaEntityNueva.getAsignatura(asignaturaInputDto,estudianteRepository);

        AsignaturaOutputDto asignaturaOutputDto = new AsignaturaOutputDto(asignaturaRepository.save(asignaturaEntityNueva),
                estudianteRepository, personaRepository,profesorRepository);

        return asignaturaOutputDto;
    }

    @Override
    public AsignaturaOutputDto deleteById(String id) {
        AsignaturaEntity asignaturaEntity = asignaturaRepository.findById(id).orElseThrow(()->
                new ErrorException("Asignatura con el id: " + id + " no encontrada."));

        asignaturaRepository.deleteById(id);

        AsignaturaOutputDto asignaturaOutputDto = new AsignaturaOutputDto(asignaturaEntity,estudianteRepository,
                personaRepository,profesorRepository);

        return asignaturaOutputDto;
    }

}
