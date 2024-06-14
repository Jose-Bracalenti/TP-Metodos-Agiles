/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.entities.ClaseLicencia;

/**
 *
 * @author Juani
 */
public interface ClaseLicenciaDAO {
    List<ClaseLicencia> buscarAll();
    List<ClaseLicencia> buscarByEspecificacion(String especificacion);
}
