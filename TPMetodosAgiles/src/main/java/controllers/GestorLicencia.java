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
import java.util.ArrayList;
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
    EntityManager manager = UtilHibernate.getInstance().getEntityManager();
    GestorTitular gestorTitular = new GestorTitular();
    ClaseLicenciaDAOImpl claseImpl = new ClaseLicenciaDAOImpl();
    LicenciaDAOImpl licenciaImpl = new LicenciaDAOImpl();
    HistoricoLicenciaDAOImpl historicoImpl = new HistoricoDAOImpl();
    
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

    public List<LicenciaDTO> buscarLicenciaDTO(TitularDTO titularDTO) {
        try{
            return  pasarListaADTO(licenciaImpl.buscarByTitularDTO(titularDTO));
        } catch(Exception e){
            return null;
        }
    }

    private List<LicenciaDTO> pasarListaADTO(List<Licencia> licencias) {
        List<LicenciaDTO> licenciasDTO = new ArrayList();
        
        for (Licencia licencia: licencias){
            licenciasDTO.add(pasarADTO(licencia));
        }
        return licenciasDTO; 
    }

    private LicenciaDTO pasarADTO(Licencia licencia) {
        LicenciaDTO licenciaDTO = new LicenciaDTO();
        licenciaDTO.setFechaInicioVigencia(licencia.getFechaInicioVigencia());
        licenciaDTO.setClase(licencia.getClaseLicencia());
        
        return licenciaDTO;
    }
    
    public List<HistoricoLicencia> buscarHistorico(LicenciaDTO licenciaDTO){
        return historicoImpl.buscarByTitularDTO(licenciaDTO);
    }
}
