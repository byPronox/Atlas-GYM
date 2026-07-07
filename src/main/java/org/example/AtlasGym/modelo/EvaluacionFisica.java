package org.example.AtlasGym.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
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
    private BigDecimal peso; // En Kilogramos

    // CAMBIO CLAVE: Renombramos a estaturaMetros y subimos la precisión a 5
    @Required
    @Column(precision = 5, scale = 2)
    private BigDecimal estaturaMetros;

    @ReadOnly
    @Column(precision = 5, scale = 2)
    private BigDecimal imc;

    @ReadOnly
    @Column(length = 50)
    private String nivelRiesgo;

    @Column(length = 255)
    private String observaciones;

    // ==========================================
    // ALGORITMO DE LÓGICA DE NEGOCIO (CAJA BLANCA)
    // ==========================================

    @PrePersist
    @PreUpdate
    public void calcularIMCYRiesgo() {
        if (peso != null && estaturaMetros != null && estaturaMetros.compareTo(BigDecimal.ZERO) > 0) {

            BigDecimal estaturaCuadrado = estaturaMetros.multiply(estaturaMetros);
            this.imc = peso.divide(estaturaCuadrado, 2, RoundingMode.HALF_UP);

            double imcCalculado = this.imc.doubleValue();

            if (imcCalculado < 18.5) {
                this.nivelRiesgo = "BAJO PESO - Requiere plan de hipertrofia";
            } else if (imcCalculado >= 18.5 && imcCalculado < 24.9) {
                this.nivelRiesgo = "PESO NORMAL - Mantener estado";
            } else if (imcCalculado >= 25.0 && imcCalculado < 29.9) {
                this.nivelRiesgo = "SOBREPESO - Requiere deficit calorico";
            } else {
                this.nivelRiesgo = "OBESIDAD - Derivar a deportologo";
            }
        } else {
            this.imc = BigDecimal.ZERO;
            this.nivelRiesgo = "DATOS INVALIDOS (Revise estatura/peso)";
        }
    }

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
    public BigDecimal getEstaturaMetros() { return estaturaMetros; }
    public void setEstaturaMetros(BigDecimal estaturaMetros) { this.estaturaMetros = estaturaMetros; }
    public BigDecimal getImc() { return imc; }
    public void setImc(BigDecimal imc) { this.imc = imc; }
    public String getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(String nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}