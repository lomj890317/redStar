package com.redstar.hospital.controller;

import com.redstar.hospital.exception.NotFoundException;
import com.redstar.hospital.model.dto.Reserva;
import com.redstar.hospital.repository.ReservaRepository;
import com.redstar.hospital.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @PutMapping("/{id}")
    public Reserva updateReserva(@PathVariable Long id, @RequestBody Reserva reservaDetails) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new NotFoundException("Reserva no encontrada"));
        reserva.setFechaInicio(reservaDetails.getFechaInicio());
        reserva.setFechaFin(reservaDetails.getFechaFin());
        return reservaRepository.save(reserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReserva(@PathVariable Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new NotFoundException("Reserva no encontrada"));
        reservaRepository.delete(reserva);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verificar")
    public ResponseEntity<String> verificarDisponibilidad(@RequestParam String sala,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {

        if (reservaService
                .verificarDisponibilidad(
                        sala, fechaFin, fechaInicio)) {
            return ResponseEntity.ok("La habitación está disponible en el rango de fechas proporcionado.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La habitación no está disponible en el rango de fechas proporcionado.");
        }

    }
}
