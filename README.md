# Proyecto-BudgetManeger
Proyecto enfocado en suplir la necesidad de las personas de controlar y gestionar sus finanzas, con la capacidad de plantear metas y tener en cuenta el tiempo.

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![SQLite](https://img.shields.io/badge/SQLite-3-blue.svg)

Aplicación web monolítica para el control, planificación y seguimiento de finanzas personales. Permite registrar ingresos y gastos, definir presupuestos por categoría, establecer metas de ahorro y monitorear el rendimiento financiero mediante reportes consolidados.

---

## Arquitectura del Sistema

El proyecto está diseñado bajo un estilo de **Monolito Clásico en Tres Capas** con **Dominio Aislado**, empaquetado en un único archivo ejecutable (`.jar`).

* **Presentación (SSR):** Vistas dinámicas renderizadas en el servidor mediante **Thymeleaf** e impulsadas por controladores **Spring MVC**.
* **Aplicación:** Servicios de Spring (`@Service`) que coordinan los casos de uso, controlan la seguridad con **Spring Security** y gestionan tareas en segundo plano mediante **`@Scheduled`** (gastos recurrentes y copias de seguridad).
* **Dominio (Reglas Puras):** Clases y lógica de negocio puras (evaluación de límites de presupuesto, avance de metas e impacto financiero) independientes de frameworks y persitencia.
* **Datos y Persistencia:** Mapeo de entidades con **Spring Data JPA / Hibernate** sobre una **base de datos relacional embebida (SQLite)** almacenada en un archivo local (`/data/presupuesto.db`). Las migraciones de esquema se gestionan automáticamente mediante **Flyway**.

---

## Tecnologías a Utilizar

* **Lenguaje:** Java 21 LTS
* **Framework Principal:** Spring Boot 3.x (Spring MVC, Spring Security, Spring Data JPA)
* **Motor de Plantillas:** Thymeleaf + HTML5 / CSS3
* **Base de Datos Embebida:** SQLite (Driver JDBC)
* **Migración de Esquema:** Flyway
* **Servidor Aplicativo:** Apache Tomcat (Embebido)

---

## Vista de desarrollo

<img width="1580" height="941" alt="digrama uml" src="https://github.com/user-attachments/assets/2ebc6462-b847-4398-8ef2-6d18d36b3f72" />  


---

## Vista de despliegue

<img width="1270" height="853" alt="Diagrama en blanco" src="https://github.com/user-attachments/assets/7b94bfbd-d417-40f2-94cf-b1b2b67cef32" />
