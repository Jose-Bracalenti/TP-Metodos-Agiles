//import Mappers.CargadorDatos;
import Views.Inicio;
import dto.TitularDTO;
import javax.swing.JFrame;
import views.AltaTitular;
import views.EmitirLicencia;
//import messages.Util;
//import views.*;

public class App {
    public static void main(String[] args) {
        JFrame pantalla;
        try {
            // Inicio del Programa
            //CargadorDatos cargadorDatos = new CargadorDatos();
            
            //cargadorDatos.cargarDatos(manager);
           TitularDTO titularDTO = new TitularDTO();
           titularDTO.setNombre("Cristiano");
           titularDTO.setApellido("Ronaldo");
           titularDTO.setNroDoc("12345678");
           pantalla = new EmitirLicencia(titularDTO);
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