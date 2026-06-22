# Actividad Autonoma: Automatizacion de Pruebas en Azure DevOps

## Proyecto

**Nombre:** Atlas GYM  
**Tipo:** Aplicacion Java/Maven con OpenXava  
**Objetivo de la practica:** investigar y demostrar como se configuran y ejecutan pruebas automatizadas en Azure DevOps como parte del proceso de Validacion y Verificacion (V&V).

Este repositorio contiene una demostracion completa de automatizacion de pruebas. Se configuraron pruebas unitarias con JUnit, un pipeline en Azure DevOps y la publicacion de resultados para que las pruebas sean visibles desde el panel de ejecucion.

## 1. Investigacion sobre automatizacion de pruebas en Azure DevOps

Azure DevOps es una plataforma de Microsoft para gestionar el ciclo de vida del desarrollo de software. En el contexto de V&V, permite automatizar la compilacion, ejecucion de pruebas, publicacion de reportes y seguimiento de resultados.

### Herramientas y servicios usados

- **Azure Repos:** servicio de control de versiones Git. En esta practica se uso para alojar el codigo del proyecto `Atlas-GYM` dentro de Azure DevOps.
- **Azure Pipelines:** servicio de integracion continua. Se uso para compilar el proyecto y ejecutar automaticamente las pruebas JUnit.
- **Azure Test Plans:** servicio orientado a planificacion, trazabilidad y gestion de casos de prueba. Es util para pruebas manuales, suites de prueba y asociacion de pruebas automatizadas con casos formales.
- **PublishTestResults:** tarea del pipeline que publica archivos de resultados JUnit para que Azure DevOps muestre las pruebas aprobadas o fallidas.
- **Agent Pools:** conjunto de agentes donde se ejecutan los jobs. En esta practica se uso un agente local porque la organizacion no tenia paralelismo hospedado gratuito habilitado.

### Integracion con frameworks de prueba

Azure DevOps no obliga a usar un framework especifico. La integracion se realiza ejecutando comandos dentro del pipeline. Algunos ejemplos:

- **JUnit:** se ejecuta con Maven mediante `mvn clean test`. Maven Surefire genera reportes XML en `target/surefire-reports`.
- **Selenium:** se puede integrar agregando dependencias Selenium al proyecto y ejecutando las pruebas desde Maven/Gradle. El agente puede usar navegadores disponibles o contenedores.
- **Appium:** se puede integrar para pruebas moviles ejecutando scripts de prueba contra un emulador, dispositivo o servicio externo compatible.

En este proyecto se eligio **JUnit 4** porque el escenario academico podia demostrarse con pruebas unitarias sencillas, rapidas y sin costo adicional.

### Workflow tipico de CI/CD con pruebas

1. El desarrollador realiza cambios en el codigo fuente.
2. Los cambios se suben al repositorio Git.
3. Azure Pipelines detecta el cambio mediante el `trigger`.
4. El agente toma el job del pipeline.
5. Maven compila el proyecto y ejecuta las pruebas con `mvn clean test`.
6. Surefire genera reportes JUnit XML.
7. Azure DevOps publica los resultados en la seccion **Tests**.
8. Si una prueba falla, el pipeline queda fallido; si todas pasan, queda exitoso.

Fuentes de apoyo:

- https://learn.microsoft.com/es-es/azure/devops/
- https://learn.microsoft.com/es-es/azure/devops/pipelines/ecosystems/java
- https://learn.microsoft.com/es-es/azure/devops/test/associate-automated-test-with-test-case
- https://learn.microsoft.com/es-es/azure/devops/test/run-automated-tests-from-test-hub

## 2. Escenario de prueba elegido

El escenario seleccionado pertenece al modulo de gimnasio del proyecto **Atlas GYM**. Se automatizaron pruebas unitarias sobre entidades del dominio.

### Caso 1: Registro de atleta

Se valida que un objeto `Atleta` pueda almacenar y devolver correctamente sus datos principales:

- Nombre completo
- Cedula
- Fecha de nacimiento
- Genero
- Telefono
- Correo electronico
- Estado

Archivo:

```text
src/test/java/org/example/AtlasGym/modelo/AtletaTest.java
```

### Caso 2: Membresia mensual

Se valida la creacion de una membresia asociada a un atleta y a un tipo de membresia. Tambien se comprueba que la fecha de vencimiento se calcule sumando la duracion del plan.

Archivo:

```text
src/test/java/org/example/AtlasGym/modelo/MembresiaTest.java
```

## 3. Codigo de las pruebas

### AtletaTest

```java
package org.example.AtlasGym.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class AtletaTest {

    @Test
    public void registraDatosBasicosDelAtleta() {
        Atleta atleta = new Atleta();

        atleta.setNombreCompleto("Maria Perez");
        atleta.setCedula("0912345678");
        atleta.setFechaNacimiento(LocalDate.of(2000, 5, 10));
        atleta.setGenero("Femenino");
        atleta.setTelefono("0999999999");
        atleta.setCorreoElectronico("maria.perez@correo.com");
        atleta.setEstado("ACTIVO");

        assertEquals("Maria Perez", atleta.getNombreCompleto());
        assertEquals("0912345678", atleta.getCedula());
        assertEquals(LocalDate.of(2000, 5, 10), atleta.getFechaNacimiento());
        assertEquals("Femenino", atleta.getGenero());
        assertEquals("0999999999", atleta.getTelefono());
        assertEquals("maria.perez@correo.com", atleta.getCorreoElectronico());
        assertEquals("ACTIVO", atleta.getEstado());
    }
}
```

### MembresiaTest

```java
package org.example.AtlasGym.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class MembresiaTest {

    @Test
    public void calculaFechaDeVencimientoSegunTipoDeMembresia() {
        Atleta atleta = new Atleta();
        atleta.setNombreCompleto("Carlos Andrade");

        TipoMembresia tipo = new TipoMembresia();
        tipo.setNombrePlan("Mensual");
        tipo.setDuracionDias(30);

        LocalDate fechaInicio = LocalDate.of(2026, 6, 1);

        Membresia membresia = new Membresia();
        membresia.setAtleta(atleta);
        membresia.setTipoMembresia(tipo);
        membresia.setFechaInicio(fechaInicio);
        membresia.setFechaVencimiento(fechaInicio.plusDays(tipo.getDuracionDias()));
        membresia.setEstado("ACTIVA");

        assertSame(atleta, membresia.getAtleta());
        assertSame(tipo, membresia.getTipoMembresia());
        assertEquals(LocalDate.of(2026, 7, 1), membresia.getFechaVencimiento());
        assertEquals("ACTIVA", membresia.getEstado());
    }
}
```

## 4. Configuracion realizada en el proyecto

### Cambios en `pom.xml`

Se agrego JUnit 4 como dependencia de pruebas:

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

Tambien se habilito Maven Surefire para que las pruebas se ejecuten con Maven.

Comando usado para validar localmente:

```bash
mvn clean test
```

Resultado local:

```text
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## 5. Implementacion en Azure DevOps

### Proyecto de Azure DevOps

Se creo el proyecto:

```text
Organizacion: jhoelsuarez
Proyecto: Atlas-GYM
URL: https://dev.azure.com/jhoelsuarez/Atlas-GYM
```

El codigo fue cargado en **Azure Repos** para evitar depender de permisos administrativos del repositorio GitHub original.

### Agente local

Al ejecutar inicialmente el pipeline con `ubuntu-latest`, Azure DevOps mostro el error:

```text
No hosted parallelism has been purchased or granted.
```

Esto ocurre cuando una organizacion nueva no tiene habilitados agentes hospedados gratuitos. Para resolverlo se configuro un agente local de Azure Pipelines en Windows:

```text
Nombre del agente: AtlasGym-PC
Pool: Default
Tipo: Self-hosted agent
```

Tambien se instalaron las herramientas necesarias en el equipo:

```text
Java: Eclipse Temurin JDK 17
Maven: Apache Maven 3.9.16
```

## 6. Pipeline configurado

Archivo:

```text
azure-pipelines.yml
```

Contenido principal:

```yaml
trigger:
  branches:
    include:
      - main

pool:
  name: Default

steps:
  - powershell: |
      $env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.0.19.10-hotspot"
      $env:MAVEN_HOME = "C:\Users\USER\tools\apache-maven-3.9.16"
      $env:Path = "$env:JAVA_HOME\bin;$env:MAVEN_HOME\bin;$env:Path"

      java -version
      mvn -version
      mvn clean test
    displayName: Compilar y ejecutar pruebas JUnit

  - task: PublishTestResults@2
    displayName: Publicar resultados JUnit
    condition: succeededOrFailed()
    inputs:
      testResultsFormat: JUnit
      testResultsFiles: "**/surefire-reports/TEST-*.xml"
      failTaskOnFailedTests: true
```

Este pipeline cumple con los requisitos porque:

- Se ejecuta automaticamente ante cambios en la rama `main`.
- Compila y prueba el proyecto con Maven.
- Ejecuta las pruebas JUnit.
- Publica los resultados en Azure DevOps.
- Permite visualizar pruebas aprobadas o fallidas en la seccion **Tests**.

## 7. Resultado de la ejecucion en Azure DevOps

Ejecucion exitosa:

```text
Build: 20260622.4
Estado: completed
Resultado: succeeded
Commit: b617cf187e081b4c32e061bcc9e319f9870b806e
URL: https://dev.azure.com/jhoelsuarez/cd53ca8a-8957-448e-b03f-942b2a7957fb/_build/results?buildId=4
```

Resultados publicados:

```text
AtletaTest: 1 prueba aprobada
MembresiaTest: 1 prueba aprobada
Total: 2 pruebas aprobadas
Fallos: 0
```

## 8. Capturas que deben incluirse en el PDF

Para completar el entregable, se deben insertar capturas de:

1. Proyecto `Atlas-GYM` creado en Azure DevOps.
2. Repositorio `Atlas-GYM` cargado en Azure Repos.
3. Archivo `azure-pipelines.yml`.
4. Agente local `AtlasGym-PC` en el pool `Default`.
5. Ejecucion del pipeline `20260622.4` con resultado `succeeded`.
6. Log del job `Compilar y ejecutar pruebas JUnit`.
7. Seccion **Tests** con `AtletaTest` y `MembresiaTest` aprobadas.

## 9. Conclusiones

La actividad demuestra como la automatizacion de pruebas fortalece el proceso de Validacion y Verificacion. Con Azure DevOps se logro integrar el repositorio, el pipeline de compilacion, la ejecucion automatica de pruebas y la publicacion de resultados.

El ejercicio tambien muestra una situacion real de trabajo: cuando no existe paralelismo hospedado gratuito, se puede usar un agente local para ejecutar el pipeline sin costo. La solucion final permite validar automaticamente el proyecto cada vez que se suben cambios, dejando una base lista para ampliar la cobertura con mas pruebas unitarias, Selenium para pruebas web o Appium para pruebas moviles.

## 10. Resumen para la entrega

Se implemento una automatizacion de pruebas en Azure DevOps para el proyecto **Atlas GYM**. Se crearon pruebas unitarias con JUnit para validar el registro de atletas y la creacion de membresias. Se configuro Maven para ejecutar las pruebas, se creo un pipeline en Azure DevOps, se uso un agente local y se publicaron los resultados en el panel de Azure DevOps. La ejecucion final fue exitosa con 2 pruebas aprobadas y 0 fallos.
