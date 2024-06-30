/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/*

1N -> Licencia

 */

@Entity
@Table (name = "HistoricoLicencia")
public class HistoricoLicencia implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    
    @Column(name = "nroLicencia")
    private long nroLicencia;
    
    @Column (name = "clase")
    private String clase;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaInicioVigencia")
    private Date fechaInicioVigencia;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaFinVigencia")
    private Date fechaFinVigencia;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_Licencia")
    private Licencia licencia;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_Titular")
    private Titular titular;

    public HistoricoLicencia() {
    }

    public HistoricoLicencia(int id, long nroLicencia, Date fechaInicioVigencia, Date fechaFinVigencia, Licencia licencia, Titular titular) {
        this.id = id;
        this.nroLicencia = nroLicencia;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.licencia = licencia;
        this.titular = titular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNroLicencia() {
        return nroLicencia;
    }

    public void setNroLicencia(long nroLicencia) {
        this.nroLicencia = nroLicencia;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
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

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }
    
    
    
}
