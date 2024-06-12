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

1N -> Titular
1N -> Usuario

 */

@Entity
@Table (name = "TipoDocumento")
class TipoDocumento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    
    @Column (name = "especificacion")
    private String especificacion;
    
    @OneToMany(mappedBy = "tipoDocumento")
    private List<Titular> titulares = new ArrayList<>();
    
    @OneToMany(mappedBy = "tipoDocumento")
    private List<Usuario> usuarios = new ArrayList<>();

    public TipoDocumento() {
    }

    public TipoDocumento(int id, String especificacion) {
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

    public List<Titular> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Titular> titulares) {
        this.titulares = titulares;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
