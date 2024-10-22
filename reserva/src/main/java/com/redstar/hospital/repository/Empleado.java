package com.redstar.hospital.repository;

import com.redstar.hospital.model.dto.Departamento;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
public class Empleado {
    @Id
    private Integer numeroEmpleado;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;

    @ManyToOne
    private Departamento areaTrabajo;

}
