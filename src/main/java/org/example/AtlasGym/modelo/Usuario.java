package org.example.AtlasGym.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idUsuario;

    @Required
    @Column(length = 50, unique = true)
    private String username;

    @Required
    @Column(length = 255)
    private String passwordHash;

    @Required
    @Column(length = 100)
    private String correo;

    private boolean estado;

    @Required
    @Column(length = 20)
    private String rol; // Aquí se digitará ADMINISTRADOR o ENTRENADOR

    // --- GETTERS Y SETTERS ---
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}