package org.example.AtlasGym.modelo.modulo02_rutinas;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.example.AtlasGym.modelo.Rutina;

public class RutinaTest {

    private Rutina rutina;

    @Before
    public void setUp() {
        rutina = new Rutina();
        rutina.setNombre("Fuerza Base");
        rutina.setDescripcionGeneral("Rutina de acondicionamiento");
        rutina.setNivelDificultad("INTERMEDIO");
        rutina.setObjetivoPrincipal("FUERZA");
    }

    // Caso de Prueba: RT-01 - Crear rutina con datos completos
    @Test
    public void testRT01_CrearRutinaDatosCompletos() {
        assertEquals("Fuerza Base", rutina.getNombre());
        assertEquals("Rutina de acondicionamiento", rutina.getDescripcionGeneral());
        assertEquals("INTERMEDIO", rutina.getNivelDificultad());
        assertEquals("FUERZA", rutina.getObjetivoPrincipal());
    }

    // Caso de Prueba: RT-04 - Modificar datos generales de rutina
    @Test
    public void testRT04_ModificarDatosRutina() {
        // Ejecución: Cambiar nivel de INTERMEDIO a AVANZADO
        rutina.setNivelDificultad("AVANZADO");
        rutina.setNombre("Fuerza Avanzada");

        // Resultado Esperado
        assertEquals("AVANZADO", rutina.getNivelDificultad());
        assertEquals("Fuerza Avanzada", rutina.getNombre());
    }


}