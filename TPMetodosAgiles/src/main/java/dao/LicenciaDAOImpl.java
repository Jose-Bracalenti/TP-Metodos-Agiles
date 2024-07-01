/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Mappers.MyValidationException;
import Mappers.UtilHibernate;
import dto.LicenciaDTO;
import dto.TitularDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.entities.Licencia;

/**
 *
 * @author Juani
 */
public class LicenciaDAOImpl implements LicenciaDAO{
    private final EntityManager entityManager = UtilHibernate.getInstance().getEntityManager();

    
    @Override
    public void altaLicencia(Licencia licencia){
        entityManager.getTransaction().begin();
        entityManager.persist(licencia);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Licencia> buscarByTitularDTO(TitularDTO titularDTO) {
        try{
            String consulta = "SELECT l FROM Licencia l WHERE l.titular.id = :titular";
            TypedQuery<Licencia> query = (TypedQuery<Licencia>) entityManager.createQuery(consulta);
            query.setParameter("titular", titularDTO.getId());
            return query.getResultList();
            
        } catch (Exception e){
            throw new MyValidationException("Error: Buscar Licencia", e);
        }
    }
    /*@Override
    public List<Licencia> buscarByTitularDTOyClase(TitularDTO titularDTO, Integer id_clase) {
        try{
            String consulta = "SELECT l FROM Licencia l WHERE l.titular.id = :titular AND l.claselicencia.id = :clase ";
            TypedQuery<Licencia> query = (TypedQuery<Licencia>) entityManager.createQuery(consulta);
            query.setParameter("titular", titularDTO.getId());
            query.setParameter("clase", id_clase);
            return query.getResultList();
            
        } catch (Exception e){
            throw new MyValidationException("Error: Buscar Licencia", e);
        }
    }*/
    
}
