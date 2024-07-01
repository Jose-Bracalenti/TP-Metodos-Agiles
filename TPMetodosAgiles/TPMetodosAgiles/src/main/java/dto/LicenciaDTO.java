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
    private TitularDTO titularDTO;
    private ClaseLicencia clase;
    private Date fechaInicioVigencia;
    private Date fechaFinVigencia;
    private Integer numeroCopia;
    private String observacion;
    private Long numeroLicencia;

    public LicenciaDTO() {
    }

    public LicenciaDTO(TitularDTO titularDTO, ClaseLicencia clase, Date fechaInicioVigencia, Date fechaFinVigencia, Integer numeroCopia, String observacion, Long numeroLicencia) {
        this.titularDTO = titularDTO;
        this.clase = clase;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.numeroCopia = numeroCopia;
        this.observacion = observacion;
        this.numeroLicencia = numeroLicencia;
    }

    public TitularDTO getTitularDTO() {
        return titularDTO;
    }

    public void setTitular(TitularDTO titular) {
        this.titularDTO = titular;
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

    public Long getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(Long numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }
    
}
