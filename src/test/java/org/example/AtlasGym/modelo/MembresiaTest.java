package org.example.AtlasGym.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class MembresiaTest {

    @Test
    public void calculaFechaDeVencimientoSegunTipoDeMembresia() {
        Atleta atleta = new Atleta();
        atleta.setNombreCompleto("Carlos Andrade");

        TipoMembresia tipo = new TipoMembresia();
        tipo.setNombrePlan("Mensual");
        tipo.setDuracionDias(30);

        LocalDate fechaInicio = LocalDate.of(2026, 6, 1);

        Membresia membresia = new Membresia();
        membresia.setAtleta(atleta);
        membresia.setTipoMembresia(tipo);
        membresia.setFechaInicio(fechaInicio);
        membresia.setFechaVencimiento(fechaInicio.plusDays(tipo.getDuracionDias()));
        membresia.setEstado("ACTIVA");

        assertSame(atleta, membresia.getAtleta());
        assertSame(tipo, membresia.getTipoMembresia());
        assertEquals(LocalDate.of(2026, 7, 1), membresia.getFechaVencimiento());
        assertEquals("ACTIVA", membresia.getEstado());
    }
}
