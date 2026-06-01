package org.example.AtlasGym.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idEjercicio;

    @Required
    @Column(length = 100)
    private String nombre;

    @Required
    @Column(length = 100)
    private String grupoMuscular;

    @Required
    @Column(length = 50)
    private String tipo;

    private boolean activo;

    // --- GETTERS Y SETTERS ---
    public Long getIdEjercicio() { return idEjercicio; }
    public void setIdEjercicio(Long idEjercicio) { this.idEjercicio = idEjercicio; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getGrupoMuscular() { return grupoMuscular; }
    public void setGrupoMuscular(String grupoMuscular) { this.grupoMuscular = grupoMuscular; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}