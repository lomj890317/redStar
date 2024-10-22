package com.redstar.hospital.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Departamento {
    @Id
    private Integer numeroArea;
    private String departamento;
    private String nombreCorto;
    private String direccion;
}