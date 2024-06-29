/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Mappers.MyValidationException;
import Mappers.UtilHibernate;
import dto.TitularDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import models.entities.ClaseLicencia;
import models.entities.TipoDocumento;
import models.entities.Titular;

/**
 *
 * @author Juani
 */
public class TitularDAOImpl implements TitularDAO{
    
    private final EntityManager entityManager = UtilHibernate.getInstance().getEntityManager();
    
    @Override
    public List<Titular> buscar(TitularDTO titularDTO) {
        
        try{
                String consulta = "SELECT t FROM Titular t WHERE 1=1"; //ver si se concatena con otra tabla
        
                if(titularDTO.getNombre() != null){ //revisar que sea vacia o nula
                    consulta = (consulta + " AND t.nombre LIKE :nombre");
                }
                if(titularDTO.getApellido() != null){
                    consulta = (consulta + " AND t.apellido LIKE :apellido");
                }
                if(titularDTO.getTipoDoc() != null){
                    consulta = (consulta + " AND t.tipoDocumento = :tipoDocumento");
                }
                if(titularDTO.getNroDoc() != null){
                    consulta = (consulta + " AND t.numerodocumento = :numerodocumento");
                }
                
                
                TypedQuery<Titular> query = (TypedQuery<Titular>) entityManager.createQuery(consulta);

                if(titularDTO.getNombre() != null){
                    query.setParameter("nombre", titularDTO.getNombre()+"%");
                }
                if(titularDTO.getApellido() != null){
                    query.setParameter("apellido", titularDTO.getApellido()+"%");
                }
                if(titularDTO.getTipoDoc() != null){
                    query.setParameter("tipoDocumento", titularDTO.getTipoDoc());
                }
                if(titularDTO.getNroDoc() != null){
                     query.setParameter("numerodocumento", titularDTO.getNroDoc());
                }
                return query.getResultList();
            } catch (Exception e){
                return null;
            }
        
    }
    
    @Override
    public List<TipoDocumento> buscarTipoDoc(String especificacion){
        
        try{
                String consulta = "SELECT t FROM TipoDocumento t WHERE t.especificacion = :especificacion"; //ver si se concatena con otra tabla
                consulta = (consulta + " AND t.especificacion = :especificacion");
                TypedQuery<TipoDocumento> query = (TypedQuery<TipoDocumento>) entityManager.createQuery(consulta);
                query.setParameter("especificacion", especificacion);
                return query.getResultList();
                
        } catch (Exception e){
                throw new MyValidationException("Error: BuscarTipoDoc DAO", e);
        }
                
    }
    
    public List<TipoDocumento> buscarAllTipoDoc(){
        
        try {
            String consulta = "SELECT t FROM TipoDocumento t";
            TypedQuery<TipoDocumento> query = (TypedQuery<TipoDocumento>) entityManager.createQuery(consulta);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
