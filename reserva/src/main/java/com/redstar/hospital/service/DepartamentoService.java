package com.redstar.hospital.service;

import com.redstar.hospital.model.dto.Departamento;
import com.redstar.hospital.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public void alta(Departamento departamento) {
        departamentoRepository.save(departamento);
    }

    public void modificar(Departamento departamento) {
        departamentoRepository.save(departamento);
    }

    public Departamento consultar(String nombreCorto) {
        return departamentoRepository.findByNombreCorto(nombreCorto);
    }
}
