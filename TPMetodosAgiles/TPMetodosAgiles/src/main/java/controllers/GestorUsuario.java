/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;



import dao.UsuarioDAOImpl;
import models.entities.Usuario;
import dto.UsuarioDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author estan
 */
public class GestorUsuario {
    
    private final UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
    
    
    public UsuarioDTO buscarUsuario(String nroDocumento, String contrasenia, String rol){
        try {
            //Buscamos el usuario
            Usuario usuario = usuarioDAOImpl.buscarUsuario(nroDocumento, contrasenia, rol).get(0);
            if(usuario != null){
                //Pasamos de usuario a usuarioDTO
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setNroDocumento(usuario.getNumeroDocumento());
                usuarioDTO.setContrasenia(usuario.getContrasenia());
                usuarioDTO.setRol(usuario.getRol().toString());
                return usuarioDTO;
            }  
        } catch (Exception e) {
        }
        
        return null;
    }
    
}
