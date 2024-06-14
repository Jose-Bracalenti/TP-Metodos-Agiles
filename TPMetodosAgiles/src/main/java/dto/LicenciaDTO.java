/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;
import models.entities.ClaseLicencia;
import models.entities.Titular;

/**
 *
 * @author Juani
 */
public class LicenciaDTO {
    private Titular titular;
    private ClaseLicencia clase;
    private Date fechaInicioVigencia;
    private Date fechaFinVigencia;
    private Integer numeroCopia;
    private String observacion;
    private String numeroLicencia;

    public LicenciaDTO() {
    }

    public LicenciaDTO(Titular titular, ClaseLicencia clase, Date fechaInicioVigencia, Date fechaFinVigencia, Integer numeroCopia, String observacion, String numeroLicencia) {
        this.titular = titular;
        this.clase = clase;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.numeroCopia = numeroCopia;
        this.observacion = observacion;
        this.numeroLicencia = numeroLicencia;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public ClaseLicencia getClase() {
        return clase;
    }

    public void setClase(ClaseLicencia clase) {
        this.clase = clase;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public Integer getNumeroCopia() {
        return numeroCopia;
    }

    public void setNumeroCopia(Integer numeroCopia) {
        this.numeroCopia = numeroCopia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }
    
}
