package org.example.AtlasGym.modelo.modulo02_rutinas;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.example.AtlasGym.modelo.AsignacionRutina;
import org.example.AtlasGym.modelo.Atleta;
import org.example.AtlasGym.modelo.Rutina;

public class AsignacionRutinaTest {

    private Atleta atletaActivo;
    private Atleta atletaInactivo;
    private Rutina rutinaFuerza;

    @Before
    public void setUp() {
        atletaActivo = new Atleta();
        atletaActivo.setNombreCompleto("David Rueda");
        atletaActivo.setEstado("ACTIVO");

        atletaInactivo = new Atleta();
        atletaInactivo.setNombreCompleto("Jhoel Suarez");
        atletaInactivo.setEstado("INACTIVO");

        rutinaFuerza = new Rutina();
        rutinaFuerza.setNombre("Fuerza 5x5");
        rutinaFuerza.setObjetivoPrincipal("FUERZA");
    }

    // Caso de Prueba: RT-10 y RT-13 - Asignar rutina a atleta activo
    @Test
    public void testRT10_AsignarRutinaAtletaActivo() {
        AsignacionRutina asignacion = new AsignacionRutina();
        asignacion.setAtleta(atletaActivo);
        asignacion.setRutina(rutinaFuerza);
        asignacion.setFechaAsignacion(LocalDate.now());
        asignacion.setActiva(true);

        // Validamos la lógica de persistencia que no debería lanzar error
        asignacion.validarAtletaActivo();

        assertSame(atletaActivo, asignacion.getAtleta());
        assertTrue("La asignación debe estar activa", asignacion.isActiva());
        assertEquals(LocalDate.now(), asignacion.getFechaAsignacion());
    }

    // Caso de Prueba: RT-11 - Asignar rutina a atleta inactivo (bloqueado)
    @Test(expected = IllegalStateException.class)
    public void testRT11_BloquearAsignacionAtletaInactivo() {
        AsignacionRutina asignacion = new AsignacionRutina();
        asignacion.setAtleta(atletaInactivo);
        asignacion.setRutina(rutinaFuerza);
        asignacion.setActiva(true);

        asignacion.validarAtletaActivo();
    }

    // Caso de Prueba: RT-12 - Desasignar rutina de un atleta
    @Test
    public void testRT12_DesasignarRutina() {
        AsignacionRutina asignacion = new AsignacionRutina();
        asignacion.setAtleta(atletaActivo);
        asignacion.setActiva(true);

        asignacion.setActiva(false);

        assertFalse("La asignación debe cambiar a activa = false", asignacion.isActiva());
        assertSame("El atleta se conserva en el registro histórico", atletaActivo, asignacion.getAtleta());
    }
}