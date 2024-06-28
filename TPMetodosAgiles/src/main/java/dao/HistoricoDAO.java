/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.LicenciaDTO;
import java.util.List;
import models.entities.HistoricoLicencia;

/**
 *
 * @author Juani
 */
public interface HistoricoDAO {
    List<HistoricoLicencia> buscarByLicenciaDTO(LicenciaDTO licenciaDTO);
}
