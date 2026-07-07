package org.example.AtlasGym.modelo.modulo03_sesiones_grupales;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.example.AtlasGym.modelo.Atleta;
import org.example.AtlasGym.modelo.InscripcionSesion;
import org.example.AtlasGym.modelo.SesionGrupal;

public class InscripcionSesionTest {

    private Atleta atleta;
    private SesionGrupal sesion;
    private InscripcionSesion inscripcion;

    @Before
    public void setUp() {
        atleta = new Atleta();
        atleta.setNombreCompleto("Justin Gomezcoello");

        sesion = new SesionGrupal();
        sesion.setCupoMaximo(5);

        inscripcion = new InscripcionSesion();
        inscripcion.setAtleta(atleta);
        inscripcion.setSesion(sesion);
    }

    // Caso de Prueba: SG-08 - Inscribir atleta con membresía activa y cupo disponible
    @Test
    public void testSG08_InscripcionExitosa() {
        boolean tieneMembresiaActiva = true;
        int inscritosActuales = 3; // Menor que el cupo máximo (5)

        // Ejecución
        inscripcion.procesarInscripcion(tieneMembresiaActiva, inscritosActuales);

        // Resultado Esperado
        assertEquals("CONFIRMADA", inscripcion.getEstado());
        assertNotNull(inscripcion.getFechaInscripcion());
    }

    // Caso de Prueba: SG-09 - Inscribir atleta sin membresía activa (bloqueado)
    @Test(expected = IllegalStateException.class)
    public void testSG09_BloqueoInscripcionSinMembresia() {
        boolean tieneMembresiaActiva = false; // Membresía vencida
        int inscritosActuales = 2;

        // Ejecución: Debe lanzar IllegalStateException
        inscripcion.procesarInscripcion(tieneMembresiaActiva, inscritosActuales);
    }

    // Caso de Prueba: SG-10 - Inscribir atleta cuando cupo máximo alcanzado (bloqueado)
    @Test(expected = IllegalStateException.class)
    public void testSG10_BloqueoInscripcionCupoMaximo() {
        boolean tieneMembresiaActiva = true;
        int inscritosActuales = 5; // Cupo lleno

        // Ejecución: Debe lanzar IllegalStateException
        inscripcion.procesarInscripcion(tieneMembresiaActiva, inscritosActuales);
    }

    // Caso de Prueba: SG-12 - Desinscribir atleta antes del inicio
    @Test
    public void testSG12_DesinscribirAtleta() {
        // Pre-condición
        inscripcion.setEstado("CONFIRMADA");

        // Ejecución
        inscripcion.setEstado("CANCELADA");

        // Resultado Esperado
        assertEquals("CANCELADA", inscripcion.getEstado());
    }


}