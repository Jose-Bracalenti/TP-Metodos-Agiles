/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Mappers.MyValidationException;
import Mappers.UtilHibernate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.entities.ClaseLicencia;

/**
 *
 * @author Juani
 */
public class ClaseLicenciaDAOImpl implements ClaseLicenciaDAO{
    
    private final EntityManager entityManager = UtilHibernate.getInstance().getEntityManager();

    
    
    @Override
    public List<ClaseLicencia> buscarAll(){
        try{
            String consulta = "SELECT c FROM ClaseLicencia c";
            TypedQuery<ClaseLicencia> query = (TypedQuery<ClaseLicencia>) entityManager.createQuery(consulta);
            return query.getResultList();
        } catch(Exception e){
            throw new MyValidationException("Error: Buscar Clase Licencia", e);
        }
    }
    
    @Override
    public List<ClaseLicencia> buscarByEspecificacion(String especificacion) {
        try{
            String consulta = "SELECT c FROM ClaseLicencia c WHERE c.especificacion = :especificacion";
            TypedQuery<ClaseLicencia> query = (TypedQuery<ClaseLicencia>) entityManager.createQuery(consulta);
            query.setParameter("especificacion", especificacion);
            return query.getResultList();
        } catch(Exception e){
            throw new MyValidationException("Error: Buscar Clase Licencia", e);
        }
    }
}
