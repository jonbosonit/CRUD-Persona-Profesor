package com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output;

import com.example.CRUD_relacion_tablas2.persona.domain.PersonaEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class PersonaSimpleOutputDto {

    Integer id;
    String user;
    String password;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;

    public PersonaSimpleOutputDto(PersonaEntity personaEntity){
        setActive(personaEntity.getActive());
        setId(personaEntity.getId());
        setCity(personaEntity.getCity());
        setCompany_email(personaEntity.getCompany_email());
        setPersonal_email(personaEntity.getPersonal_email());
        setCreated_date(personaEntity.getCreated_date());
        setTermination_date(personaEntity.getTermination_date());
        setImagen_url(personaEntity.getImagen_url());
        setPassword(personaEntity.getPassword());
        setSurname(personaEntity.getSurname());
        setUser(personaEntity.getUser());
    }

}
