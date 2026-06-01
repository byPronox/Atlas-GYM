package org.example.AtlasGym.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idRutina;

    @Required
    @Column(length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcionGeneral;

    @Required
    @Column(length = 50)
    private String nivelDificultad; // Básico, Intermedio, Avanzado

    @Required
    @Column(length = 100)
    private String objetivoPrincipal;

    // --- GETTERS Y SETTERS ---
    public Long getIdRutina() { return idRutina; }
    public void setIdRutina(Long idRutina) { this.idRutina = idRutina; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcionGeneral() { return descripcionGeneral; }
    public void setDescripcionGeneral(String descripcionGeneral) { this.descripcionGeneral = descripcionGeneral; }
    public String getNivelDificultad() { return nivelDificultad; }
    public void setNivelDificultad(String nivelDificultad) { this.nivelDificultad = nivelDificultad; }
    public String getObjetivoPrincipal() { return objetivoPrincipal; }
    public void setObjetivoPrincipal(String objetivoPrincipal) { this.objetivoPrincipal = objetivoPrincipal; }
}