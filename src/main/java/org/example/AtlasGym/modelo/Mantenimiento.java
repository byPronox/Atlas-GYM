package org.example.AtlasGym.modelo;

import javax.persistence.*;
import java.time.*;
import org.openxava.annotations.*;

@Entity
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idMantenimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombre")
    @Required
    private Equipamiento equipamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "username")
    @Required
    private Usuario administrador;

    @Required
    @Column(length = 15)
    private String tipo; // PREVENTIVO, CORRECTIVO

    @Required
    private LocalDate fechaProgramada;

    private LocalDate fechaRealizada;

    @Required
    @Column(length = 255)
    private String descripcionActividad;

    @Column(length = 255)
    private String observacionesResultado;

    @Required
    @Column(length = 10)
    private String estado; // PENDIENTE, EJECUTADO, VENCIDO

    // ==========================================
    // LÓGICA DE NEGOCIO (CAJA BLANCA)
    // ==========================================

    @PrePersist
    public void validarProgramacion() {
        if (this.equipamiento != null && "DADO_DE_BAJA".equals(this.equipamiento.getEstado())) {
            throw new IllegalStateException("No se puede programar mantenimiento a un ítem dado de baja");
        }
    }

    public void registrarEjecucion(java.time.LocalDate fecha, String observaciones) {
        this.estado = "EJECUTADO";
        this.fechaRealizada = fecha;
        this.observacionesResultado = observaciones;

        if (this.equipamiento != null) {
            this.equipamiento.setEstado("OPERATIVO");
        }
    }

    @org.openxava.annotations.Depends("fechaProgramada, estado")
    public boolean estaVencido() {
        if ("PENDIENTE".equals(this.estado) && this.fechaProgramada != null) {
            return this.fechaProgramada.isBefore(java.time.LocalDate.now());
        }
        return false;
    }

    // --- GETTERS Y SETTERS ---
    public Long getIdMantenimiento() { return idMantenimiento; }
    public void setIdMantenimiento(Long idMantenimiento) { this.idMantenimiento = idMantenimiento; }
    public Equipamiento getEquipamiento() { return equipamiento; }
    public void setEquipamiento(Equipamiento equipamiento) { this.equipamiento = equipamiento; }
    public Usuario getAdministrador() { return administrador; }
    public void setAdministrador(Usuario administrador) { this.administrador = administrador; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public LocalDate getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(LocalDate fechaProgramada) { this.fechaProgramada = fechaProgramada; }
    public LocalDate getFechaRealizada() { return fechaRealizada; }
    public void setFechaRealizada(LocalDate fechaRealizada) { this.fechaRealizada = fechaRealizada; }
    public String getDescripcionActividad() { return descripcionActividad; }
    public void setDescripcionActividad(String descripcionActividad) { this.descripcionActividad = descripcionActividad; }
    public String getObservacionesResultado() { return observacionesResultado; }
    public void setObservacionesResultado(String observacionesResultado) { this.observacionesResultado = observacionesResultado; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}