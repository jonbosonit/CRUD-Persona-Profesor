package com.example.CRUD_relacion_tablas2.asignatura.infrastructure.repository;

import com.example.CRUD_relacion_tablas2.asignatura.domain.AsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity,String> {
}
