/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mappers;

import javax.persistence.*;

/**
 *
 * @author estan
 */
public class UtilHibernate {
    
    private static UtilHibernate instancia;
    private EntityManager manager;
    
    
    private UtilHibernate(){
        try {
            EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("PersistenciaTP");
            manager = managerFactory.createEntityManager();
        } catch (Exception e) {
            System.out.println("Error: \n" + e);
        }   
    }
    
    public static UtilHibernate getInstance(){
        
        if (instancia == null) {
            instancia = new UtilHibernate();
        }
        return instancia;
    }
    
    public EntityManager getEntityManager() {
        return manager;
    }
}
