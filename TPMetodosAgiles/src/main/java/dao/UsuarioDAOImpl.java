package dao;

import Mappers.MyValidationException;
import Mappers.UtilHibernate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import messages.Util;
import models.entities.RolEnum;
import models.entities.Titular;
import models.entities.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

    private final EntityManager entityManager = UtilHibernate.getInstance().getEntityManager();
    
    
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
            String consulta = "SELECT u FROM Usuario u";
            TypedQuery<Usuario> query = (TypedQuery<Usuario>) entityManager.createQuery(consulta);
            JOptionPane.showMessageDialog(null,query.getResultList()); 
            return query.getResultList();
        } catch (Exception e){
            throw new MyValidationException("Error: Buscar Empleado", e);
        }
        
    }

    @Override
    public List<Usuario> buscarUsuario(String nroDocumento, String contrasenia, String rol) {
        try{
            String consulta = "SELECT u FROM Usuario u WHERE u.numeroDocumento = :numeroDocumento AND u.contrasenia = :contrasenia AND u.rol = :rol";
            TypedQuery<Usuario> query = (TypedQuery<Usuario>) entityManager.createQuery(consulta);
            query.setParameter("numeroDocumento", nroDocumento);
            query.setParameter("contrasenia", contrasenia);
            query.setParameter("rol", RolEnum.valueOf(rol));
            return query.getResultList();
        } catch (Exception e){
            throw new MyValidationException("Error: Buscar Usuario", e);
        }
    }
}