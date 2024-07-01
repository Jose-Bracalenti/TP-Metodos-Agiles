/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Mappers.UtilHibernate;
import dao.DomicilioDAOImpl;
import javax.persistence.EntityManager;
import models.entities.Domicilio;

/**
 *
 * @author estan
 */
public class GestorDomicilio {
    
    EntityManager manager = UtilHibernate.getInstance().getEntityManager();
    DomicilioDAOImpl domicilioImpl = new DomicilioDAOImpl();
    
    
    public Domicilio altaDomicilio(Domicilio domicilio){
        try {
            return domicilioImpl.altaDomicilio(domicilio);
        } catch (Exception e) {
            return null;
        }
        
    }
}
