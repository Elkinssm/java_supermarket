package org.example.controller;

import org.example.model.Empleado;
import org.example.service.EmpleadoService;
import org.example.view.EmpleadoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmpleadoController {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    EmpleadoService service;
    EmpleadoView view;

    public EmpleadoController() {
        this.service = new EmpleadoService();
        this.view = new EmpleadoView();
    }



    public void iniciar() {
        int opcion;
        do {
            view.mostrarMenu();
            opcion = view.leerOpcion();
            switch (opcion) {
                case 1:
                    registrarEmpleado();
                    break;
                case 2:
                    consultarPorCargo();
                    break;
                case 3:
                    mostrarTodos();
                    break;
                case 4:
                    actualizarEmpleado();
                    break;
                case 5:
                    eliminarEmpleado();
                    break;
                case 0:
                    view.mostrarMensaje("Exiting... Goodbye!");
                    logger.info("User selected Exit. Shutting down app.");
                    break;
                default:
                    view.mostrarMensaje("Invalid option. Try again.");
                    logger.warn("Invalid menu option: {}", opcion);
            }
        } while (opcion != 0);
    }

    public void registrarEmpleado() {
        String[] datos = view.leerDatosEmpleadoParaCrear();
        String nombre = datos[0];
        String cargo = datos[1];
        String fecha = datos[2];
        String salario = datos[3];

        logger.debug("Register input: name='{}', role='{}', date='{}', salary='{}'",
                nombre, cargo, fecha, salario);

        boolean exito = service.crearEmpleado(nombre, cargo, fecha, salario);
        if (exito) {
            view.mostrarMensaje("Employee registered successfully.");
            logger.info("Employee '{}' with role '{}' registered.", nombre, cargo);
        } else {
            view.mostrarMensaje("Error registering employee. Check input and try again.");
            logger.error("Failed to create employee: name='{}', role='{}'.", nombre, cargo);
        }
    }

    public void consultarPorCargo() {
        String cargoInput = view.leerCargoParaBuscar();
        if (cargoInput.isBlank()) {
            mostrarTodos();
            return;
        }

        List<Empleado> lista = service.obtenerEmpleadosPorCargo(cargoInput);
        view.mostrarEmpleados(cargoInput, lista);
        logger.info("Displayed {} employees for role='{}'.", lista.size(), cargoInput);
    }

    void mostrarTodos() {
        List<Empleado> lista = service.obtenerEmpleadosPorCargo(null);
        view.mostrarEmpleados("All", lista);
        logger.info("Displayed all employees: count={}.", lista.size());
    }

    void actualizarEmpleado() {
        String[] datos = view.leerDatosEmpleadoParaActualizar();
        String id = datos[0];
        String nombre = datos[1];
        String cargo = datos[2];
        String fecha = datos[3];
        String salario = datos[4];

        logger.debug("Update input: id='{}', name='{}', role='{}', date='{}', salary='{}'",
                id, nombre, cargo, fecha, salario);

        boolean exito = service.actualizarEmpleado(id, nombre, cargo, fecha, salario);
        if (exito) {
            view.mostrarMensaje("Employee updated successfully.");
            logger.info("Employee ID '{}' updated.", id);
        } else {
            view.mostrarMensaje("Error updating employee. Check input and try again.");
            logger.error("Failed to update employee ID='{}'.", id);
        }
    }

    public void eliminarEmpleado() {
        String id = view.leerIdParaEliminar();
        logger.debug("Delete input: id='{}'.", id);

        boolean exito = service.eliminarEmpleado(id);
        if (exito) {
            view.mostrarMensaje("Employee deleted successfully.");
            logger.info("Employee ID '{}' deleted.", id);
        } else {
            view.mostrarMensaje("Error deleting employee. Check ID and try again.");
            logger.error("Failed to delete employee ID='{}'.", id);
        }
    }
}
