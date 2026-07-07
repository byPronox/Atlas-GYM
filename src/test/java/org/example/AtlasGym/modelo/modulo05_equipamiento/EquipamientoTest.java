package org.example.AtlasGym.modelo.modulo05_equipamiento;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.example.AtlasGym.modelo.Equipamiento;

public class EquipamientoTest {

    private Equipamiento maquina;

    @Before
    public void setUp() {
        maquina = new Equipamiento();
        maquina.setNombre("Cinta de Correr");
        maquina.setCategoria("Cardio");
        maquina.setCantidadDisponible(5);
        maquina.setEstado("OPERATIVO");
        maquina.setFechaAdquisicion(LocalDate.of(2025, 1, 10));
    }

    // Caso de Prueba: EQ-01 - Registrar nuevo ítem de equipamiento
    @Test
    public void testEQ01_RegistrarEquipamiento() {
        assertEquals("Cinta de Correr", maquina.getNombre());
        assertEquals("Cardio", maquina.getCategoria());
        assertEquals(Integer.valueOf(5), maquina.getCantidadDisponible());
        assertEquals("OPERATIVO", maquina.getEstado());
    }

    // Caso de Prueba: EQ-03 - Actualizar datos de equipamiento
    @Test
    public void testEQ03_ActualizarEquipamiento() {
        // Ejecución
        maquina.setCantidadDisponible(4);
        maquina.setDescripcion("Requiere lubricación semanal");

        // Resultado Esperado
        assertEquals(Integer.valueOf(4), maquina.getCantidadDisponible());
        assertEquals("Requiere lubricación semanal", maquina.getDescripcion());
    }

    // Caso de Prueba: EQ-04 - Dar de baja equipamiento con fecha y motivo
    @Test
    public void testEQ04_DarDeBajaConMotivo() {
        LocalDate fechaBaja = LocalDate.now();
        String motivo = "Motor quemado, reparación inviable";

        // Ejecución
        maquina.darDeBaja(fechaBaja, motivo);

        // Resultado Esperado
        assertEquals("DADO_DE_BAJA", maquina.getEstado());
        assertEquals(fechaBaja, maquina.getFechaBaja());
        assertEquals(motivo, maquina.getMotivoBaja());
    }

    // Caso de Prueba: EQ-05 - Dar de baja sin ingresar motivo (bloqueado)
    @Test(expected = IllegalStateException.class)
    public void testEQ05_DarDeBajaSinMotivoBloqueado() {
        // Ejecución: Intentar dar de baja pasando un string vacío
        maquina.darDeBaja(LocalDate.now(), "   ");
        // DEBE lanzar IllegalStateException
    }
}