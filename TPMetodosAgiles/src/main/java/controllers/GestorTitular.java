/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Mappers.MyValidationException;
import Mappers.UtilHibernate;
import dao.TitularDAOImpl;
import dto.TitularDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import messages.Util;
import models.entities.Contribuyente;
import models.entities.TipoDocumento;
import models.entities.Titular;

/**
 *
 * @author Juani
 */
public class GestorTitular {
    EntityManager manager = UtilHibernate.getInstance().getEntityManager();
    TitularDAOImpl titularImpl = new TitularDAOImpl();
    
    
    
    public TitularDTO altaTitular(Titular titular){
        try {
            titularImpl.altaTitular(titular);
            TitularDTO titularDTO = pasarADTO(titular);
            return buscarTitularDTO(titularDTO).get(0);
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public TitularDTO pasarADTO(Titular titular){
        TitularDTO titularDTO = new TitularDTO();
        
        titularDTO.setNombre(titular.getNombre());
        titularDTO.setApellido(titular.getApellido());
        titularDTO.setNroDoc(titular.getNumeroDocumento());
        titularDTO.setTipoDoc(titular.getTipoDocumento());
        titularDTO.setFechaNacimiento(titular.getFechaNacimiento());
        
        return titularDTO;
    }
    
    
    public List<TitularDTO> pasarListaADTO(List<Titular> titulares){
        
        List<TitularDTO> titularesDTO = new ArrayList();
        
        for(Titular titular: titulares){
            titularesDTO.add(pasarADTO(titular));
        }
        return titularesDTO;
    }
    
    public List<TitularDTO> buscarTitularDTO(TitularDTO titularDTO) {
        
        try{
            return  pasarListaADTO(buscarTitular(titularDTO));
        } catch(Exception e){
            return null;
        }
        
    }
    
    public List<Titular> buscarTitular(TitularDTO titularDTO) {
        
        try{
            return  titularImpl.buscar(titularDTO);
        } catch(Exception e){
            return null;
        }
    }
    
    public TipoDocumento buscarTipoDoc(String especificacion){
        try{
            return titularImpl.buscarTipoDoc(especificacion).get(0);
        } catch(Exception e){
            throw new MyValidationException("Error buscarTipoDoc", e);
        }
    }
    
    public List<TipoDocumento> mostrarTipos(){
        return titularImpl.buscarAllTipoDoc();
    }
    
    public Contribuyente buscarContribuyente(String numeroDoc, String tipoDoc){
        try {
            return titularImpl.buscarContribuynete(numeroDoc, tipoDoc).get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
