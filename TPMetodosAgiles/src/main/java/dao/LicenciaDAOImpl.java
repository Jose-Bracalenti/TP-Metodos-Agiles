/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Mappers.UtilHibernate;
import dto.LicenciaDTO;
import javax.persistence.EntityManager;
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
}
