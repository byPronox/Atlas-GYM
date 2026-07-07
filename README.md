# Atlas Gym - Gym Management System 🏋️‍♂️

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![OpenXava](https://img.shields.io/badge/OpenXava-7.7.2-blue.svg)](https://openxava.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-4.13-25A162.svg)](https://junit.org/junit4/)
[![Azure Pipelines](https://img.shields.io/badge/Azure_Pipelines-CI%2FCD-118DFF.svg)](https://azure.microsoft.com/en-us/products/devops/pipelines/)

Atlas Gym is a comprehensive web-based Gym Management System developed to streamline daily operations, track athlete progress, and manage physical assets. Built using the **OpenXava** framework, this project applies a rapid Time-To-Market approach while maintaining robust business logic and domain-driven design.

Developed by **Group 5** for the Software Validation and Verification course at Universidad de las Américas (UDLA).

## 🚀 Features & Modules

The system is divided into five core modules, each secured by role-based access control (Admin / Trainer):

1. **Athletes & Memberships:** Registration, tracking, and automatic expiration calculations for gym plans.
2. **Workout Routines:** Creation of customized routines mapping specific exercises, series, and repetitions to individual athletes.
3. **Group Sessions:** Scheduling of classes with automated capacity limits, avoiding trainer schedule conflicts.
4. **Attendance & Physical Evaluations:** Tracking class attendance and automatic BMI (Body Mass Index) calculation with health risk categorization.
5. **Equipment Inventory:** Lifecycle management of gym machinery using a State Transition model (`OPERATIVE` ➔ `IN_MAINTENANCE` ➔ `DECOMMISSIONED`).

## 🛠️ Tech Stack

* **Backend:** Java 11 (JDK LTS)
* **Framework:** OpenXava 7.7.2 (JPA / Hibernate)
* **Database:** HSQLDB (Embedded for development)
* **Server:** Apache Tomcat 9
* **Build Tool:** Maven
* **Testing:** JUnit 4
* **CI/CD:** Azure Pipelines

## 🧪 Validation & Verification (Testing Strategy)

Quality Assurance is at the core of Atlas Gym. The system has passed a rigorous V&V protocol achieving a 100% success rate:

* **White-Box Testing (Unit Testing):**
    * Achieved 100% Statement and Decision Coverage on complex algorithms (e.g., BMI calculations, Schedule conflicts).
    * Validated Cyclomatic Complexity (M=3 for core validation methods) using JUnit.
* **Black-Box Testing (Acceptance Testing):**
    * **Use Cases:** 65 E2E test cases (Functional, Negative, and Security).
    * **Boundary Value Analysis (BVA):** Applied to session capacity limits to prevent overcrowding.
    * **State Transition Testing:** Validated the strict lifecycle rules of equipment maintenance.

## ⚙️ Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/byPronox/Atlas-GYM.git](https://github.com/byPronox/Atlas-GYM.git)
   cd Atlas-GYM
   ```
2. **Build the project:**
   Ensure you have Maven installed. Run the following command to clean the workspace and build the .war package:
   ```bash
   mvn clean package
   ```
3. **Run the application:**
   Execute the main runner class `org.example.AtlasGym.run.AtlasGym` from your IDE (IntelliJ IDEA / Eclipse).
4. **Access the system:**
   Open your browser and navigate to: `http://localhost:8080/AtlasGym`


---

# Atlas Gym - Sistema de Gestión de Gimnasios 🏋️‍♂️

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![OpenXava](https://img.shields.io/badge/OpenXava-7.7.2-blue.svg)](https://openxava.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-4.13-25A162.svg)](https://junit.org/junit4/)
[![Azure Pipelines](https://img.shields.io/badge/Azure_Pipelines-CI%2FCD-118DFF.svg)](https://azure.microsoft.com/en-us/products/devops/pipelines/)

Atlas Gym es un sistema web integral diseñado para optimizar las operaciones diarias, el seguimiento de atletas y la gestión de activos físicos de un centro de acondicionamiento. Construido con el framework **OpenXava**, este proyecto aplica un enfoque ágil *Time-To-Market* manteniendo una robusta lógica de negocio y un diseño guiado por el dominio (DDD).

Desarrollado por el **Grupo 5** para la asignatura de Validación y Verificación de Software en la Universidad de las Américas (UDLA).

## 🚀 Características y Módulos

El sistema se divide en cinco módulos principales, protegidos por control de acceso basado en roles (Administrador / Entrenador):

1. **Atletas y Membresías:** Registro, seguimiento y cálculo automático de expiración de planes de gimnasio.
2. **Rutinas de Entrenamiento:** Creación de rutinas personalizadas asociando ejercicios específicos, series y repeticiones a atletas individuales.
3. **Sesiones Grupales:** Programación de clases con control automático de aforo máximo y prevención de conflictos de horario de entrenadores.
4. **Asistencia y Evaluaciones Físicas:** Control de asistencia a clases y cálculo automatizado del IMC (Índice de Masa Corporal) con categorización de riesgo de salud.
5. **Inventario de Equipamiento:** Gestión del ciclo de vida de la maquinaria mediante un modelo de Transición de Estados (`OPERATIVO` ➔ `EN_MANTENIMIENTO` ➔ `DADO_DE_BAJA`).

## 🛠️ Stack Tecnológico

* **Backend:** Java 11 (JDK LTS)
* **Framework:** OpenXava 7.7.2 (JPA / Hibernate)
* **Base de Datos:** HSQLDB (Embebida para desarrollo)
* **Servidor:** Apache Tomcat 9
* **Construcción:** Maven
* **Pruebas:** JUnit 4
* **CI/CD:** Azure Pipelines

## 🧪 Validación y Verificación (Estrategia de Testing)

El Aseguramiento de Calidad (QA) es el núcleo de Atlas Gym. El sistema ha superado un riguroso protocolo de V&V logrando un 100% de tasa de éxito:

* **Pruebas de Caja Blanca (Unitarias):**
    * Se alcanzó un 100% de Cobertura de Sentencia y Decisión en algoritmos complejos (ej. cálculos de IMC, conflictos de horario).
    * Se validó la Complejidad Ciclomática (M=3 para métodos centrales de validación) usando JUnit.
* **Pruebas de Caja Negra (Pruebas de Aceptación):**
    * **Casos de Uso:** 65 casos de prueba End-to-End (Funcionales, Negativos y de Seguridad).
    * **Análisis de Valores Límite (BVA):** Aplicado a los límites de aforo de sesiones para prevenir la sobrecapacidad.
    * **Transición de Estados:** Validó las reglas estrictas del ciclo de vida y mantenimiento de los equipos.

## ⚙️ Instalación y Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/byPronox/Atlas-GYM.git](https://github.com/byPronox/Atlas-GYM.git)
   cd Atlas-GYM
   ```
2. **Construir el proyecto:**
   Asegúrate de tener Maven instalado. Ejecuta el siguiente comando para limpiar el espacio de trabajo y construir el paquete .war:
   ```bash
   mvn clean package
   ```
3. **Ejecutar la aplicación:**
   Ejecuta la clase principal `org.example.AtlasGym.run.AtlasGym` desde tu IDE (IntelliJ IDEA / Eclipse).
4. **Acceder al sistema:**
   Abre tu navegador y dirígete a: `http://localhost:8080/AtlasGym`
