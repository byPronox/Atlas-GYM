package org.example.AtlasGym.modelo.modulo01_atletas_membresias;

import static org.junit.Assert.*;
import org.junit.Test;

import org.example.AtlasGym.modelo.TipoMembresia;

public class TipoMembresiaTest {

    // Caso de Prueba: AM-10 - Crear tipo de membresía
    @Test
    public void testAM10_CrearTipoDeMembresia() {
        TipoMembresia plan = new TipoMembresia();
        plan.setNombrePlan("Plan Trimestral");
        plan.setDuracionDias(90);
        plan.setDescripcion("Acceso ilimitado por 3 meses");

        assertEquals("Plan Trimestral", plan.getNombrePlan());
        assertEquals(Integer.valueOf(90), plan.getDuracionDias());
        assertEquals("Acceso ilimitado por 3 meses", plan.getDescripcion());
    }
}