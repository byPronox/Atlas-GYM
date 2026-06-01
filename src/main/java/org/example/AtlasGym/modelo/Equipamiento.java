package org.example.AtlasGym.modelo;

import javax.persistence.*;
import java.time.*;
import org.openxava.annotations.*;

@Entity
public class Equipamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idEquipamiento;

    @Required
    @Column(length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Required
    @Column(length = 50)
    private String categoria;

    @Required
    private Integer cantidadDisponible;

    @Required
    @Column(length = 20)
    private String estado; // OPERATIVO, EN_MANTENIMIENTO, DADO_DE_BAJA

    @Required
    private LocalDate fechaAdquisicion;

    private LocalDate fechaBaja;

    @Column(length = 255)
    private String motivoBaja;

    // --- GETTERS Y SETTERS ---
    public Long getIdEquipamiento() { return idEquipamiento; }
    public void setIdEquipamiento(Long idEquipamiento) { this.idEquipamiento = idEquipamiento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Integer getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(Integer cantidadDisponible) { this.cantidadDisponible = cantidadDisponible; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDate getFechaAdquisicion() { return fechaAdquisicion; }
    public void setFechaAdquisicion(LocalDate fechaAdquisicion) { this.fechaAdquisicion = fechaAdquisicion; }
    public LocalDate getFechaBaja() { return fechaBaja; }
    public void setFechaBaja(LocalDate fechaBaja) { this.fechaBaja = fechaBaja; }
    public String getMotivoBaja() { return motivoBaja; }
    public void setMotivoBaja(String motivoBaja) { this.motivoBaja = motivoBaja; }
}