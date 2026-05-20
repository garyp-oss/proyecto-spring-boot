package com.example.farmacia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String principioActivo;
    private String formato;
    private Double precioEuros;
    private Boolean conReceta;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    // ===== CONSTRUCTORES =====
    public Medicamento() {}

    public Medicamento(String nombre, String principioActivo, String formato,
                       Double precioEuros, Boolean conReceta, Laboratorio laboratorio) {
        this.nombre = nombre;
        this.principioActivo = principioActivo;
        this.formato = formato;
        this.precioEuros = precioEuros;
        this.conReceta = conReceta;
        this.laboratorio = laboratorio;
    }

    // ===== GETTERS Y SETTERS =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPrincipioActivo() { return principioActivo; }
    public void setPrincipioActivo(String principioActivo) { this.principioActivo = principioActivo; }

    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }

    public Double getPrecioEuros() { return precioEuros; }
    public void setPrecioEuros(Double precioEuros) { this.precioEuros = precioEuros; }

    public Boolean getConReceta() { return conReceta; }
    public void setConReceta(Boolean conReceta) { this.conReceta = conReceta; }

    public Laboratorio getLaboratorio() { return laboratorio; }
    public void setLaboratorio(Laboratorio laboratorio) { this.laboratorio = laboratorio; }
}