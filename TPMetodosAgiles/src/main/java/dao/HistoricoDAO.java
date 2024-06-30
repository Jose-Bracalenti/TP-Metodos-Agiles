/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.TitularDTO;
import java.util.List;
import models.entities.HistoricoLicencia;

/**
 *
 * @author Juani
 */
public interface HistoricoDAO {
    List<HistoricoLicencia> buscarByTitularDTO (TitularDTO titularDTO);
    List<HistoricoLicencia> buscarByTitularDTOyClase(TitularDTO titularDTO, String clase);
}