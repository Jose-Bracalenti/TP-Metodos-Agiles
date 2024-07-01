/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.LicenciaDTO;
import dto.TitularDTO;
import java.util.List;
import models.entities.Licencia;

/**
 *
 * @author Juani
 */
public interface LicenciaDAO {
    void altaLicencia(Licencia licencia);
    List<Licencia> buscarByTitularDTO(TitularDTO titularDTO);
    void renovarLicencia(LicenciaDTO licenciaDTO);
}
