package com.example.CRUD_relacion_tablas2.persona.aplication;

import com.example.CRUD_relacion_tablas2.exception.ErrorException;
import com.example.CRUD_relacion_tablas2.persona.domain.PersonaEntity;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.input.PersonaInputDto;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output.PersonaOutputDto;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output.PersonaSimpleOutputDto;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.repository.PersonaRepository;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Component
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //List<PersonaOutputDto> personaOutputDtos;

    @Override
    public PersonaSimpleOutputDto save(PersonaInputDto personaInputDto)  {
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.getPersona(personaInputDto);
        personaValida(personaEntity);
        PersonaSimpleOutputDto personaOutputDto = new PersonaSimpleOutputDto(personaRepository.save(personaEntity));
        return personaOutputDto;
    }

    @Override
    public List<PersonaOutputDto> findAll() {
        List<PersonaOutputDto> personaOutputDtos;
        personaOutputDtos = personaRepository.findAll().stream().map(p ->
                new PersonaOutputDto(p,personaRepository,profesorRepository)).collect(Collectors.toList());
        return personaOutputDtos;
    }

    @Override
    public PersonaSimpleOutputDto findById(int id,String type) {
        PersonaEntity personaEntity = personaRepository.findById(id).orElseThrow(() ->
                new ErrorException("Persona con el id: " + id + " no encontrada."));

        PersonaSimpleOutputDto personaOutputDto;
        if(type.equals("SIMPLE")) {
            personaOutputDto = new PersonaSimpleOutputDto(personaEntity);
        } else personaOutputDto = new PersonaOutputDto(personaEntity,personaRepository,profesorRepository);

        return personaOutputDto;
    }

    @Override
    public List<PersonaOutputDto> findByUser(String user) {
        List<PersonaEntity> personaEntities = personaRepository.findByUser(user);
        if (!personaEntities.isEmpty()) {
            List<PersonaOutputDto> personaOutputDtos = new ArrayList<>();
            for (PersonaEntity p : personaEntities) {
                personaOutputDtos.add(new PersonaOutputDto(p,personaRepository,profesorRepository));
            }
            return personaOutputDtos;
        } else {
            throw new ErrorException("Persona con el campo user: " + user + " no encontrada.");
        }
    }

    @Override
    public PersonaOutputDto deleteById(int id) {
        PersonaEntity personaEntity = personaRepository.findById(id).orElseThrow(() ->
                new ErrorException("Persona con el id: " + id + " no encontrada."));
        personaRepository.deleteById(id);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(personaEntity,personaRepository,profesorRepository);
        return personaOutputDto;
    }

    @Override
    public PersonaOutputDto updateById(int id, PersonaInputDto personaInputDto) {

        PersonaEntity personaEntityNueva = personaRepository.findById(id).orElseThrow(() ->
                new ErrorException("Persona con el id: " + id + " no encontrada."));

        personaValida(personaEntityNueva);
        personaEntityNueva.getPersona(personaInputDto);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(personaRepository.save(personaEntityNueva),personaRepository,profesorRepository);

        return personaOutputDto;
    }


    private void personaValida(PersonaEntity personaEntity) {

        if(!(personaEntity.getTermination_date() == null)){
            if(personaEntity.getTermination_date().before(personaEntity.getCreated_date())){
                throw new ErrorException("El campo termination_date " +
                        "no puede ser anterior al campo created_date.");
            }
        }

        if(!checkEmail(personaEntity.getCompany_email())){
            throw new ErrorException("Tu campo company_email no es valido.");
        }

        if(!checkEmail(personaEntity.getPersonal_email())){
            throw new ErrorException("Tu campo personal_email no es valido.");
        }
    }

    public boolean checkEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
