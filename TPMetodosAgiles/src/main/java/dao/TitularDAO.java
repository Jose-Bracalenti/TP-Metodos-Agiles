/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.TitularDTO;
import java.util.List;
import models.entities.Titular;

/**
 *
 * @author Juani
 */
public interface TitularDAO {
    List<Titular> buscar(TitularDTO titularDTO);
    
}
