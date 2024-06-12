//import Mappers.CargadorDatos;
import views.Inicio;
import javax.persistence.*;
import javax.swing.JFrame;
//import messages.Util;
//import views.*;

public class App {
    public static void main(String[] args) {
        JFrame pantalla;
        try {
            // Inicio del Programa
  //          CargadorDatos cargadorDatos = new CargadorDatos();
            EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("PersistenciaTP");
            EntityManager manager = managerFactory.createEntityManager();
            
            //cargadorDatos.cargarDatos(manager);
            
           pantalla = new Inicio(manager);
           pantalla.setVisible(true);
           pantalla.setLocationRelativeTo(null);
        } catch (Exception e) {
            // Aviso de Problema
      //      Util.mensajeError("Error: Inicio", "Ha ocurrido un error: \n" + e.getMessage());
            System.out.println("Error: \n" + e);
        } finally {
            // Fin del Programa
            //manager.close();
            //managerFactory.close();
        }
        
    }
}