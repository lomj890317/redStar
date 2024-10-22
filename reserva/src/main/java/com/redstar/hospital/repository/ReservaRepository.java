package com.redstar.hospital.repository;

import com.redstar.hospital.model.dto.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("SELECT r FROM Reserva r WHERE r.idHabitacionSala = :idHabitacionSala " +
            "AND ((:fechaInicio BETWEEN r.fechaInicio AND r.fechaFin) " +
            "OR (:fechaFin BETWEEN r.fechaInicio AND r.fechaFin) " +
            "OR (r.fechaInicio BETWEEN :fechaInicio AND :fechaFin))")
    List<Reserva> findReservasSolapadas(@Param("idHabitacionSala") Long idHabitacionSala,
                                        @Param("fechaInicio") LocalDateTime fechaInicio,
                                        @Param("fechaFin") LocalDateTime fechaFin);
}

