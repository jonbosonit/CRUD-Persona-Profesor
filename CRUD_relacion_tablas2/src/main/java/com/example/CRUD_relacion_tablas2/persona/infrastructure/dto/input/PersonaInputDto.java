package com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Data
@NoArgsConstructor
public class PersonaInputDto {

    //Integer id;

    @NotNull(message = "Tu campo user no puede ser nulo.")
    @Length(min = 6, max = 10, message = "El campo user tiene que tener mas de 6 caracteres y menos de 10.")
    String user;

    @NotNull(message = "Tu campo password no puede ser nulo.")
    @Length(min=5, max=20, message = "La contraseña tiene que tener mas de 5 caracteres y menos de 20.")
    String password;

    //@NotNull(message = "El campo surname no puede ser nulo.")
    //Se ha dejado como posible nulo para comprobar que en update si mandamos nulo coge el valor del anterior
    String surname;

    @NotNull(message = "Tu campo company_email no puede ser nulo.")
    @Email
    String company_email;

    @NotNull(message = "Tu campo personal_email no puede ser nulo.")
    @Email
    String personal_email;

    @NotNull(message = "Tu campo city no puede ser nulo.")
    @Length(max = 100, message = "El campo city no puede tener mas de 100 caracteres.")
    String city;

    @NotNull(message = "Tu campo active no puede ser nulo.")
    Boolean active;

    @NotNull(message = "Tu campo created_date no puede ser nulo.")
    @DateTimeFormat
    Date created_date;

    @URL
    String imagen_url;

    @DateTimeFormat
    Date termination_date;

}
