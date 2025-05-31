package org.example.model;

import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Empleado {

    private Integer id;
    private String nombre;
    private Role cargo;
    private LocalDate fechaContratacion;
    private Double salario;

    public Empleado(String nombre, Role cargo, LocalDate fechaContratacion, Double salario) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
    }

}
