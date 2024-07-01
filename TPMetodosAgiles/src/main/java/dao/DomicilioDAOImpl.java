/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Mappers.UtilHibernate;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.entities.Domicilio;
import models.entities.Titular;

/**
 *
 * @author estan
 */
public class DomicilioDAOImpl implements DomicilioDAO{
    
    private final EntityManager entityManager = UtilHibernate.getInstance().getEntityManager();
    
    public Domicilio altaDomicilio(Domicilio domicilio){
        
        entityManager.getTransaction().begin();
        entityManager.persist(domicilio);
        entityManager.getTransaction().commit();
       
        return buscarDomicilio(domicilio);
    }
    
    public Domicilio buscarDomicilio(Domicilio domicilio){
        
        try{
                String consulta = "SELECT d FROM Domicilio d WHERE 1=1"; //ver si se concatena con otra tabla
        
                if(domicilio.getCalle() != null){ //revisar que sea vacia o nula
                    consulta = (consulta + " AND d.calle = :calle");
                }
                if(domicilio.getNumero() != null){
                    consulta = (consulta + " AND d.numero = :numero");
                }
                if(domicilio.getPiso() != null){
                    consulta = (consulta + " AND d.piso = :piso");
                }
                if(domicilio.getDepartamento() != null){
                    consulta = (consulta + " AND d.departamento = :departamento");
                }
                
                
                TypedQuery<Domicilio> query = (TypedQuery<Domicilio>) entityManager.createQuery(consulta);

                if(domicilio.getCalle() != null){
                    query.setParameter("calle", domicilio.getCalle());
                }
                if(domicilio.getNumero() != null){
                    query.setParameter("numero", domicilio.getNumero());
                }
                if(domicilio.getPiso() != null){
                    query.setParameter("piso", domicilio.getPiso());
                }
                if(domicilio.getDepartamento() != null){
                     query.setParameter("departamneto", domicilio.getDepartamento());
                }
                return query.getResultList().get(0);
            } catch (Exception e){
                return null;
            }
    } 
}
