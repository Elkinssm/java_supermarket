package org.example.model;

import org.example.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoDAO.class);

    public int guardarEmpleado(Empleado emp) {
        String sql = "INSERT INTO empleado (nombre, cargo, fecha_contratacion, salario) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getCargo().name());
            ps.setDate(3, Date.valueOf(emp.getFechaContratacion()));
            ps.setDouble(4, emp.getSalario());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                logger.warn("No rows inserted in guardarEmpleado().");
                return -1;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    logger.info("Inserted Empleado with ID={}", id);
                    return id;
                }
            }
            return -1;

        } catch (SQLException e) {
            logger.error("Error inserting Empleado: {}", e.getMessage(), e);
            return -1;
        }
    }

    public List<Empleado> buscarPorCargo(Role cargo) {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, cargo, fecha_contratacion, salario FROM empleado WHERE cargo = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cargo.name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String cargoStr = rs.getString("cargo");
                    Role roleBD;
                    try {
                        roleBD = Role.valueOf(cargoStr.toUpperCase());
                    } catch (IllegalArgumentException exEnum) {
                        logger.warn("Unknown role '{}' for empleado id={}. Skipping.", cargoStr, id);
                        continue;
                    }
                    LocalDate fecha = rs.getDate("fecha_contratacion").toLocalDate();
                    Double salario = rs.getDouble("salario");

                    Empleado e = new Empleado(id, nombre, roleBD, fecha, salario);
                    lista.add(e);
                    logger.debug("Found Empleado: {}", e);
                }
            }
        } catch (SQLException e) {
            logger.error("Error in buscarPorCargo(\"{}\"): {}", cargo, e.getMessage(), e);
        }
        return lista;
    }

    public List<Empleado> listarTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, cargo, fecha_contratacion, salario FROM empleado";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String cargoStr = rs.getString("cargo");
                Role roleBD;
                try {
                    roleBD = Role.valueOf(cargoStr.toUpperCase());
                } catch (IllegalArgumentException exEnum) {
                    logger.warn("Unknown role '{}' for empleado id={}. Skipping.", cargoStr, id);
                    continue;
                }
                LocalDate fecha = rs.getDate("fecha_contratacion").toLocalDate();
                Double salario = rs.getDouble("salario");

                Empleado e = new Empleado(id, nombre, roleBD, fecha, salario);
                lista.add(e);
                logger.debug("Listed Empleado: {}", e);
            }
        } catch (SQLException e) {
            logger.error("Error in listarTodos(): {}", e.getMessage(), e);
        }
        return lista;
    }

    public boolean actualizarEmpleado(Empleado emp) {
        String sql = "UPDATE empleado SET nombre = ?, cargo = ?, fecha_contratacion = ?, salario = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getCargo().name());
            ps.setDate(3, Date.valueOf(emp.getFechaContratacion()));
            ps.setDouble(4, emp.getSalario());
            ps.setInt(5, emp.getId());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                logger.warn("No rows updated in actualizarEmpleado() for id={}", emp.getId());
                return false;
            }
            logger.info("Updated Empleado with ID={}", emp.getId());
            return true;

        } catch (SQLException e) {
            logger.error("Error updating Empleado id={}: {}", emp.getId(), e.getMessage(), e);
            return false;
        }
    }

    public boolean eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleado WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                logger.warn("No rows deleted in eliminarEmpleado() for id={}", id);
                return false;
            }
            logger.info("Deleted Empleado with ID={}", id);
            return true;

        } catch (SQLException e) {
            logger.error("Error deleting Empleado id={}: {}", id, e.getMessage(), e);
            return false;
        }
    }

    public Empleado buscarPorId(int id) {
        String sql = "SELECT id, nombre, cargo, fecha_contratacion, salario FROM empleado WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre   = rs.getString("nombre");
                    String cargoStr = rs.getString("cargo");
                    Role roleBD;
                    try {
                        roleBD = Role.valueOf(cargoStr.toUpperCase());
                    } catch (IllegalArgumentException ex) {
                        logger.warn("Unknown role '{}' for empleado id={}. Returning null.", cargoStr, id);
                        roleBD = null;
                    }
                    LocalDate fecha = rs.getDate("fecha_contratacion").toLocalDate();
                    Double salario  = rs.getDouble("salario");

                    return new Empleado(id, nombre, roleBD, fecha, salario);
                }
            }
        } catch (SQLException e) {
            logger.error("Error en buscarPorId({}): {}", id, e.getMessage(), e);
        }
        return null;
    }
}
