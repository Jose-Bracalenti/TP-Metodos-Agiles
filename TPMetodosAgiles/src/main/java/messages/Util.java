/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author GervaMatteoda
 */
public class Util {
    
    // Mensaje de Informacion -> Casos Exitosos
    public static void mensajeInformacion (String titulo, String mensaje) {
        JOptionPane.showMessageDialog(new JFrame(), mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Mensaje de Advertencia -> Formatos Invalidos o Campos Vacios
    public static void mensajeAdvertencia (String titulo, String mensaje) {
        JOptionPane.showMessageDialog(new JFrame(), mensaje, titulo, JOptionPane.WARNING_MESSAGE);
    }
    
    // Mensaje de Error -> Datos Incorrectos
    public static void mensajeError (String titulo, String mensaje) {
        JOptionPane.showMessageDialog(new JFrame(), mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    // Mensaje de Confirmación -> Yes == 0
    public static boolean mensajeConfirmacion (String titulo, String mensaje) {
        return JOptionPane.showConfirmDialog(new JFrame(), mensaje, titulo, JOptionPane.YES_NO_OPTION) == 0;
    }
    
}
