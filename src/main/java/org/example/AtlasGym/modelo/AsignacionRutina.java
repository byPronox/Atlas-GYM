package org.example.AtlasGym.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import org.openxava.annotations.*;

@Entity
public class AsignacionRutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idAsignacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombreCompleto")
    @Required
    private Atleta atleta;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombre")
    @Required
    private Rutina rutina;

    @Required
    private LocalDate fechaAsignacion;

    private boolean activa;

    @PrePersist
    @PreUpdate
    public void validarAtletaActivo() {
        if (atleta != null && !"ACTIVO".equals(atleta.getEstado())) {
            throw new IllegalStateException("Solo se pueden asignar rutinas a atletas activos");
        }
    }

    // --- GETTERS Y SETTERS ---
    public Long getIdAsignacion() { return idAsignacion; }
    public void setIdAsignacion(Long idAsignacion) { this.idAsignacion = idAsignacion; }
    public Atleta getAtleta() { return atleta; }
    public void setAtleta(Atleta atleta) { this.atleta = atleta; }
    public Rutina getRutina() { return rutina; }
    public void setRutina(Rutina rutina) { this.rutina = rutina; }
    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDate fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}