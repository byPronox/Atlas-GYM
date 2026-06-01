package org.example.AtlasGym.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.*;
import org.openxava.annotations.*;

@Entity
public class EvaluacionFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long idEvaluacion;

    @Required
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombreCompleto")
    @Required
    private Atleta atleta;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "username")
    @Required
    private Usuario entrenador;

    @Required
    @Column(precision = 5, scale = 2)
    private BigDecimal peso;

    @Required
    @Column(precision = 4, scale = 2)
    private BigDecimal estatura;

    @Column(precision = 5, scale = 2)
    private BigDecimal imc;

    @Column(length = 255)
    private String medidasCorporales;

    @Column(length = 255)
    private String marcasRendimiento;

    // --- GETTERS Y SETTERS ---
    public Long getIdEvaluacion() { return idEvaluacion; }
    public void setIdEvaluacion(Long idEvaluacion) { this.idEvaluacion = idEvaluacion; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public Atleta getAtleta() { return atleta; }
    public void setAtleta(Atleta atleta) { this.atleta = atleta; }
    public Usuario getEntrenador() { return entrenador; }
    public void setEntrenador(Usuario entrenador) { this.entrenador = entrenador; }
    public BigDecimal getPeso() { return peso; }
    public void setPeso(BigDecimal peso) { this.peso = peso; }
    public BigDecimal getEstatura() { return estatura; }
    public void setEstatura(BigDecimal estatura) { this.estatura = estatura; }
    public BigDecimal getImc() { return imc; }
    public void setImc(BigDecimal imc) { this.imc = imc; }
    public String getMedidasCorporales() { return medidasCorporales; }
    public void setMedidasCorporales(String medidasCorporales) { this.medidasCorporales = medidasCorporales; }
    public String getMarcasRendimiento() { return marcasRendimiento; }
    public void setMarcasRendimiento(String marcasRendimiento) { this.marcasRendimiento = marcasRendimiento; }
}