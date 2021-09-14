package com.example.CRUD_relacion_tablas2.persona.infrastructure.repository;

import com.example.CRUD_relacion_tablas2.persona.domain.PersonaEntity;
import com.example.CRUD_relacion_tablas2.persona.infrastructure.dto.output.PersonaOutputDto;
import com.example.CRUD_relacion_tablas2.profesor.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.CRUD_relacion_tablas2.persona.infrastructure.controller.PersonaController.*;

public class PersonaRepositoryImpl {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @PersistenceContext
    EntityManager entityManager;


    public List<PersonaOutputDto> getData(HashMap<String, Object> conditions) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonaEntity> query = cb.createQuery(PersonaEntity.class);
        Root<PersonaEntity> root = query.from(PersonaEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field,value) ->
        {
            switch (field)
            {
                case "user":
                case "city":
                case "surname":
                    predicates.add(cb.like(root.get(field), "%" + (String)value + "%"));
                    break;
                case "created_date":
                    String dateCondition = (String) conditions.get("dateCondition");
                    switch (dateCondition)
                    {
                        case GREATER_THAN:
                            predicates.add(cb.greaterThan(root.<Date>get(field),(Date)value));
                            break;
                        case LESS_THAN:
                            predicates.add(cb.lessThan(root.<Date>get(field),(Date)value));
                            break;
                        case EQUAL:
                            predicates.add(cb.equal(root.<Date>get(field),(Date)value));
                            break;
                    }
                    break;
            }

        });

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        String campoOrden=(String) conditions.get("orden");
        if(!campoOrden.isEmpty()){
            query.orderBy(cb.asc(root.get(campoOrden)));
        }

        Integer pageNumber=(Integer) conditions.get("page");
        int primero = ((pageNumber-1)*10) +1;
        int ultimo = primero + 9;

        return entityManager.createQuery(query).setMaxResults(ultimo).setFirstResult(primero-1).getResultList().stream().map(personaOutputDtos ->
                new PersonaOutputDto(personaOutputDtos,personaRepository,profesorRepository)).collect(Collectors.toList());

    }

}
