package org.example.AtlasGym.modelo.modulo04_asistencia_evaluaciones;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.example.AtlasGym.modelo.Asistencia;
import org.example.AtlasGym.modelo.InscripcionSesion;
import org.example.AtlasGym.modelo.SesionGrupal;

public class AsistenciaTest {

    private SesionGrupal sesionHoy;
    private SesionGrupal sesionAyer;
    private InscripcionSesion inscripcionHoy;
    private InscripcionSesion inscripcionAyer;

    @Before
    public void setUp() {
        sesionHoy = new SesionGrupal();
        sesionHoy.setFecha(LocalDate.now());

        inscripcionHoy = new InscripcionSesion();
        inscripcionHoy.setSesion(sesionHoy);

        sesionAyer = new SesionGrupal();
        sesionAyer.setFecha(LocalDate.now().minusDays(1)); // Sesión del día anterior

        inscripcionAyer = new InscripcionSesion();
        inscripcionAyer.setSesion(sesionAyer);
    }

    // Caso de Prueba: AS-01 - Registrar asistencia de atletas inscritos
    @Test
    public void testAS01_RegistrarAsistencia() {
        Asistencia asistencia = new Asistencia();
        asistencia.setInscripcion(inscripcionHoy);
        asistencia.setEstado("ASISTIO");

        assertEquals("ASISTIO", asistencia.getEstado());
        assertSame(inscripcionHoy, asistencia.getInscripcion());
    }

    // Caso de Prueba: AS-03 - Modificar estado de asistencia en el mismo día
    @Test
    public void testAS03_ModificarAsistenciaMismoDia() {
        Asistencia asistencia = new Asistencia();
        asistencia.setInscripcion(inscripcionHoy);
        asistencia.setEstado("NO_ASISTIO");

        // Ejecución: El entrenador corrige el estado
        asistencia.setEstado("ASISTIO");

        // Validación de negocio (Caja Blanca): Al ser del mismo día, NO debe lanzar error
        asistencia.validarModificacionAsistencia();

        assertEquals("ASISTIO", asistencia.getEstado());
    }

    // Caso de Prueba: AS-04 - Modificar asistencia de sesión de día anterior (bloqueado)
    @Test(expected = IllegalStateException.class)
    public void testAS04_BloquearModificacionAsistenciaDiaAnterior() {
        Asistencia asistencia = new Asistencia();
        asistencia.setInscripcion(inscripcionAyer); // Ojo: Sesión de ayer
        asistencia.setEstado("NO_ASISTIO");

        // Ejecución: El entrenador intenta cambiar a ASISTIO en un día posterior
        asistencia.setEstado("ASISTIO");

        // Esto DEBE lanzar IllegalStateException
        asistencia.validarModificacionAsistencia();
    }
}