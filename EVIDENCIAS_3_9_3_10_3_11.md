# Evidencias para informe - secciones 3.9, 3.10 y 3.11

Este documento indica que ejecutar y que capturas tomar para completar las
secciones 3.9, 3.10 y 3.11 del informe de Atlas GYM.

## Preparacion general

Ejecutar en PowerShell desde la raiz del proyecto:

```powershell
cd C:\Users\USER\Desktop\vali\Atlas-GYM

$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.19.10-hotspot"
$env:MAVEN_HOME="C:\Users\USER\tools\apache-maven-3.9.16"
$env:Path="$env:JAVA_HOME\bin;$env:MAVEN_HOME\bin;$env:Path"
```

Compilar sin ejecutar tests desde Maven:

```powershell
& "$env:MAVEN_HOME\bin\mvn.cmd" -q -DskipTests test-compile dependency:build-classpath "-Dmdep.outputFile=target/test-cp.txt"
```

Ejecutar todas las pruebas con JUnit 4 directamente:

```powershell
$dep = Get-Content "target\test-cp.txt" -Raw
$cp = "target\test-classes;target\classes;$dep"
$classes = Get-ChildItem "target\test-classes" -Recurse -Filter "*Test.class" |
  ForEach-Object {
    $_.FullName.Substring((Resolve-Path "target\test-classes").Path.Length + 1).
      Replace("\","/").Replace(".class","").Replace("/",".")
  }

& "$env:JAVA_HOME\bin\java.exe" -cp $cp org.junit.runner.JUnitCore $classes
```

Resultado esperado para la captura:

```text
JUnit version 4.13.2
........................................
Time: 0,025

OK (40 tests)
```

Foto obligatoria sugerida: terminal completa mostrando `OK (40 tests)`.

## 3.9 Tecnica 2 - Caja negra por casos de uso

Objetivo: presentar protocolo de pruebas derivado de casos de uso y su
ejecucion.

### Protocolo ejecutado

| Caso de uso | Caso de prueba | Clase / metodo JUnit | Resultado |
|---|---|---|---|
| CU-P1 Gestionar Atletas y Membresias | Registrar atleta con datos completos | `AtletaTest.testAM01_RegistrarAtletaDatosCompletos` | APROBADO |
| CU-P1 Gestionar Atletas y Membresias | Actualizar datos de atleta | `AtletaTest.testAM04_ActualizarDatosAtleta` | APROBADO |
| CU-P1 Gestionar Atletas y Membresias | Inactivar atleta activo | `AtletaTest.testAM05_InactivarAtletaActivo` | APROBADO |
| CU-P1 Gestionar Atletas y Membresias | Reactivar atleta inactivo | `AtletaTest.testAM06_ReactivarAtletaInactivo` | APROBADO |
| CU-P1 Gestionar Atletas y Membresias | Crear tipo de membresia | `TipoMembresiaTest.testAM10_CrearTipoDeMembresia` | APROBADO |
| CU-P1 Gestionar Atletas y Membresias | Asignar membresia a atleta | `MembresiaTest.testAM11_AsignarMembresiaAtletaSinMembresia` | APROBADO |
| CU-P2 Gestionar Rutinas | Crear rutina con datos completos | `RutinaTest.testRT01_CrearRutinaDatosCompletos` | APROBADO |
| CU-P2 Gestionar Rutinas | Agregar ejercicio a rutina | `RutinaEjercicioTest.testRT03_AgregarEjercicioARutina` | APROBADO |
| CU-P2 Gestionar Rutinas | Asignar rutina a atleta activo | `AsignacionRutinaTest.testRT10_AsignarRutinaAtletaActivo` | APROBADO |
| CU-P3 Gestionar Sesiones Grupales | Programar sesion grupal | `SesionGrupalTest.testSG01_ProgramarSesionDatosCompletos` | APROBADO |
| CU-P3 Gestionar Sesiones Grupales | Inscribir atleta con cupo disponible | `InscripcionSesionTest.testSG08_InscripcionExitosa` | APROBADO |
| CU-P4 Gestionar Asistencia y Evaluaciones | Registrar asistencia | `AsistenciaTest.testAS01_RegistrarAsistencia` | APROBADO |
| CU-P4 Gestionar Asistencia y Evaluaciones | Calcular IMC peso normal | `EvaluacionFisicaTest.testAS08_CalcularIMCPesoNormal` | APROBADO |
| CU-P5 Gestionar Equipamiento | Registrar equipamiento | `EquipamientoTest.testEQ01_RegistrarEquipamiento` | APROBADO |
| CU-P5 Gestionar Equipamiento | Dar de baja equipamiento con motivo | `EquipamientoTest.testEQ04_DarDeBajaConMotivo` | APROBADO BACKEND |
| CU-P5 Gestionar Equipamiento | Programar mantenimiento operativo | `MantenimientoTest.testEQ06_ProgramarMantenimiento` | APROBADO BACKEND |

### Ejecucion para fotos

1. Abrir las clases de prueba en el IDE o explorador:
   - `src/test/java/org/example/AtlasGym/modelo/modulo01_atletas_membresias`
   - `src/test/java/org/example/AtlasGym/modelo/modulo02_rutinas`
   - `src/test/java/org/example/AtlasGym/modelo/modulo03_sesiones_grupales`
   - `src/test/java/org/example/AtlasGym/modelo/modulo04_asistencia_evaluaciones`
   - `src/test/java/org/example/AtlasGym/modelo/modulo05_equipamiento`
2. Tomar foto de una clase por modulo mostrando metodos `@Test`.
3. Tomar foto del terminal con `OK (40 tests)`.

Texto para el informe:

```text
La tecnica de caja negra por casos de uso se ejecuto tomando los cinco casos
principales del sistema Atlas GYM. Cada caso de uso fue trazado a pruebas JUnit
de dominio. La ejecucion final obtuvo 40 pruebas aprobadas, 0 fallos y 0 errores.
```

## 3.10 Tecnica 3 - Caja negra: clases de equivalencia y valores limite

Se seleccionaron dos tecnicas:

1. Clases de equivalencia.
2. Valores limite.

### A. Clases de equivalencia

| Modulo | Entrada / condicion | Clase valida | Clase invalida | Metodo ejecutado | Resultado |
|---|---|---|---|---|---|
| Sesiones grupales | Membresia activa | `true` | `false` | `InscripcionSesionTest.testSG08_InscripcionExitosa` y `testSG09_BloqueoInscripcionSinMembresia` | APROBADO |
| Rutinas | Estado del atleta | `ACTIVO` | `INACTIVO` | `AsignacionRutinaTest.testRT10_AsignarRutinaAtletaActivo` y `testRT11_BloquearAsignacionAtletaInactivo` | APROBADO |
| Equipamiento | Estado del item | `OPERATIVO` | `DADO_DE_BAJA` | `MantenimientoTest.testEQ06_ProgramarMantenimiento` y `testEQ07_ProgramarMantenimientoDadoDeBajaBloqueado` | APROBADO |
| Equipamiento | Motivo de baja | Texto no vacio | Blanco/vacio | `EquipamientoTest.testEQ04_DarDeBajaConMotivo` y `testEQ05_DarDeBajaSinMotivoBloqueado` | APROBADO |

Fotos sugeridas:

- Foto del codigo de `InscripcionSesionTest` mostrando `testSG08` y `testSG09`.
- Foto del codigo de `AsignacionRutinaTest` mostrando `testRT10` y `testRT11`.
- Foto del codigo de `MantenimientoTest` mostrando `testEQ06` y `testEQ07`.
- Foto del terminal con `OK (40 tests)`.

Texto para el informe:

```text
En clases de equivalencia se separaron entradas validas e invalidas para
verificar que el sistema acepte los datos permitidos y bloquee los no permitidos.
Los casos ejecutados confirmaron el bloqueo de atletas inactivos, inscripciones
sin membresia, mantenimientos sobre equipos dados de baja y bajas sin motivo.
```

### B. Valores limite

| Modulo | Variable | Valor probado | Metodo ejecutado | Resultado |
|---|---|---:|---|---|
| Sesiones grupales | Cupo maximo | 3 inscritos de 5 | `InscripcionSesionTest.testSG08_InscripcionExitosa` | APROBADO |
| Sesiones grupales | Cupo maximo | 5 inscritos de 5 | `InscripcionSesionTest.testSG10_BloqueoInscripcionCupoMaximo` | APROBADO |
| Evaluacion fisica | Estatura | `0` | `EvaluacionFisicaTest.testAS09_DivisionPorCeroODatosInvalidos` | APROBADO |
| Evaluacion fisica | IMC | Bajo peso | `EvaluacionFisicaTest.testExtra_BajoPeso` | APROBADO |
| Evaluacion fisica | IMC | Peso normal | `EvaluacionFisicaTest.testAS08_CalcularIMCPesoNormal` | APROBADO |
| Evaluacion fisica | IMC | Sobrepeso | `EvaluacionFisicaTest.testExtra_Sobrepeso` | APROBADO |
| Evaluacion fisica | IMC | Obesidad | `EvaluacionFisicaTest.testAS10_ActualizarEvaluacionObesidad` | APROBADO |

Fotos sugeridas:

- Foto de `InscripcionSesionTest.testSG10_BloqueoInscripcionCupoMaximo`.
- Foto de `EvaluacionFisicaTest` mostrando division por cero e IMC.
- Foto del terminal con `OK (40 tests)`.

Texto para el informe:

```text
En valores limite se probaron los bordes operativos mas sensibles: cupo maximo
de sesiones, division por cero en estatura y categorias de IMC. La ejecucion
confirma que el sistema bloquea el cupo lleno y maneja datos invalidos sin
interrumpir la aplicacion.
```

## 3.11 Pruebas funcionales QA y resultados

Objetivo: presentar formato de protocolo funcional QA y su ejecucion.

### Protocolo funcional del modulo Equipamiento

Fuente: `CasosPrueba_AtlasGym_v2(05_Equipamiento).csv`.

| ID | Caso funcional QA | Resultado de ejecucion | Evidencia |
|---|---|---|---|
| EQ-01 | Registrar nuevo item de equipamiento | APROBADO | `EquipamientoTest.testEQ01_RegistrarEquipamiento` |
| EQ-02 | Registrar equipamiento con obligatorios vacios | PARCIAL | Anotaciones `@Required`; falta prueba UI automatizada |
| EQ-03 | Actualizar datos de equipamiento | APROBADO | `EquipamientoTest.testEQ03_ActualizarEquipamiento` |
| EQ-04 | Dar de baja con fecha y motivo | PARCIAL | Logica aprobada; falta accion UI |
| EQ-05 | Dar de baja sin motivo | PARCIAL | Logica aprobada; falta accion UI |
| EQ-06 | Programar mantenimiento en item operativo | PARCIAL | Logica aprobada; falta accion UI |
| EQ-07 | Bloquear mantenimiento en item dado de baja | APROBADO | `MantenimientoTest.testEQ07_ProgramarMantenimientoDadoDeBajaBloqueado` |
| EQ-08 | Registrar ejecucion de mantenimiento | PARCIAL | Logica aprobada; falta accion UI |
| EQ-09 | Alerta visual de mantenimiento vencido | PARCIAL | Booleano `estaVencido()` aprobado; falta estilo visual |
| EQ-10 | Filtrar inventario por categoria y estado | PENDIENTE UI | Requiere captura en navegador |
| EQ-11 | Historial de mantenimientos por item | FALLIDO FUNCIONAL | No hay coleccion/historial expuesto desde `Equipamiento` |
| EQ-12 | Entrenador bloqueado en modulo Equipamiento | FALLIDO FUNCIONAL | No hay usuario Entrenador ni permisos por rol configurados |

### Porcentajes para el informe

| Estado | Casos | Porcentaje |
|---|---:|---:|
| Aprobados completos | 3/12 | 25.00% |
| Parciales | 6/12 | 50.00% |
| Pendiente UI | 1/12 | 8.33% |
| Fallidos funcionales | 2/12 | 16.67% |

### Ejecucion para fotos de QA

1. Foto del CSV abierto mostrando los casos EQ-01 a EQ-12.
2. Foto del terminal con `OK (40 tests)`.
3. Foto de `EquipamientoTest` mostrando EQ-01, EQ-03, EQ-04 y EQ-05.
4. Foto de `MantenimientoTest` mostrando EQ-06, EQ-07, EQ-08 y EQ-09.
5. Foto de `src/main/resources/xava/controladores.xml` mostrando que no hay
   acciones especificas de Equipamiento.
6. Foto de `src/main/webapp/xava/style/custom.css` mostrando que no hay estilo
   para alerta visual.
7. Foto de `src/main/resources/naviox-users.properties` mostrando que solo
   existe `admin=admin`.

Texto para el informe:

```text
Las pruebas funcionales QA del modulo Equipamiento se ejecutaron contra los 12
casos del protocolo. Tres casos quedaron aprobados completamente, seis quedaron
parciales porque la logica de backend fue validada pero falta accion visual en
OpenXava, un caso queda pendiente de prueba UI y dos casos quedan fallidos por
funcionalidad no implementada: historial desde Equipamiento y bloqueo por rol
Entrenador.
```

## Capturas finales recomendadas

Para que la evidencia quede completa, usar estas capturas:

1. Terminal con `OK (40 tests)`.
2. Carpeta `src/test/java/...` mostrando las pruebas por modulo.
3. `EquipamientoTest.java`.
4. `MantenimientoTest.java`.
5. `InscripcionSesionTest.java`.
6. `EvaluacionFisicaTest.java`.
7. CSV `CasosPrueba_AtlasGym_v2(05_Equipamiento).csv`.
8. README con tabla de resultados.
9. `controladores.xml` sin acciones especificas.
10. `naviox-users.properties` con solo `admin=admin`.
