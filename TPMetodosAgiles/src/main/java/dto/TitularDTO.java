/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import models.entities.TipoDocumento;

/**
 *
 * @author Juani
 */
public class TitularDTO {
    private String apellido;
    private String nombre;
    private TipoDocumento tipoDoc;
    private String nroDoc;

    public TitularDTO() {
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDocumento getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TipoDocumento tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }
    
    
}
