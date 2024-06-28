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
            String consulta = "SELECT l FROM Licencia l JOIN Titular t ON l.id_titular = t.id WHERE t.numerodocumento = :numerodocumento";
            TypedQuery<Licencia> query = (TypedQuery<Licencia>) entityManager.createQuery(consulta);
            query.setParameter("numerodocumento", titularDTO.getNroDoc());
            return query.getResultList();
            
        } catch (Exception e){
            throw new MyValidationException("Error: Buscar Licencia", e);
        }
    }
    
    @Override
    public List<LicenciaDTO> buscarDTOByTitularDTO(TitularDTO titularDTO) {
        try{
            String consulta = "SELECT l FROM Licencia l JOIN Titular t ON l.id_titular = t.id WHERE t.numerodocumento = :numerodocumento";
            TypedQuery<Licencia> query = (TypedQuery<Licencia>) entityManager.createQuery(consulta);
            query.setParameter("numerodocumento", titularDTO.getNroDoc());
            //return query.getResultList();
            
        } catch (Exception e){
            throw new MyValidationException("Error: Buscar Licencia", e);
        }
    }
}
