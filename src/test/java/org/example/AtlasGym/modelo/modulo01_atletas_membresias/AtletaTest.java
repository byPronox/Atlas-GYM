package org.example.AtlasGym.modelo.modulo01_atletas_membresias;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import org.example.AtlasGym.modelo.Atleta;

public class AtletaTest {

    private Atleta atleta;

    @Before
    public void setUp() {
        atleta = new Atleta();
        atleta.setNombreCompleto("Maria Perez");
        atleta.setCedula("0912345678");
        atleta.setFechaNacimiento(LocalDate.of(2000, 5, 10));
        atleta.setGenero("Femenino");
        atleta.setTelefono("0999999999");
        atleta.setCorreoElectronico("maria.perez@correo.com");
        atleta.setEstado("ACTIVO");
    }

    // Caso de Prueba: AM-01 - Registrar atleta con datos completos
    @Test
    public void testAM01_RegistrarAtletaDatosCompletos() {
        assertEquals("Maria Perez", atleta.getNombreCompleto());
        assertEquals("0912345678", atleta.getCedula());
        assertEquals(LocalDate.of(2000, 5, 10), atleta.getFechaNacimiento());
        assertEquals("ACTIVO", atleta.getEstado());
        assertNotNull(atleta.getCorreoElectronico());
    }

    // Caso de Prueba: AM-04 - Actualizar datos de atleta
    @Test
    public void testAM04_ActualizarDatosAtleta() {
        atleta.setTelefono("0988888888");
        atleta.setCorreoElectronico("maria.nueva@correo.com");

        assertEquals("0988888888", atleta.getTelefono());
        assertEquals("maria.nueva@correo.com", atleta.getCorreoElectronico());
    }

    // Caso de Prueba: AM-05 - Inactivar atleta activo
    @Test
    public void testAM05_InactivarAtletaActivo() {
        assertEquals("ACTIVO", atleta.getEstado());
        atleta.setEstado("INACTIVO");
        assertEquals("INACTIVO", atleta.getEstado());
    }

    // Caso de Prueba: AM-06 - Reactivar atleta inactivo
    @Test
    public void testAM06_ReactivarAtletaInactivo() {
        atleta.setEstado("INACTIVO");
        atleta.setEstado("ACTIVO");
        assertEquals("ACTIVO", atleta.getEstado());
    }
}