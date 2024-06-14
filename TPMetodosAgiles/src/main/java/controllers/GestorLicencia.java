/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Mappers.MyValidationException;
import Mappers.UtilHibernate;
import dao.ClaseLicenciaDAOImpl;
import dao.LicenciaDAOImpl;
import dto.LicenciaDTO;
import dto.TitularDTO;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import models.entities.ClaseLicencia;
import models.entities.Licencia;

/**
 *
 * @author Juani
 */
public class GestorLicencia {
    
    ClaseLicenciaDAOImpl claseImpl = new ClaseLicenciaDAOImpl();
    LicenciaDAOImpl licenciaImpl = new LicenciaDAOImpl();
    
    public List<Date> mostrarInicioVigencia(TitularDTO titularDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Date> mostrarFinVigencia(TitularDTO titularDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<ClaseLicencia> mostrarClases() {
        return claseImpl.buscarAll();
    }
    
    public List<ClaseLicencia> mostrarClases(String especificacion) {
        return claseImpl.buscarByEspecificacion(especificacion);
    }

    public boolean altaLicencia(LicenciaDTO licenciaDTO) {
        try{
            Licencia licencia = crearLicencia(licenciaDTO);
            licenciaImpl.altaLicencia(licencia);
            return true;
        } catch (Exception e) {
            throw new MyValidationException("Error: Crear Licencia", e);
        }
    }
    
    private Licencia crearLicencia(LicenciaDTO licenciaDTO){
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(licenciaDTO.getClase());
        licencia.setFechaInicioVigencia(licenciaDTO.getFechaInicioVigencia());
        licencia.setFechaFinVigencia(licenciaDTO.getFechaFinVigencia());
        licencia.setNroCopia(licenciaDTO.getNumeroCopia());
        licencia.setNroLicencia(Long.parseLong(licenciaDTO.getNumeroLicencia()));
        licencia.setObservacion(licenciaDTO.getObservacion());
        
        //TENGO QUE AGREGAR QUE SE AGREGUE LA LICENCIA A LA LISTA DE LICENCIAS QUE TIENE CADA CLASE LICENCIA, QUE CREO SERÏA ALGO ASI
        licenciaDTO.getClase().getLicencias().add(licencia); //creo que se podría hacer algo así (?)
        //NO SE POR QUE ME SIGUE TIRANDO ERROR CON LA PRIMARY KEY, PUEDE SER POR LO QUE ME FALTA QUE PUSE AHI ARRIBA
        
        return licencia;
    }
}