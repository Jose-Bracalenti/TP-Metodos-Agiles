package dao;

import Mappers.MyValidationException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import messages.Util;
import models.entities.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

    private final EntityManager entityManager;
    
    public UsuarioDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void alta(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void baja(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Buscar Usuario para Iniciar Sesion -> Lista Vacía (No existe usuario)
    
    @Override
    public List<Usuario> buscarAll (){
        try{
            String consulta = "SELECT u FROM Umpleado u";
            TypedQuery<Usuario> query = (TypedQuery<Usuario>) entityManager.createQuery(consulta);
            return query.getResultList();
        } catch (Exception e){
            throw new MyValidationException("Error: Buscar Empleado", e);
        }
        
    }

    @Override
    public List<Usuario> buscarUsuario(String nroDocumento, String contrasenia, String rol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}