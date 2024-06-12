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

N1 -> Domicilio
N1 -> TipoDocumento
1N -> Licencia

 */

@Entity
@Table (name = "Titular")
class Titular implements Serializable{
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
    
    @Column (name = "numeroDocumento")
    private String nroDocumento;
    
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
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "id_Domicilio")
    private Domicilio domicilio;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "id_tipoDocumento")
    private TipoDocumento tipoDocumento;
    
    @OneToMany (mappedBy = "titular")
    private List<Licencia> licencias = new ArrayList();

    //Constructores
    public Titular() {
    }

    public Titular(int id, String nombre, String apellido, SexoEnum sexo, String nroDocumento, String cuil, Date fechaNacimiento, GrupoSanguineoEnum grupoSanguineo, FactorRHEnum factorRH, Boolean donanteDeOrganos, Domicilio domicilio, TipoDocumento tipoDocumento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nroDocumento = nroDocumento;
        this.cuil = cuil;
        this.fechaNacimiento = fechaNacimiento;
        this.grupoSanguineo = grupoSanguineo;
        this.factorRH = factorRH;
        this.donanteDeOrganos = donanteDeOrganos;
        this.domicilio = domicilio;
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

    public String getNumeroDocumento() {
        return nroDocumento;
    }

    public void setNumeroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
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

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<Licencia> getLicencias() {
        return licencias;
    }

    public void setLicencias(List<Licencia> licencias) {
        this.licencias = licencias;
    }    
    
}
