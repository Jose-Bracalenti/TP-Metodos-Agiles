/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/*

1N -> Titular
1N -> Usuario

 */

@Entity
@Table (name = "Licencia")
class Licencia implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nroLicencia", unique = true)
    private long nroLicencia;
    
    @Column (name = "nroCopia")
    private int nroCopia;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaInicioVigencia")
    private Date fechaInicioVigencia;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaFinVigencia")
    private Date fechaFinVigencia;
    
    @Column (name = "observacion")
    private String observacion;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_titular")
    private Titular titular;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_claseLicencia")
    private ClaseLicencia claseLicencia;
    
    @OneToMany (mappedBy = "licencia")
    private List<HistoricoLicencia> historicosLicencia = new ArrayList();
    
    @OneToMany (mappedBy = "licencia")
    private List<RegistroTramite> registroTramites = new ArrayList();

    public Licencia() {
    }

    public Licencia(int id, long nroLicencia, int nroCopia, Date fechaInicioVigencia, Date fechaFinVigencia, String observacion, Titular titular, ClaseLicencia claseLicencia) {
        this.id = id;
        this.nroLicencia = nroLicencia;
        this.nroCopia = nroCopia;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.observacion = observacion;
        this.titular = titular;
        this.claseLicencia = claseLicencia;
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

    public int getNroCopia() {
        return nroCopia;
    }

    public void setNroCopia(int nroCopia) {
        this.nroCopia = nroCopia;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public ClaseLicencia getClaseLicencia() {
        return claseLicencia;
    }

    public void setClaseLicencia(ClaseLicencia claseLicencia) {
        this.claseLicencia = claseLicencia;
    }

    public List<HistoricoLicencia> getHistoricosLicencia() {
        return historicosLicencia;
    }

    public void setHistoricosLicencia(List<HistoricoLicencia> historicosLicencia) {
        this.historicosLicencia = historicosLicencia;
    }

    public List<RegistroTramite> getRegistroTramites() {
        return registroTramites;
    }

    public void setRegistroTramites(List<RegistroTramite> registroTramites) {
        this.registroTramites = registroTramites;
    }
    
    
}
