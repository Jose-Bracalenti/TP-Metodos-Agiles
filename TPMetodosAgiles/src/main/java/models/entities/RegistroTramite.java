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
@Table (name = "RegistroTramite")
public class RegistroTramite implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "accion")
    private AccionRealizarEnum accion;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_Licencia")
    private Licencia licencia;

    public RegistroTramite() {
    }

    public RegistroTramite(int id, Date fecha, AccionRealizarEnum accion, Licencia licencia) {
        this.id = id;
        this.fecha = fecha;
        this.accion = accion;
        this.licencia = licencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public AccionRealizarEnum getAccion() {
        return accion;
    }

    public void setAccion(AccionRealizarEnum accion) {
        this.accion = accion;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

}
