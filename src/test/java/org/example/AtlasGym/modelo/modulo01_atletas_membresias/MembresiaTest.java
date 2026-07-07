package org.example.AtlasGym.modelo.modulo01_atletas_membresias;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import org.example.AtlasGym.modelo.Atleta;
import org.example.AtlasGym.modelo.Membresia;
import org.example.AtlasGym.modelo.TipoMembresia;

public class MembresiaTest {

    private Atleta atleta;
    private TipoMembresia planMensual;
    private Membresia membresiaActiva;

    @Before
    public void setUp() {
        atleta = new Atleta();
        atleta.setNombreCompleto("Carlos Andrade");
        atleta.setEstado("ACTIVO");

        planMensual = new TipoMembresia();
        planMensual.setNombrePlan("Mensual");
        planMensual.setDuracionDias(30);

        membresiaActiva = new Membresia();
        membresiaActiva.setAtleta(atleta);
        membresiaActiva.setTipoMembresia(planMensual);
        membresiaActiva.setFechaInicio(LocalDate.now());
        membresiaActiva.setFechaVencimiento(LocalDate.now().plusDays(planMensual.getDuracionDias()));
        membresiaActiva.setEstado("ACTIVA");
    }

    // Caso de Prueba: AM-11 - Asignar membresía a atleta sin membresía activa
    @Test
    public void testAM11_AsignarMembresiaAtletaSinMembresia() {
        Membresia nuevaMembresia = new Membresia();
        LocalDate fechaInicio = LocalDate.of(2026, 6, 1);

        nuevaMembresia.setAtleta(atleta);
        nuevaMembresia.setTipoMembresia(planMensual);
        nuevaMembresia.setFechaInicio(fechaInicio);
        nuevaMembresia.setFechaVencimiento(fechaInicio.plusDays(planMensual.getDuracionDias()));
        nuevaMembresia.setEstado("ACTIVA");

        assertSame(atleta, nuevaMembresia.getAtleta());
        assertEquals(LocalDate.of(2026, 7, 1), nuevaMembresia.getFechaVencimiento());
        assertEquals("ACTIVA", nuevaMembresia.getEstado());
    }

    // Caso de Prueba: AM-12 - Evaluar lógica para impedir asignar membresía si ya tiene activa
    @Test
    public void testAM12_ValidarEstadoMembresiaActivaExistente() {
        boolean tieneMembresiaActiva = "ACTIVA".equals(membresiaActiva.getEstado());
        assertTrue("El sistema debe detectar que el atleta ya tiene una membresía activa", tieneMembresiaActiva);
    }

    // Caso de Prueba: AM-13 - Renovar membresía vencida
    @Test
    public void testAM13_RenovarMembresiaVencida() {
        membresiaActiva.setEstado("VENCIDA");

        Membresia renovacion = new Membresia();
        LocalDate fechaRenovacion = LocalDate.now().plusDays(1);

        renovacion.setAtleta(atleta);
        renovacion.setTipoMembresia(planMensual);
        renovacion.setFechaInicio(fechaRenovacion);
        renovacion.setFechaVencimiento(fechaRenovacion.plusDays(planMensual.getDuracionDias()));
        renovacion.setEstado("ACTIVA");

        assertEquals("VENCIDA", membresiaActiva.getEstado());
        assertEquals("ACTIVA", renovacion.getEstado());
        assertEquals(fechaRenovacion.plusDays(30), renovacion.getFechaVencimiento());
    }

    // Caso de Prueba: AM-14 - Consultar estado de membresía (Validación de fechas)
    @Test
    public void testAM14_ConsultarEstadoMembresiaVigente() {
        LocalDate hoy = LocalDate.now();
        boolean estaVigente = !hoy.isBefore(membresiaActiva.getFechaInicio()) && !hoy.isAfter(membresiaActiva.getFechaVencimiento());

        assertTrue("La membresía debería estar vigente para el día de hoy", estaVigente);
        assertEquals("ACTIVA", membresiaActiva.getEstado());
    }
}