# Automatizacion de pruebas en Azure DevOps - Atlas GYM

Este repositorio contiene una demostracion gratuita de automatizacion de pruebas para el proyecto Java/Maven **Atlas GYM**. La propuesta usa Azure Repos o GitHub, Azure Pipelines y resultados JUnit publicados en el panel de la ejecucion.

## Investigacion breve

Azure DevOps permite automatizar pruebas dentro del ciclo de V&V mediante estos servicios:

- **Azure Repos**: almacena el codigo fuente y dispara el pipeline cuando se suben cambios.
- **Azure Pipelines**: compila el proyecto, ejecuta pruebas automatizadas y publica resultados en cada cambio.
- **Azure Test Plans**: organiza planes, suites y casos de prueba. Es util para trazabilidad y pruebas manuales/automatizadas, aunque puede requerir licencia adicional. Para mantener esta practica gratuita, se publican resultados JUnit desde Azure Pipelines.
- **Artifacts y tab de Tests**: permiten conservar evidencias como reportes XML, resultados por prueba y fallos.

La integracion con frameworks como **JUnit**, **Selenium** o **Appium** se realiza desde el pipeline ejecutando el comando del framework. En Java, Maven genera reportes JUnit XML en `target/surefire-reports`, que Azure Pipelines lee para mostrar la pestana **Tests**. Para Selenium o Appium se agregarian dependencias y se ejecutarian pruebas funcionales contra un navegador o dispositivo/emulador disponible en el agente.

Flujo tipico de CI/CD:

1. El desarrollador sube cambios al repositorio.
2. Azure Pipelines detecta el cambio por el `trigger`.
3. El agente instala/selecciona Java 17.
4. Maven ejecuta `clean test`.
5. Surefire genera resultados JUnit XML.
6. Azure publica los resultados y marca el pipeline como correcto o fallido.

Fuentes oficiales consultadas:

- https://learn.microsoft.com/es-es/azure/devops/pipelines/ecosystems/java
- https://learn.microsoft.com/es-es/azure/devops/test/associate-automated-test-with-test-case
- https://learn.microsoft.com/es-es/azure/devops/test/run-automated-tests-from-test-hub

## Escenario de prueba elegido

El escenario automatizado valida reglas basicas del modulo de gimnasio:

- Registro de datos principales de un atleta.
- Creacion de una membresia mensual y calculo de su fecha de vencimiento.

Pruebas creadas:

- `src/test/java/org/example/AtlasGym/modelo/AtletaTest.java`
- `src/test/java/org/example/AtlasGym/modelo/MembresiaTest.java`

Comando local:

```bash
mvn clean test
```

## Configuracion realizada

Se actualizo `pom.xml` para:

- Agregar JUnit 4.13.2 como dependencia de pruebas.
- Permitir que Maven Surefire ejecute los tests.

Se agrego `azure-pipelines.yml` con:

- Agente gratuito `ubuntu-latest`.
- Java 17 preinstalado.
- Ejecucion `mvn clean test`.
- Publicacion de resultados JUnit desde `target/surefire-reports`.

## Como subirlo gratis a Azure DevOps

1. Entra a https://dev.azure.com/ y crea una organizacion si no tienes una.
2. Crea un proyecto privado o publico.
3. Sube este repositorio a **Azure Repos** o conectalo desde GitHub.
4. Ve a **Pipelines > New pipeline**.
5. Selecciona el repositorio.
6. Elige **Existing Azure Pipelines YAML file**.
7. Selecciona `/azure-pipelines.yml`.
8. Ejecuta **Run**.
9. Al finalizar, abre la ejecucion y revisa la pestana **Tests** para ver las pruebas aprobadas.

## Capturas sugeridas para el informe PDF

Incluye estas evidencias en tu documento final:

- Configuracion del archivo `azure-pipelines.yml`.
- Primera ejecucion del pipeline en Azure DevOps.
- Resultado de la tarea Maven.
- Pestana **Tests** con `AtletaTest` y `MembresiaTest` aprobadas.

## Resumen para entrega

La automatizacion implementada demuestra un flujo profesional de V&V: el codigo se almacena en un repositorio, cada cambio activa una canalizacion CI, Maven ejecuta pruebas JUnit y Azure DevOps publica los resultados. El ejercicio usa herramientas gratuitas y deja preparado el proyecto para ampliar la cobertura con pruebas funcionales Selenium/Appium o trazabilidad con Azure Test Plans.
