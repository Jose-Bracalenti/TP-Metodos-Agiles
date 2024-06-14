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
import java.util.List;
import javax.persistence.EntityManager;
import models.entities.Titular;

/**
 *
 * @author Juani
 */
public class GestorTitular {
    EntityManager manager = UtilHibernate.getInstance().getEntityManager();
    TitularDAOImpl titularImpl = new TitularDAOImpl();
    
    public List<Titular> buscarTitular(TitularDTO titularDTO) {
        try{
            return titularImpl.buscar(titularDTO);
        } catch(Exception e){
            throw new MyValidationException("Error: Mostrar Titular", e);
        }
    }
    
}
