package com.redstar.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Empleado findByNombre(String nombre);
}