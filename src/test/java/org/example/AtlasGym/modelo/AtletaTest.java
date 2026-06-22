package org.example.AtlasGym.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class AtletaTest {

    @Test
    public void registraDatosBasicosDelAtleta() {
        Atleta atleta = new Atleta();

        atleta.setNombreCompleto("Maria Perez");
        atleta.setCedula("0912345678");
        atleta.setFechaNacimiento(LocalDate.of(2000, 5, 10));
        atleta.setGenero("Femenino");
        atleta.setTelefono("0999999999");
        atleta.setCorreoElectronico("maria.perez@correo.com");
        atleta.setEstado("ACTIVO");

        assertEquals("Maria Perez", atleta.getNombreCompleto());
        assertEquals("0912345678", atleta.getCedula());
        assertEquals(LocalDate.of(2000, 5, 10), atleta.getFechaNacimiento());
        assertEquals("Femenino", atleta.getGenero());
        assertEquals("0999999999", atleta.getTelefono());
        assertEquals("maria.perez@correo.com", atleta.getCorreoElectronico());
        assertEquals("ACTIVO", atleta.getEstado());
    }
}
