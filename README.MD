#  Supermarket - Employee Management System

Este proyecto es una aplicación Java de consola basada en el patrón **Modelo-Vista-Controlador (MVC)**. Permite consultar, registrar, modificar y eliminar empleados de un supermercado, filtrando por cargo. Utiliza una base de datos MySQL en Docker y carga inicial de datos.

---

##  Características principales

- Consulta de empleados por **cargo** (Cajero, Gerente, Supervisor, etc.)
- Registro, actualización y eliminación de empleados
- Actualización de información con campos opcionales
- Separación clara de capas (MVC)
- Acceso a base de datos mediante JDBC
- Pruebas unitarias con **JUnit 5** y **Mockito**
- Logging con **SLF4J + Logback**
- Base de datos MySQL configurada vía **Docker Compose**
- Datos iniciales cargados desde `init.sql` (UTF-8)

---

##  Estructura del proyecto

```
supermarket/
├── init-db/
│   └── init.sql
├── logs/
│   └── app.log
├── src/
│   ├── main/
│   │   ├── java/org.example/
│   │   │   ├── controller/EmpleadoController.java
│   │   │   ├── model/Empleado.java
│   │   │   ├── model/EmpleadoDAO.java
│   │   │   ├── model/Role.java
│   │   │   ├── service/EmpleadoService.java
│   │   │   ├── util/DBConnection.java
│   │   │   ├── view/EmpleadoView.java
│   │   │   └── view/Main.java
│   │   └── resources/logback.xml
│   └── test/
│       └── java/org.example.controller/EmpleadoControllerTest.java
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

##  Tecnologías utilizadas

- **Java 17+**
- **Maven**
- **MySQL 8** (en Docker)
- **JUnit 5 + Mockito**
- **SLF4J + Logback**
- **Docker Compose**

---

## ⚙️ Cómo ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/Elkinssm/java_supermarket
cd supermarket
```

### 2. Levantar la base de datos con Docker

```bash
docker-compose up -d
```
Esto iniciará un contenedor MySQL y ejecutará `init.sql` para crear la base de datos y la tabla de empleados.

### 3. Ejecutar la aplicación

Desde tu IDE o terminal:

```bash
mvn clean compile exec:java -Dexec.mainClass="org.example.Main"
```
Asegúrate de tener Java 17+ y Maven instalados.

---

##  Ejecutar pruebas

```bash
mvn test
```
Las pruebas unitarias están ubicadas en `EmpleadoControllerTest` y utilizan Mockito para simular la vista y el servicio.

---

##  Notas

- El patrón MVC garantiza que el código sea escalable y mantenible.
- La codificación UTF-8 evita errores de visualización con nombres que contienen tildes o caracteres especiales.
- El sistema está diseñado para ser ejecutado como una aplicación de consola, usando `Scanner` para la interacción con el usuario.

---

##  Ejemplo de menú


![Menu](/img/menu.png)


---

## Créditos

- Desarrollado por [Elkin Silva Manrique](https://github.com/Elkinssm).

---

¡Contribuciones y sugerencias son bienvenidas!