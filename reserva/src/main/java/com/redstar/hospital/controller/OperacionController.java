package com.redstar.hospital.controller;

import com.redstar.hospital.model.dto.Departamento;
import com.redstar.hospital.model.dto.OperacionRequest;
import com.redstar.hospital.repository.Empleado;
import com.redstar.hospital.service.DepartamentoService;
import com.redstar.hospital.service.EmpleadoService;
import com.redstar.hospital.utils.ParserXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practica/operacion")
public class OperacionController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<?> manejarOperacion(@RequestBody OperacionRequest request) throws Exception {
        String operacion = request.getOperacion();
        String registro = request.getRegistro();
        String datos = request.getDatos();

        if ("areaTrabajo".equalsIgnoreCase(registro)) {
            return manejarDepartamento(operacion, datos);
        } else if ("empleado".equalsIgnoreCase(registro)) {
            return manejarEmpleado(operacion, datos);
        } else {
            return ResponseEntity.badRequest().body("Registro no válido.");
        }
    }

    private ResponseEntity<?> manejarDepartamento(String operacion, String datos) throws Exception {
        Departamento departamento = ParserXML.parse(datos,Departamento.class);

        switch (operacion.toUpperCase()) {
            case "ALTA":
                departamentoService.alta(departamento);
                break;
            case "MODIFICACION":
                departamentoService.modificar(departamento);
                break;
            case "CONSULTA":
                Departamento resultado = departamentoService.consultar(departamento.getNombreCorto());
                return ResponseEntity.ok(resultado);
            default:
                return ResponseEntity.badRequest().body("Operación no válida.");
        }
        return ResponseEntity.ok(departamento);
    }

    private ResponseEntity<?> manejarEmpleado(String operacion, String datos) throws Exception {
        Empleado empleado = ParserXML.parse(datos,Empleado.class);

        switch (operacion.toUpperCase()) {
            case "ALTA":
                empleadoService.alta(empleado);
                break;
            case "MODIFICACION":
                empleadoService.modificar(empleado);
                break;
            case "CONSULTA":
                Empleado resultado = empleadoService.consultar(empleado.getNombre());
                return ResponseEntity.ok(resultado);
            default:
                return ResponseEntity.badRequest().body("Operación no válida.");
        }
        return ResponseEntity.ok(empleado);
    }
}
