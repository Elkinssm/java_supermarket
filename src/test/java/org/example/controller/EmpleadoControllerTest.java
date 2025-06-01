package org.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.example.model.Empleado;
import org.example.service.EmpleadoService;
import org.example.view.EmpleadoView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class EmpleadoControllerTest {
    private EmpleadoService serviceMock;
    private EmpleadoView viewMock;
    private EmpleadoController controller;

    @BeforeEach
    void setUp() {
        serviceMock = mock(EmpleadoService.class);
        viewMock = mock(EmpleadoView.class);
        controller = new EmpleadoController() {
            {
                this.service = serviceMock;
                this.view = viewMock;
            }
        };
    }

    @Test
    void testRegistrarEmpleado_exito() {
        when(viewMock.leerDatosEmpleadoParaCrear()).thenReturn(new String[] { "Juan", "Cajero", "2024-01-01", "1000" });
        when(serviceMock.crearEmpleado(anyString(), anyString(), anyString(), anyString())).thenReturn(true);

        controller.registrarEmpleado();

        verify(viewMock).mostrarMensaje("Employee registered successfully.");
        verify(serviceMock).crearEmpleado("Juan", "Cajero", "2024-01-01", "1000");
    }

    @Test
    void testRegistrarEmpleado_fallo() {
        when(viewMock.leerDatosEmpleadoParaCrear()).thenReturn(new String[] { "Juan", "Cajero", "2024-01-01", "1000" });
        when(serviceMock.crearEmpleado(anyString(), anyString(), anyString(), anyString())).thenReturn(false);

        controller.registrarEmpleado();

        verify(viewMock).mostrarMensaje("Error registering employee. Check input and try again.");
    }

    @Test
    void testConsultarPorCargo_conCargo() {
        List<Empleado> empleados = Arrays.asList(mock(Empleado.class), mock(Empleado.class));
        when(viewMock.leerCargoParaBuscar()).thenReturn("Cajero");
        when(serviceMock.obtenerEmpleadosPorCargo("Cajero")).thenReturn(empleados);

        controller.consultarPorCargo();

        verify(viewMock).mostrarEmpleados("Cajero", empleados);
    }

    @Test
    void testConsultarPorCargo_sinCargo() {
        List<Empleado> empleados = Collections.singletonList(mock(Empleado.class));
        when(viewMock.leerCargoParaBuscar()).thenReturn("");
        when(serviceMock.obtenerEmpleadosPorCargo(null)).thenReturn(empleados);

        controller.consultarPorCargo();

        verify(viewMock).mostrarEmpleados("All", empleados);
    }

    @Test
    void testMostrarTodos() {
        List<Empleado> empleados = Arrays.asList(mock(Empleado.class), mock(Empleado.class));
        when(serviceMock.obtenerEmpleadosPorCargo(null)).thenReturn(empleados);

        controller.mostrarTodos();

        verify(viewMock).mostrarEmpleados("All", empleados);
    }

    @Test
    void testActualizarEmpleado_exito() {
        when(viewMock.leerDatosEmpleadoParaActualizar())
                .thenReturn(new String[] { "1", "Juan", "Cajero", "2024-01-01", "1200" });
        when(serviceMock.actualizarEmpleado(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        controller.actualizarEmpleado();

        verify(viewMock).mostrarMensaje("Employee updated successfully.");
        verify(serviceMock).actualizarEmpleado("1", "Juan", "Cajero", "2024-01-01", "1200");
    }

    @Test
    void testActualizarEmpleado_fallo() {
        when(viewMock.leerDatosEmpleadoParaActualizar())
                .thenReturn(new String[] { "1", "Juan", "Cajero", "2024-01-01", "1200" });
        when(serviceMock.actualizarEmpleado(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(false);

        controller.actualizarEmpleado();

        verify(viewMock).mostrarMensaje("Error updating employee. Check input and try again.");
    }

    @Test
    void testEliminarEmpleado_exito() {
        when(viewMock.leerIdParaEliminar()).thenReturn("1");
        when(serviceMock.eliminarEmpleado("1")).thenReturn(true);

        controller.eliminarEmpleado();

        verify(viewMock).mostrarMensaje("Employee deleted successfully.");
        verify(serviceMock).eliminarEmpleado("1");
    }

    @Test
    void testEliminarEmpleado_fallo() {
        when(viewMock.leerIdParaEliminar()).thenReturn("1");
        when(serviceMock.eliminarEmpleado("1")).thenReturn(false);

        controller.eliminarEmpleado();

        verify(viewMock).mostrarMensaje("Error deleting employee. Check ID and try again.");
    }
}