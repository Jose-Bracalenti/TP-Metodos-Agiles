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

N1 -> Domicilio
N1 -> TipoDocumento
1N -> Licencia

 */

@Entity
@Table (name = "Contribuyente")
public class Contribuyente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    
    @Column (name = "nombre")
    private String nombre;
    
    @Column (name = "apellido")
    private String apellido;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private SexoEnum sexo;
    
    @Column (name = "numerodocumento")
    private String numerodocumento;
    
    @Column(name = "cuil")
    private String cuil;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FechaNacimiento")
    private Date fechaNacimiento;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "grupoSanguineo")
    private GrupoSanguineoEnum grupoSanguineo;
    
    @Enumerated(EnumType.STRING)
    @Column (name = "factorRH")
    private FactorRHEnum factorRH;
    
    @Column (name = "donanteDeOrganos")
    private Boolean donanteDeOrganos;
    
    @Column(name = "calle")
    private String calle;
    
    @Column(name = "piso")
    private Integer numero;
    
    @Column(name = "numero")
    private Integer piso;
    
    @Column(name = "departamento")
    private String departamento;
    
    @Column (name = "tipoDocumento")
    private String tipoDocumento;

    //Constructores
    public Contribuyente() {
    }

    
    public Contribuyente(int id, String nombre, String apellido, SexoEnum sexo, String numerodocumento, String cuil, Date fechaNacimiento, GrupoSanguineoEnum grupoSanguineo, FactorRHEnum factorRH, Boolean donanteDeOrganos, String calle, Integer numero, Integer piso, String departamento, String tipoDocumento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.numerodocumento = numerodocumento;
        this.cuil = cuil;
        this.fechaNacimiento = fechaNacimiento;
        this.grupoSanguineo = grupoSanguineo;
        this.factorRH = factorRH;
        this.donanteDeOrganos = donanteDeOrganos;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.departamento = departamento;
        this.tipoDocumento = tipoDocumento;
    }

    

    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public GrupoSanguineoEnum getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(GrupoSanguineoEnum grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public FactorRHEnum getFactorRH() {
        return factorRH;
    }

    public void setFactorRH(FactorRHEnum factorRH) {
        this.factorRH = factorRH;
    }

    public Boolean getDonanteDeOrganos() {
        return donanteDeOrganos;
    }

    public void setDonanteDeOrganos(Boolean donanteDeOrganos) {
        this.donanteDeOrganos = donanteDeOrganos;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    
}
