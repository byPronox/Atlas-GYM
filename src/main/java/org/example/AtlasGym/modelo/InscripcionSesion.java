package org.example.AtlasGym.modelo;

import javax.persistence.*;
import java.time.*;
import org.openxava.annotations.*;

@Entity
public class InscripcionSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idInscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombre")
    @Required
    private SesionGrupal sesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombreCompleto")
    @Required
    private Atleta atleta;

    @Required
    private LocalDateTime fechaInscripcion;

    @Required
    @Column(length = 15)
    private String estado;

    // --- GETTERS Y SETTERS ---
    public Long getIdInscripcion() { return idInscripcion; }
    public void setIdInscripcion(Long idInscripcion) { this.idInscripcion = idInscripcion; }
    public SesionGrupal getSesion() { return sesion; }
    public void setSesion(SesionGrupal sesion) { this.sesion = sesion; }
    public Atleta getAtleta() { return atleta; }
    public void setAtleta(Atleta atleta) { this.atleta = atleta; }
    public LocalDateTime getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDateTime fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}