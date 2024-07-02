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
import models.entities.Domicilio;
import models.entities.TipoDocumento;
import models.entities.Titular;

/**
 *
 * @author Juani
 */
public class GestorTitular {
    EntityManager manager = UtilHibernate.getInstance().getEntityManager();
    TitularDAOImpl titularImpl = new TitularDAOImpl();
    
    
    public TitularDTO pasarADTO(Titular titular){
        TitularDTO titularDTO = new TitularDTO();
        
        titularDTO.setId(titular.getId());
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

    public void eliminarTitular(TitularDTO titularDTO) {
        try{
            titularImpl.eliminarTitular(titularDTO);
        }catch(Exception e){
            throw new MyValidationException("Error al eliminar el titular",e);
        }
    }

    public List<Titular> buscarTitularByDireccion(Domicilio domicilio) {
        try{
            return titularImpl.buscarTitularByDireccion(domicilio);
        } catch(Exception e){
            throw new MyValidationException("Error buscar titular por direccion", e);
        }
    }

    public void eliminarDomicilio(Domicilio domicilio) {
         try{
            titularImpl.eliminarDomicilio(domicilio);
        }catch(Exception e){
            throw new MyValidationException("Error al eliminar el titular",e);
        }
    }
}
