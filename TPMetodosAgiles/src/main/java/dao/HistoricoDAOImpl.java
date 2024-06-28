/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Mappers.MyValidationException;
import Mappers.UtilHibernate;
import dto.LicenciaDTO;
import java.util.List;
import javax.persistence.EntityManager;
import models.entities.HistoricoLicencia;

/**
 *
 * @author Juani
 */
public class HistoricoDAOImpl implements HistoricoDAO{
    private final EntityManager entityManager = UtilHibernate.getInstance().getEntityManager();
    
    @Override
    public List<HistoricoLicencia> buscarByLicenciaDTO (LicenciaDTO licenciaDTO){
        try{
            String query = 'SELECT h FROM HistoricoLicencia h WHERE'
        }catch(Exception e){
            throw new MyValidationException("Error: Buscar Historico Licencia", e);
        }
    }
}
