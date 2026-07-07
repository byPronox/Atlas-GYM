package org.example.AtlasGym.modelo.modulo02_rutinas;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.example.AtlasGym.modelo.Ejercicio;
import org.example.AtlasGym.modelo.Rutina;
import org.example.AtlasGym.modelo.RutinaEjercicio;

public class RutinaEjercicioTest {

    private Rutina rutina;
    private Ejercicio pressBanca;

    @Before
    public void setUp() {
        rutina = new Rutina();
        rutina.setNombre("Pecho y Triceps");

        pressBanca = new Ejercicio();
        pressBanca.setNombre("Press de Banca Plano");
        pressBanca.setGrupoMuscular("Pecho");
        pressBanca.setActivo(true);
    }

    // Caso de Prueba: RT-03 - Agregar ejercicios a una rutina
    @Test
    public void testRT03_AgregarEjercicioARutina() {
        RutinaEjercicio composicion = new RutinaEjercicio();
        composicion.setRutina(rutina);
        composicion.setEjercicio(pressBanca);
        composicion.setSeries(3);
        composicion.setRepeticiones(12);
        composicion.setOrden(1);

        assertSame(rutina, composicion.getRutina());
        assertSame(pressBanca, composicion.getEjercicio());
        assertEquals(Integer.valueOf(3), composicion.getSeries());
        assertEquals(Integer.valueOf(12), composicion.getRepeticiones());
        assertEquals(Integer.valueOf(1), composicion.getOrden());
    }

    // Caso de Prueba: RT-05 - Eliminar ejercicio de una rutina
    @Test
    public void testRT05_EliminarEjercicioDeRutina() {
        RutinaEjercicio composicion = new RutinaEjercicio();
        composicion.setRutina(rutina);
        composicion.setEjercicio(pressBanca);

        // Simulamos la acción de desvinculación a nivel de objeto
        composicion.setRutina(null);
        composicion.setEjercicio(null);

        assertNull("La rutina debe quedar desvinculada del detalle", composicion.getRutina());
        assertNull("El ejercicio debe quedar desvinculado del detalle", composicion.getEjercicio());
    }
}