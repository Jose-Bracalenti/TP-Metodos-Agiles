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
import models.entities.HistoricoLicencia;
import models.entities.Licencia;

/**
 *
 * @author Juani
 */
public class GestorLicencia {
    
    GestorTitular gestorTitular = new GestorTitular();
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
        licencia.setTitular(gestorTitular.buscarTitular(licenciaDTO.getTitularDTO()).get(0));
        licencia.setClaseLicencia(licenciaDTO.getClase());
        licencia.setFechaInicioVigencia(licenciaDTO.getFechaInicioVigencia());
        licencia.setFechaFinVigencia(licenciaDTO.getFechaFinVigencia());
        licencia.setNroCopia(licenciaDTO.getNumeroCopia());
        licencia.setNroLicencia(Long.parseLong(licenciaDTO.getNumeroLicencia()));
        licencia.setObservacion(licenciaDTO.getObservacion());
        
        return licencia;
    }

    public List<Licencia> buscarLicencia(TitularDTO titularDTO) {
        return licenciaImpl.buscarByTitularDTO(titularDTO);
    }
}
