package org.example.AtlasGym.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import org.openxava.annotations.*;

@Entity
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idAsistencia;

    @OneToOne(fetch = FetchType.LAZY)
    @Required
    private InscripcionSesion inscripcion;

    @Required
    @Column(length = 15)
    private String estado; // ASISTIO, NO_ASISTIO

    @Column(precision = 5, scale = 2)
    private BigDecimal porcentajeAsistencia;

    @PreUpdate
    public void validarModificacionAsistencia() {
        if (this.inscripcion != null && this.inscripcion.getSesion() != null) {
            java.time.LocalDate fechaSesion = this.inscripcion.getSesion().getFecha();
            // Si la fecha de la sesión es anterior a hoy, se bloquea la modificación
            if (fechaSesion.isBefore(java.time.LocalDate.now())) {
                throw new IllegalStateException("El periodo de modificación ha expirado");
            }
        }
    }

    // --- GETTERS Y SETTERS ---
    public Long getIdAsistencia() { return idAsistencia; }
    public void setIdAsistencia(Long idAsistencia) { this.idAsistencia = idAsistencia; }
    public InscripcionSesion getInscripcion() { return inscripcion; }
    public void setInscripcion(InscripcionSesion inscripcion) { this.inscripcion = inscripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getPorcentajeAsistencia() { return porcentajeAsistencia; }
    public void setPorcentajeAsistencia(BigDecimal porcentajeAsistencia) { this.porcentajeAsistencia = porcentajeAsistencia; }
}