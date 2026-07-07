$Host.UI.RawUI.WindowTitle = "EVIDENCIA 3.10 - Equivalencia y valores limite"

Set-Location "C:\Users\USER\Desktop\vali\Atlas-GYM"

$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.0.19.10-hotspot"
$env:MAVEN_HOME = "C:\Users\USER\tools\apache-maven-3.9.16"
$env:Path = "$env:JAVA_HOME\bin;$env:MAVEN_HOME\bin;$env:Path"

Write-Host "============================================================" -ForegroundColor Cyan
Write-Host "3.10 TECNICA 3: CLASES DE EQUIVALENCIA Y VALORES LIMITE" -ForegroundColor Cyan
Write-Host "============================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Tecnicas seleccionadas:"
Write-Host "1. Clases de equivalencia"
Write-Host "2. Valores limite"
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
    "org.example.AtlasGym.modelo.modulo03_sesiones_grupales.InscripcionSesionTest",
    "org.example.AtlasGym.modelo.modulo02_rutinas.AsignacionRutinaTest",
    "org.example.AtlasGym.modelo.modulo05_equipamiento.EquipamientoTest",
    "org.example.AtlasGym.modelo.modulo05_equipamiento.MantenimientoTest",
    "org.example.AtlasGym.modelo.modulo04_asistencia_evaluaciones.EvaluacionFisicaTest"
)

Write-Host ""
Write-Host "2) Protocolo de clases de equivalencia:" -ForegroundColor Yellow
Write-Host "- Membresia activa: valido=true / invalido=false"
Write-Host "- Estado atleta: valido=ACTIVO / invalido=INACTIVO"
Write-Host "- Estado equipamiento: valido=OPERATIVO / invalido=DADO_DE_BAJA"
Write-Host "- Motivo de baja: valido=texto / invalido=vacio"
Write-Host ""
Write-Host "3) Protocolo de valores limite:" -ForegroundColor Yellow
Write-Host "- Cupo disponible: 3 de 5"
Write-Host "- Cupo lleno: 5 de 5"
Write-Host "- Estatura cero para IMC"
Write-Host "- Rangos de IMC: bajo peso, normal, sobrepeso y obesidad"
Write-Host ""
Write-Host "4) Ejecutando pruebas seleccionadas..." -ForegroundColor Yellow
Write-Host ""

& "$env:JAVA_HOME\bin\java.exe" -cp $cp org.junit.runner.JUnitCore $classes

Write-Host ""
Write-Host "============================================================" -ForegroundColor Green
Write-Host "RESULTADO ESPERADO PARA LA FOTO: OK (18 tests)" -ForegroundColor Green
Write-Host "============================================================" -ForegroundColor Green
Write-Host ""
Write-Host "TOMA FOTO DE ESTA VENTANA COMPLETA PARA EL APARTADO 3.10." -ForegroundColor Yellow
Write-Host ""
Read-Host "Presiona Enter cuando ya hayas tomado la foto"
