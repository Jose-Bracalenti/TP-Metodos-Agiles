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
import models.entities.HistoricoLicencia;
import models.entities.Titular;

/**
 *
 * @author Juani
 */
public class HistoricoDAOImpl implements HistoricoDAO{
    private final EntityManager entityManager = UtilHibernate.getInstance().getEntityManager();
    
    @Override
    public List<HistoricoLicencia> buscarByTitularDTO (TitularDTO titularDTO){
        try{
            String consulta = "SELECT h FROM HistoricoLicencia h WHERE h.titular.id = :idTitular";
            TypedQuery<HistoricoLicencia> query = (TypedQuery<HistoricoLicencia>) entityManager.createQuery(consulta);
            query.setParameter("idTitular", titularDTO.getId());
            return query.getResultList();
        }catch(Exception e){
            throw new MyValidationException("Error: Buscar Historico Licencia", e);
        }
    }
    
    @Override
    public List<HistoricoLicencia> buscarByTitularDTOyClase(Titular titular, String clase) {
        try{
            String consulta = "SELECT h FROM HistoricoLicencia h WHERE h.clase = :clase AND h.titular.id = :idTitular";
            TypedQuery<HistoricoLicencia> query = (TypedQuery<HistoricoLicencia>) entityManager.createQuery(consulta);
            query.setParameter("idTitular", titular.getId());
            query.setParameter("clase", clase);
            return query.getResultList();
        }catch(Exception e){
            throw new MyValidationException("Error: Buscar Historico Licencia", e);
        }
    }
}
