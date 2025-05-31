package org.example.model;

import java.time.LocalDate;

public class Empleado {

    private Integer id;
    private String nombre;
    private Role cargo;
    private LocalDate fechaContratacion;
    private Double salario;

    public Empleado() { }

    public Empleado(String nombre, Role cargo, LocalDate fechaContratacion, Double salario) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
    }

    public Empleado(Integer id, String nombre, Role cargo, LocalDate fechaContratacion, Double salario) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Role getCargo() {
        return cargo;
    }
    public void setCargo(Role cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }
    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Double getSalario() {
        return salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cargo=" + cargo +
                ", fechaContratacion=" + fechaContratacion +
                ", salario=" + salario +
                '}';
    }
}
