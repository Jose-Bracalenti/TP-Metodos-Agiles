/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import dto.UsuarioDTO;
import models.entities.Usuario;
import dto.UsuarioDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author estan
 */
public class GestorUsuario {
    
    private static GestorUsuario _INSTANCE;
    private UsuarioDAO usuarioDAO;
    Usuario usuario;
    
    
    public UsuarioDTO buscarUsuario(String nroDocumento, String contrasenia, String rol){
        try {
            //Buscamos el usuario
            Usuario usuario = usuarioDAO.buscarUsuario(nroDocumento, contrasenia, rol).get(0);
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
