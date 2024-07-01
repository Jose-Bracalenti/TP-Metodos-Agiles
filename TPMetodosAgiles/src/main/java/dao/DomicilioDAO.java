/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.entities.Domicilio;

/**
 *
 * @author estan
 */
public interface DomicilioDAO {
    
    Domicilio altaDomicilio(Domicilio domicilio);
    Domicilio buscarDomicilio(Domicilio domicilio);
}
