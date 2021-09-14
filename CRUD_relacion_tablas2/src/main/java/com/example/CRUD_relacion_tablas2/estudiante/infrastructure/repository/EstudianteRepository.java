package com.example.CRUD_relacion_tablas2.estudiante.infrastructure.repository;

import com.example.CRUD_relacion_tablas2.estudiante.domain.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<EstudianteEntity,String> {
}
