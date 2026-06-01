package org.example.AtlasGym.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
public class RutinaEjercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idRutinaEjercicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombre")
    @Required
    private Rutina rutina;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombre")
    @Required
    private Ejercicio ejercicio;

    @Required
    private Integer series;

    @Required
    private Integer repeticiones;

    @Required
    private Integer orden;

    @Column(length = 255)
    private String observaciones;

    // --- GETTERS Y SETTERS ---
    public Long getIdRutinaEjercicio() { return idRutinaEjercicio; }
    public void setIdRutinaEjercicio(Long idRutinaEjercicio) { this.idRutinaEjercicio = idRutinaEjercicio; }
    public Rutina getRutina() { return rutina; }
    public void setRutina(Rutina rutina) { this.rutina = rutina; }
    public Ejercicio getEjercicio() { return ejercicio; }
    public void setEjercicio(Ejercicio ejercicio) { this.ejercicio = ejercicio; }
    public Integer getSeries() { return series; }
    public void setSeries(Integer series) { this.series = series; }
    public Integer getRepeticiones() { return repeticiones; }
    public void setRepeticiones(Integer repeticiones) { this.repeticiones = repeticiones; }
    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}