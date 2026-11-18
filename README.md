# Proyecto-BudgetManager
Proyecto enfocado en suplir la necesidad de las personas de controlar y gestionar sus finanzas, con la capacidad de plantear metas y tener en cuenta el tiempo.

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![SQLite](https://img.shields.io/badge/SQLite-3-blue.svg)

Integrantes: 
Juan Sebastian Peñuela - jupenuelad@unal.edu.co
Juan Kandula Restrepo - juarestrepoba@unal.edu.co
Julián Andrés Sepulveda - jsepulvedacr@unal.edu.co
Alejandro Jimenez - aljimenezp@unal.edu.co

Aplicación web monolítica para el control, planificación y seguimiento de finanzas personales. Permite registrar ingresos y gastos, definir presupuestos por categoría, establecer metas de ahorro y monitorear el rendimiento financiero mediante reportes consolidados.

---

## Arquitectura del Sistema

El proyecto está diseñado bajo un estilo de **Monolito Clásico en Tres Capas** con **Dominio Aislado**, empaquetado en un único archivo ejecutable (`.jar`).

* **Presentación (SSR):** Vistas dinámicas renderizadas en el servidor mediante **Thymeleaf** e impulsadas por controladores **Spring MVC**.
* **Aplicación:** Servicios de Spring (`@Service`) que coordinan los casos de uso, controlan la seguridad con **Spring Security** y gestionan tareas en segundo plano mediante **`@Scheduled`** (gastos recurrentes y copias de seguridad).
* **Dominio (Reglas Puras):** Clases y lógica de negocio puras (evaluación de límites de presupuesto, avance de metas e impacto financiero) independientes de frameworks y persitencia.
* **Datos y Persistencia:** Mapeo de entidades con **Spring Data JPA / Hibernate** sobre una **base de datos relacional embebida (SQLite)** almacenada en un archivo local (`/data/budgetmanager.db`). Las migraciones de esquema se gestionan automáticamente mediante **Flyway**.

---

## Tecnologías a Utilizar

* **Lenguaje:** Java 21 LTS
* **Framework Principal:** Spring Boot 3.x (Spring MVC, Spring Security, Spring Data JPA)
* **Motor de Plantillas:** Thymeleaf + HTML5 / CSS3
* **Base de Datos Embebida:** SQLite (Driver JDBC)
* **Migración de Esquema:** Flyway
* **Servidor Aplicativo:** Apache Tomcat (Embebido)

---

## ¿Cómo ejecutar el proyecto?

### Windows
1. Haz doble clic en el archivo `run.bat` (o ejecútalo desde la terminal).
2. Abre tu navegador en: `http://localhost:8080`.

### Mac / Linux
1. Abre una terminal en la carpeta del proyecto.
2. Ejecuta: `./run.sh` (la primera vez quizás necesites ejecutar `chmod +x run.sh`).
3. Abre tu navegador en: `http://localhost:8080`.

## Usuarios de prueba
- **Admin**: `admin@unal.edu.co` / `Admin1234`
- O crea tu propio usuario en la pantalla de registro.
