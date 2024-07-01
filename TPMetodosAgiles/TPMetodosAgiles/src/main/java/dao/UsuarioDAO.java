package dao;

import java.util.List;
import models.entities.Usuario;

public interface UsuarioDAO {
    
    void alta(Usuario usuario);
    List<Usuario> buscarAll();
    List<Usuario> buscarUsuario (String nroDocumento, String contrasenia, String rol);
    void modificar(Usuario usuario);
    void baja(int id);

}