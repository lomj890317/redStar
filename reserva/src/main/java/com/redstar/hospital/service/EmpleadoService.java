package com.redstar.hospital.service;

import com.redstar.hospital.repository.Empleado;
import com.redstar.hospital.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public void alta(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    public void modificar(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    public Empleado consultar(String nombre) {
        return empleadoRepository.findByNombre(nombre);
    }
}

