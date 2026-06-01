package org.example.AtlasGym.modelo;

import javax.persistence.*;
import java.time.*;
import org.openxava.annotations.*;

@Entity
public class SesionGrupal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idSesion;

    @Required
    @Column(length = 100)
    private String nombre;

    @Required
    private LocalDate fecha;

    @Required
    private LocalTime horaInicio;

    @Required
    private LocalTime horaFin;

    @Required
    private Integer cupoMaximo;

    @Required
    @Column(length = 15)
    private String estado; // PROGRAMADA, CANCELADA, FINALIZADA

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "username")
    @Required
    private Usuario entrenador;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombre")
    @Required
    private Rutina rutina;

    // --- GETTERS Y SETTERS ---
    public Long getIdSesion() { return idSesion; }
    public void setIdSesion(Long idSesion) { this.idSesion = idSesion; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public Integer getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(Integer cupoMaximo) { this.cupoMaximo = cupoMaximo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Usuario getEntrenador() { return entrenador; }
    public void setEntrenador(Usuario entrenador) { this.entrenador = entrenador; }
    public Rutina getRutina() { return rutina; }
    public void setRutina(Rutina rutina) { this.rutina = rutina; }
}