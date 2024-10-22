package com.redstar.hospital.model.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "RESERVA")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FECHA_INICIO", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "FECHA_FIN", nullable = false)
    private LocalDateTime fechaFin;

    @Column(name = "ID_HABITACION_SALA", nullable = false)
    private Long idHabitacionSala;

    @Column(name = "NOMBRE_CLIENTE", nullable = false)
    private String nombreCliente;

    @Column(name = "ESTADO_RESERVA", nullable = false)
    private String estadoReserva;



}