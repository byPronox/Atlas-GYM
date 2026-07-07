package org.example.AtlasGym.modelo.modulo04_asistencia_evaluaciones;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.example.AtlasGym.modelo.Atleta;
import org.example.AtlasGym.modelo.EvaluacionFisica;

public class EvaluacionFisicaTest {

    private EvaluacionFisica evaluacion;
    private Atleta atleta;

    @Before
    public void setUp() {
        atleta = new Atleta();
        atleta.setNombreCompleto("Stefan Jativa");

        evaluacion = new EvaluacionFisica();
        evaluacion.setAtleta(atleta);
        evaluacion.setFecha(LocalDate.now());
    }

    // Caso de Prueba: AS-08 - IMC Peso Normal (Cobertura de Decisión: Rama 2)
    @Test
    public void testAS08_CalcularIMCPesoNormal() {
        evaluacion.setPeso(new BigDecimal("75.00"));
        evaluacion.setEstaturaMetros(new BigDecimal("1.75"));

        evaluacion.calcularIMCYRiesgo();

        assertEquals(new BigDecimal("24.49"), evaluacion.getImc());
        assertEquals("PESO NORMAL - Mantener estado", evaluacion.getNivelRiesgo());
    }

    // Caso de Prueba: AS-09 - División por cero / Datos inválidos
    @Test
    public void testAS09_DivisionPorCeroODatosInvalidos() {
        evaluacion.setPeso(new BigDecimal("70.00"));
        evaluacion.setEstaturaMetros(BigDecimal.ZERO);

        evaluacion.calcularIMCYRiesgo();

        assertEquals(BigDecimal.ZERO, evaluacion.getImc());
        assertEquals("DATOS INVALIDOS (Revise estatura/peso)", evaluacion.getNivelRiesgo());
    }

    // Caso de Prueba: AS-10 - Obesidad (Cobertura de Decisión: Rama 4)
    @Test
    public void testAS10_ActualizarEvaluacionObesidad() {
        evaluacion.setPeso(new BigDecimal("100.00"));
        evaluacion.setEstaturaMetros(new BigDecimal("1.65"));

        evaluacion.calcularIMCYRiesgo();

        assertEquals(new BigDecimal("36.73"), evaluacion.getImc());
        assertEquals("OBESIDAD - Derivar a deportologo", evaluacion.getNivelRiesgo());
    }

    // Test Extra: Cobertura de Decisión - Bajo Peso (Rama 1)
    @Test
    public void testExtra_BajoPeso() {
        evaluacion.setPeso(new BigDecimal("50.00"));
        evaluacion.setEstaturaMetros(new BigDecimal("1.80"));

        evaluacion.calcularIMCYRiesgo();

        assertEquals(new BigDecimal("15.43"), evaluacion.getImc());
        assertEquals("BAJO PESO - Requiere plan de hipertrofia", evaluacion.getNivelRiesgo());
    }

    // Test Extra: Cobertura de Decisión - Sobrepeso (Rama 3)
    @Test
    public void testExtra_Sobrepeso() {
        evaluacion.setPeso(new BigDecimal("85.00"));
        evaluacion.setEstaturaMetros(new BigDecimal("1.75"));

        evaluacion.calcularIMCYRiesgo();

        assertEquals(new BigDecimal("27.76"), evaluacion.getImc());
        assertEquals("SOBREPESO - Requiere deficit calorico", evaluacion.getNivelRiesgo());
    }
}