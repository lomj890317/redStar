package com.redstar.hospital.service;

import com.redstar.hospital.exception.NotFoundException;
import com.redstar.hospital.model.dto.Reserva;
import com.redstar.hospital.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reserva no encontrada"));
    }

    public Reserva actualizarReserva(Long id, Reserva reserva) {
        Reserva reservaExistente = obtenerReservaPorId(id);
        reservaExistente.setFechaInicio(reserva.getFechaInicio());
        reservaExistente.setFechaFin(reserva.getFechaFin());
        reservaExistente.setIdHabitacionSala(reserva.getIdHabitacionSala());
        reservaExistente.setNombreCliente(reserva.getNombreCliente());
        reservaExistente.setEstadoReserva(reserva.getEstadoReserva());
        return reservaRepository.save(reservaExistente);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public boolean verificarDisponibilidad(String idHabitacionSala,LocalDateTime fechaInicio, LocalDateTime fechaFin ) {
        List<Reserva> reservasSolapadas = reservaRepository.findReservasSolapadas(Long.valueOf(idHabitacionSala), fechaInicio, fechaFin);
        return reservasSolapadas.isEmpty();
    }
}
