/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/*

1N -> Licencia

 */

@Entity
@Table (name = "ClaseLicencia")
class ClaseLicencia implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    
    @Column (name = "especificacion")
    private String especificacion;
    
    @OneToMany (mappedBy = "claseLicencia")
    private List<Licencia> licencias = new ArrayList();

    public ClaseLicencia() {
    }

    public ClaseLicencia(int id, String especificacion) {
        this.id = id;
        this.especificacion = especificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    public List<Licencia> getLicencias() {
        return licencias;
    }

    public void setLicencias(List<Licencia> licencias) {
        this.licencias = licencias;
    }

}
