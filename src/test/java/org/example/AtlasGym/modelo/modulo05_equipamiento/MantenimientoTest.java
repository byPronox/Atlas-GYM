package org.example.AtlasGym.modelo.modulo05_equipamiento;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.example.AtlasGym.modelo.Equipamiento;
import org.example.AtlasGym.modelo.Mantenimiento;

public class MantenimientoTest {

    private Equipamiento maquinaOperativa;
    private Equipamiento maquinaDeBaja;
    private Mantenimiento mantenimientoPendiente;

    @Before
    public void setUp() {
        maquinaOperativa = new Equipamiento();
        maquinaOperativa.setNombre("Prensa de Piernas");
        maquinaOperativa.setEstado("OPERATIVO");

        maquinaDeBaja = new Equipamiento();
        maquinaDeBaja.setNombre("Bicicleta Estática Antigua");
        maquinaDeBaja.setEstado("DADO_DE_BAJA");

        mantenimientoPendiente = new Mantenimiento();
        mantenimientoPendiente.setEquipamiento(maquinaOperativa);
        mantenimientoPendiente.setEstado("PENDIENTE");
        mantenimientoPendiente.setFechaProgramada(LocalDate.now().plusDays(5)); // Vence en 5 días
    }

    // Caso de Prueba: EQ-06 - Programar mantenimiento en ítem operativo
    @Test
    public void testEQ06_ProgramarMantenimiento() {
        // Validación (Caja Blanca) no debe fallar
        mantenimientoPendiente.validarProgramacion();

        assertEquals("PENDIENTE", mantenimientoPendiente.getEstado());
        assertSame(maquinaOperativa, mantenimientoPendiente.getEquipamiento());
    }

    // Caso de Prueba: EQ-07 - Programar mantenimiento en ítem dado de baja (bloqueado)
    @Test(expected = IllegalStateException.class)
    public void testEQ07_ProgramarMantenimientoDadoDeBajaBloqueado() {
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setEquipamiento(maquinaDeBaja);
        mantenimiento.setEstado("PENDIENTE");

        // Ejecución: DEBE lanzar IllegalStateException
        mantenimiento.validarProgramacion();
    }

    // Caso de Prueba: EQ-08 - Registrar ejecución de mantenimiento
    @Test
    public void testEQ08_RegistrarEjecucionMantenimiento() {
        // Pre-condición: Máquina estaba en mantenimiento (no operativa temporalmente)
        maquinaOperativa.setEstado("EN_MANTENIMIENTO");
        LocalDate fechaEjecucion = LocalDate.now();
        String observaciones = "Cambio de poleas completado";

        // Ejecución
        mantenimientoPendiente.registrarEjecucion(fechaEjecucion, observaciones);

        // Resultados Esperados
        assertEquals("EJECUTADO", mantenimientoPendiente.getEstado());
        assertEquals(fechaEjecucion, mantenimientoPendiente.getFechaRealizada());
        assertEquals(observaciones, mantenimientoPendiente.getObservacionesResultado());
        // La máquina debe volver a estar operativa automáticamente
        assertEquals("OPERATIVO", maquinaOperativa.getEstado());
    }

    // Caso de Prueba: EQ-09 - Alerta visual de mantenimiento vencido
    @Test
    public void testEQ09_MantenimientoVencido() {
        // 1. Mantenimiento para dentro de 5 días (NO vencido)
        assertFalse("El mantenimiento futuro no debe estar vencido", mantenimientoPendiente.estaVencido());

        // 2. Simulamos que la fecha programada fue hace 2 días
        mantenimientoPendiente.setFechaProgramada(LocalDate.now().minusDays(2));
        assertTrue("El mantenimiento pasado debe marcarse como vencido", mantenimientoPendiente.estaVencido());

        // 3. Si ya se ejecutó, no debe salir como vencido
        mantenimientoPendiente.setEstado("EJECUTADO");
        assertFalse("Un mantenimiento ejecutado no debe marcarse como vencido", mantenimientoPendiente.estaVencido());
    }
}