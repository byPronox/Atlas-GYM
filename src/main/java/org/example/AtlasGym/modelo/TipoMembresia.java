package org.example.AtlasGym.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
public class TipoMembresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idTipoMembresia;

    @Required
    @Column(length = 100)
    private String nombrePlan;

    @Required
    private Integer duracionDias;

    @Column(length = 255)
    private String descripcion;

    // --- GETTERS Y SETTERS ---
    public Long getIdTipoMembresia() { return idTipoMembresia; }
    public void setIdTipoMembresia(Long idTipoMembresia) { this.idTipoMembresia = idTipoMembresia; }
    public String getNombrePlan() { return nombrePlan; }
    public void setNombrePlan(String nombrePlan) { this.nombrePlan = nombrePlan; }
    public Integer getDuracionDias() { return duracionDias; }
    public void setDuracionDias(Integer duracionDias) { this.duracionDias = duracionDias; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}