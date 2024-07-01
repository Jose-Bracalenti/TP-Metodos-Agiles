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
@Table (name = "Usuario")
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    
    @Column (name = "nombre")
    private String nombre;
    
    @Column (name = "apellido")
    private String apellido;
    
    @Column (name = "numeroDocumento")
    private String numeroDocumento;
    
    @Column(name = "cuil")
    private String cuil;
    
    @Column (name = "nombreUsuario")
    private String nombreUsuario;
    
    @Column (name = "contraseña")
    private String contrasenia;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private RolEnum rol;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "id_tipoDocumento")
    private TipoDocumento tipoDocumento;
    
    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String numeroDocumento, String cuil, String nombreUsuario, String contrasenia, RolEnum rol, TipoDocumento tipoDocumento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDocumento = numeroDocumento;
        this.cuil = cuil;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.tipoDocumento = tipoDocumento;
    }

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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public RolEnum getRol() {
        return rol;
    }

    public void setRol(RolEnum rol) {
        this.rol = rol;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    
    
}
