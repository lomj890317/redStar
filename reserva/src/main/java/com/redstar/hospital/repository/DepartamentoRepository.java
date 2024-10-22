package com.redstar.hospital.repository;

import com.redstar.hospital.model.dto.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    Departamento findByNombreCorto(String nombreCorto);
}
