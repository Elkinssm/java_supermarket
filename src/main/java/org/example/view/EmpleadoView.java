package org.example.view;

import org.example.model.Empleado;
import org.example.model.Role;

import java.util.List;
import java.util.Scanner;

public class EmpleadoView {

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n===== EMPLOYEE MANAGEMENT =====");
        System.out.println("1. Register new employee");
        System.out.println("2. Query employees by role");
        System.out.println("3. Show all employees");
        System.out.println("4. Update an employee");
        System.out.println("5. Delete an employee");
        System.out.println("0. Exit");
    }

    public int leerOpcion() {
        while (true) {
            System.out.print("Select an option: ");
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public String[] leerDatosEmpleadoParaCrear() {
        System.out.print("Full name: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Role (");
        Role[] roles = Role.values();
        for (int i = 0; i < roles.length; i++) {
            System.out.print(roles[i]);
            if (i < roles.length - 1) System.out.print(", ");
        }
        System.out.print("): ");
        String cargo = scanner.nextLine().trim();

        System.out.print("Hire date (YYYY-MM-DD): ");
        String fecha = scanner.nextLine().trim();

        System.out.print("Salary (e.g., 1200000.00): ");
        String salario = scanner.nextLine().trim();

        return new String[]{nombre, cargo, fecha, salario};
    }

    public String[] leerDatosEmpleadoParaActualizar() {
        System.out.print("Employee ID to update: ");
        String id = scanner.nextLine().trim();

        System.out.print("New full name: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("New Role (");
        Role[] roles = Role.values();
        for (int i = 0; i < roles.length; i++) {
            System.out.print(roles[i]);
            if (i < roles.length - 1) System.out.print(", ");
        }
        System.out.print("): ");
        String cargo = scanner.nextLine().trim();

        System.out.print("New Hire date (YYYY-MM-DD): ");
        String fecha = scanner.nextLine().trim();

        System.out.print("New Salary (e.g., 1200000.00): ");
        String salario = scanner.nextLine().trim();

        return new String[]{id, nombre, cargo, fecha, salario};
    }

    public String leerCargoParaBuscar() {
        System.out.print("Enter role to search (");
        Role[] roles = Role.values();
        for (int i = 0; i < roles.length; i++) {
            System.out.print(roles[i]);
            if (i < roles.length - 1) System.out.print(", ");
        }
        System.out.print(") or leave empty to list all: ");
        return scanner.nextLine().trim();
    }

    public String leerIdParaEliminar() {
        System.out.print("Employee ID to delete: ");
        return scanner.nextLine().trim();
    }

    public void mostrarEmpleados(String etiqueta, List<Empleado> lista) {
        if (lista.isEmpty()) {
            System.out.println("No employees found for: " + etiqueta);
            return;
        }

        System.out.println("\n--- Employees (" + etiqueta + ") ---");
        System.out.printf("%-5s %-20s %-15s %-15s %-10s%n",
                "ID", "Name", "Role", "Hire Date", "Salary");
        System.out.println("---------------------------------------------------------------");
        for (Empleado e : lista) {
            System.out.printf("%-5d %-20s %-15s %-15s %-10.2f%n",
                    e.getId(), e.getNombre(), e.getCargo(),
                    e.getFechaContratacion(), e.getSalario());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
