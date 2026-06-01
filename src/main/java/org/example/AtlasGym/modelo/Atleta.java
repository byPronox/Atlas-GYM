package org.example.AtlasGym.modelo;

import javax.persistence.*;
import java.time.*;
import org.openxava.annotations.*;

@Entity
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idAtleta;

    @Required
    @Column(length = 150)
    private String nombreCompleto;

    @Required
    @Column(length = 20, unique = true)
    private String cedula;

    @Required
    private LocalDate fechaNacimiento;

    @Required
    @Column(length = 20)
    private String genero;

    @Column(length = 20)
    private String telefono;

    @Required
    @Column(length = 190)
    private String correoElectronico;

    @Required
    @Column(length = 10)
    private String estado; // ACTIVO o INACTIVO

    // --- GETTERS Y SETTERS ---
    public Long getIdAtleta() { return idAtleta; }
    public void setIdAtleta(Long idAtleta) { this.idAtleta = idAtleta; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}