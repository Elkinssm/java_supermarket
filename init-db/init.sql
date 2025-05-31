CREATE DATABASE IF NOT EXISTS supermercado
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE supermercado;

CREATE TABLE IF NOT EXISTS empleado (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        nombre VARCHAR(100)
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci NOT NULL,
    cargo  VARCHAR(100)
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci NOT NULL,
    fecha_contratacion DATE,
    salario DECIMAL(10,2),
    INDEX idx_cargo (cargo)
    ) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;



INSERT INTO empleado (nombre, cargo, fecha_contratacion, salario) VALUES
                                                                      ('Maria Gomez',     'Cajero',     '2022-03-15', 1200000.00),
                                                                      ('Luis Perez',      'Supervisor', '2021-11-01', 1000000.00),
                                                                      ('Ana Martinez',    'Gerente',    '2020-06-10', 2500000.00),
                                                                      ('Carlos Sanchez',  'Cajero',     '2023-01-20', 1250000.00),
                                                                      ('Laura Rodriguez', 'Bodega',     '2022-09-05', 1050000.00);
