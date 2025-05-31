package org.example.service;

import org.example.model.Empleado;
import org.example.model.EmpleadoDAO;
import org.example.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class EmpleadoService {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoService.class);

    private final EmpleadoDAO dao;

    public EmpleadoService() {
        this.dao = new EmpleadoDAO();
    }

    public boolean crearEmpleado(String nombre, String cargoStr, String fechaContratacion, String salarioStr) {
        try {
            Role cargo = Role.valueOf(cargoStr.toUpperCase());
            LocalDate fecha = LocalDate.parse(fechaContratacion);
            Double salario = Double.parseDouble(salarioStr);

            Empleado emp = new Empleado(nombre, cargo, fecha, salario);
            int id = dao.guardarEmpleado(emp);
            return id != -1;
        } catch (IllegalArgumentException e) {
            logger.error("Invalid role or format: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("Error parsing or saving Empleado: {}", e.getMessage(), e);
            return false;
        }
    }

    public List<Empleado> obtenerEmpleadosPorCargo(String cargoStr) {
        if (cargoStr == null || cargoStr.isBlank()) {
            return dao.listarTodos();
        } else {
            try {
                Role cargo = Role.valueOf(cargoStr.toUpperCase());
                return dao.buscarPorCargo(cargo);
            } catch (IllegalArgumentException e) {
                logger.error("Invalid role '{}'", cargoStr);
                return List.of();
            }
        }
    }

    public Empleado buscarEmpleadoPorId(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            return dao.buscarPorId(id);
        } catch (NumberFormatException e) {
            logger.error("Invalid ID format in buscarEmpleadoPorId: {}", idStr);
            return null;
        }
    }

    public boolean actualizarEmpleado(
            String idStr,
            String nombreInput,
            String cargoInput,
            String fechaInput,
            String salarioInput
    ) {

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            logger.error("ID inválido: {}", idStr);
            return false;
        }


        Empleado existente = dao.buscarPorId(id);
        if (existente == null) {
            logger.warn("No existe empleado con ID={}", id);
            return false;
        }


        String nombreFinal = nombreInput.isBlank() ? existente.getNombre() : nombreInput;

        Role cargoFinal = existente.getCargo();
        if (!cargoInput.isBlank()) {
            try {
                cargoFinal = Role.valueOf(cargoInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.error("Rol inválido: {}", cargoInput);
                return false;
            }
        }

        LocalDate fechaFinal = existente.getFechaContratacion();
        if (!fechaInput.isBlank()) {
            try {
                fechaFinal = LocalDate.parse(fechaInput);
            } catch (Exception e) {
                logger.error("Fecha inválida: {}", fechaInput);
                return false;
            }
        }

        Double salarioFinal = existente.getSalario();
        if (!salarioInput.isBlank()) {
            try {
                salarioFinal = Double.parseDouble(salarioInput);
            } catch (NumberFormatException e) {
                logger.error("Salario inválido: {}", salarioInput);
                return false;
            }
        }


        Empleado actualizado = new Empleado(id, nombreFinal, cargoFinal, fechaFinal, salarioFinal);
        boolean exito = dao.actualizarEmpleado(actualizado);
        if (!exito) {
            logger.error("Fallo al actualizar empleado ID={}", id);
        }
        return exito;
    }

    public boolean eliminarEmpleado(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            return dao.eliminarEmpleado(id);
        } catch (NumberFormatException e) {
            logger.error("Invalid ID format: {}", e.getMessage());
            return false;
        }
    }
}
