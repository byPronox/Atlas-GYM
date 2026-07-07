package org.example.AtlasGym.modelo.modulo03_sesiones_grupales;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.example.AtlasGym.modelo.Rutina;
import org.example.AtlasGym.modelo.SesionGrupal;
import org.example.AtlasGym.modelo.Usuario;

public class SesionGrupalTest {

    private SesionGrupal sesionOriginal;
    private Usuario entrenador;
    private Rutina rutina;

    @Before
    public void setUp() {
        entrenador = new Usuario();
        entrenador.setUsername("entrenador_luis");

        rutina = new Rutina();
        rutina.setNombre("Crossfit Basico");

        sesionOriginal = new SesionGrupal();
        sesionOriginal.setNombre("Clase Matutina");
        sesionOriginal.setFecha(LocalDate.of(2026, 7, 1));
        sesionOriginal.setHoraInicio(LocalTime.of(8, 0));
        sesionOriginal.setHoraFin(LocalTime.of(9, 0));
        sesionOriginal.setCupoMaximo(15);
        sesionOriginal.setEstado("PROGRAMADA");
        sesionOriginal.setEntrenador(entrenador);
        sesionOriginal.setRutina(rutina);
    }

    // Caso de Prueba: SG-01 - Programar sesión grupal con datos completos
    @Test
    public void testSG01_ProgramarSesionDatosCompletos() {
        assertEquals("Clase Matutina", sesionOriginal.getNombre());
        assertEquals(LocalTime.of(8, 0), sesionOriginal.getHoraInicio());
        assertEquals("PROGRAMADA", sesionOriginal.getEstado());
        assertEquals(Integer.valueOf(15), sesionOriginal.getCupoMaximo());
    }

    // Caso de Prueba: SG-02 y SG-05 - Conflicto de horario del entrenador
    @Test
    public void testSG02_ConflictoHorarioEntrenador() {
        SesionGrupal nuevaSesion = new SesionGrupal();
        nuevaSesion.setFecha(LocalDate.of(2026, 7, 1));
        nuevaSesion.setEntrenador(entrenador);

        // Ejecución: Solapamos el horario (8:30 a 9:30)
        nuevaSesion.setHoraInicio(LocalTime.of(8, 30));
        nuevaSesion.setHoraFin(LocalTime.of(9, 30));

        // Resultado Esperado: El método de negocio debe detectar el conflicto (Caja Blanca)
        assertTrue("El sistema debe detectar conflicto de horario", nuevaSesion.tieneConflictoHorario(sesionOriginal));
    }

    // Caso de Prueba: SG-04 - Modificar sesión programada
    @Test
    public void testSG04_ModificarSesionProgramada() {
        // Ejecución: Aumentamos el cupo
        sesionOriginal.setCupoMaximo(20);

        // Resultado Esperado
        assertEquals(Integer.valueOf(20), sesionOriginal.getCupoMaximo());
    }

    // Caso de Prueba: SG-06 - Cancelar sesión
    @Test
    public void testSG06_CancelarSesion() {
        // Pre-condición
        assertEquals("PROGRAMADA", sesionOriginal.getEstado());

        // Ejecución
        sesionOriginal.setEstado("CANCELADA");

        // Resultado Esperado
        assertEquals("CANCELADA", sesionOriginal.getEstado());
    }
}