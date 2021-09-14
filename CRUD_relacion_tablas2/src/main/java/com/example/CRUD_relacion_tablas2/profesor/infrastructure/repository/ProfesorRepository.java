package com.example.CRUD_relacion_tablas2.profesor.infrastructure.repository;

import com.example.CRUD_relacion_tablas2.profesor.domain.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorRepository extends JpaRepository<ProfesorEntity,String> {
    List<ProfesorEntity> findByBranch(String branch);
}
