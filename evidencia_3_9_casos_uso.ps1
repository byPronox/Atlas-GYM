$Host.UI.RawUI.WindowTitle = "EVIDENCIA 3.9 - Caja negra por casos de uso"

Set-Location "C:\Users\USER\Desktop\vali\Atlas-GYM"

$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.0.19.10-hotspot"
$env:MAVEN_HOME = "C:\Users\USER\tools\apache-maven-3.9.16"
$env:Path = "$env:JAVA_HOME\bin;$env:MAVEN_HOME\bin;$env:Path"

Write-Host "============================================================" -ForegroundColor Cyan
Write-Host "3.9 TECNICA 2: CAJA NEGRA POR CASOS DE USO" -ForegroundColor Cyan
Write-Host "============================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Objetivo: presentar protocolo de pruebas derivado de casos de uso y su ejecucion."
Write-Host "Casos de uso cubiertos: CU-P1, CU-P2, CU-P3, CU-P4 y CU-P5."
Write-Host ""
Write-Host "1) Compilando clases y pruebas sin ejecutar mvn test..." -ForegroundColor Yellow
& "$env:MAVEN_HOME\bin\mvn.cmd" -q -DskipTests test-compile dependency:build-classpath "-Dmdep.outputFile=target/test-cp.txt"

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "ERROR: fallo la compilacion. Toma captura de este error." -ForegroundColor Red
    Read-Host "Presiona Enter para cerrar"
    exit $LASTEXITCODE
}

$dep = Get-Content "target\test-cp.txt" -Raw
$cp = "target\test-classes;target\classes;$dep"

$classes = @(
    "org.example.AtlasGym.modelo.modulo01_atletas_membresias.AtletaTest",
    "org.example.AtlasGym.modelo.modulo01_atletas_membresias.TipoMembresiaTest",
    "org.example.AtlasGym.modelo.modulo01_atletas_membresias.MembresiaTest",
    "org.example.AtlasGym.modelo.modulo02_rutinas.RutinaTest",
    "org.example.AtlasGym.modelo.modulo02_rutinas.RutinaEjercicioTest",
    "org.example.AtlasGym.modelo.modulo02_rutinas.AsignacionRutinaTest",
    "org.example.AtlasGym.modelo.modulo03_sesiones_grupales.SesionGrupalTest",
    "org.example.AtlasGym.modelo.modulo03_sesiones_grupales.InscripcionSesionTest",
    "org.example.AtlasGym.modelo.modulo04_asistencia_evaluaciones.AsistenciaTest",
    "org.example.AtlasGym.modelo.modulo04_asistencia_evaluaciones.EvaluacionFisicaTest",
    "org.example.AtlasGym.modelo.modulo05_equipamiento.EquipamientoTest",
    "org.example.AtlasGym.modelo.modulo05_equipamiento.MantenimientoTest"
)

Write-Host ""
Write-Host "2) Ejecutando protocolo por casos de uso..." -ForegroundColor Yellow
Write-Host ""
Write-Host "CU-P1 Gestionar Atletas y Membresias: AtletaTest, TipoMembresiaTest, MembresiaTest"
Write-Host "CU-P2 Gestionar Rutinas: RutinaTest, RutinaEjercicioTest, AsignacionRutinaTest"
Write-Host "CU-P3 Gestionar Sesiones Grupales: SesionGrupalTest, InscripcionSesionTest"
Write-Host "CU-P4 Gestionar Asistencia y Evaluaciones: AsistenciaTest, EvaluacionFisicaTest"
Write-Host "CU-P5 Gestionar Equipamiento: EquipamientoTest, MantenimientoTest"
Write-Host ""

& "$env:JAVA_HOME\bin\java.exe" -cp $cp org.junit.runner.JUnitCore $classes

Write-Host ""
Write-Host "============================================================" -ForegroundColor Green
Write-Host "RESULTADO ESPERADO PARA LA FOTO: OK (40 tests)" -ForegroundColor Green
Write-Host "============================================================" -ForegroundColor Green
Write-Host ""
Write-Host "TOMA FOTO DE ESTA VENTANA COMPLETA PARA EL APARTADO 3.9." -ForegroundColor Yellow
Write-Host ""
Read-Host "Presiona Enter cuando ya hayas tomado la foto"
