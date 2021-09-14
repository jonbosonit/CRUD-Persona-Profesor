package com.example.CRUD_relacion_tablas2.persona.domain;

import com.example.CRUD_relacion_tablas2.estudiante.domain.EstudianteEntity;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.input.PersonaInputDto;
import com.example.CRUD_relacion_tablas2.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
public class PersonaEntity {

    @GeneratedValue
    @Id
    Integer id;

    @Column
    String user;

    @Column
    String password;

    @Column
    String surname;

    @Column
    String company_email;

    @Column
    String personal_email;

    @Column
    String city;

    @Column
    Boolean active;

    @Column
    Date created_date;

    @Column
    String imagen_url;

    @Column
    Date termination_date;

    @OneToOne(mappedBy = "personaEntity", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    ProfesorEntity profesorEntity;

    @OneToOne(mappedBy = "personaEntity", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    EstudianteEntity estudianteEntity;

    public void getPersona(PersonaInputDto personaInputDto){
        if(personaInputDto.getUser() != null) setUser(personaInputDto.getUser());
        if(personaInputDto.getActive() != null) setActive(personaInputDto.getActive());
        if(personaInputDto.getCity() != null) setCity(personaInputDto.getCity());
        if(personaInputDto.getCompany_email() != null) setCompany_email(personaInputDto.getCompany_email());
        if(personaInputDto.getPersonal_email() != null) setPersonal_email(personaInputDto.getPersonal_email());
        if(personaInputDto.getCreated_date() != null) setCreated_date(personaInputDto.getCreated_date());
        if(personaInputDto.getPassword() != null) setPassword(personaInputDto.getPassword());
        if(personaInputDto.getImagen_url() != null) setImagen_url(personaInputDto.getImagen_url());
        if(personaInputDto.getSurname() != null) setSurname(personaInputDto.getSurname());
        if(personaInputDto.getTermination_date() != null) setTermination_date(personaInputDto.getTermination_date());
    }

}
