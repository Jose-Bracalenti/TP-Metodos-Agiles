
package models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
/*

1N -> Titular

 */

@Entity
@Table (name = "Domicilio")
public class Domicilio implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    
    @Column(name = "calle")
    private String calle;
    
    @Column(name = "piso")
    private Integer numero;
    
    @Column(name = "numero")
    private Integer piso;
    
    @Column(name = "departamento")
    private String departamento;
    
    @OneToMany(mappedBy="domicilio")
    private List<Titular> titulares;

    //Constructores
    
    public Domicilio() {
    }

    public Domicilio(int id, String calle, Integer numero, Integer piso, String departamento) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.departamento = departamento;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Titular> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Titular> titulares) {
        this.titulares = titulares;
    }
    
    
    
}
