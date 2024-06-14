package views;

import controllers.GestorLicencia;
import controllers.GestorTitular;
import dto.LicenciaDTO;
import dto.TitularDTO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import messages.Util;
import models.entities.ClaseLicencia;
import models.entities.TipoDocumento;
import models.entities.Titular;
import org.eclipse.persistence.exceptions.ValidationException;

/**
 *
 * @author Juani
 */
public class EmitirLicencia extends javax.swing.JFrame {

    private EntityManager entityManager;
    
    GestorLicencia gestorLicencia;
    GestorTitular gestorTitular;
    TitularDTO titularDTO;
    
    
    public EmitirLicencia() {
        initComponents();
    }
    public EmitirLicencia (TitularDTO titularDTO){
        initComponents();
        txtApellidoNombre.setEditable(false);
        txtNroDocumento.setEditable(false);
        txtNroCopia.setEditable(false);
        txtInicioVigencia.setEditable(false);
        txtFinVigencia.setEditable(false);
        txtNroCopia.setText("0");
        gestorLicencia = new GestorLicencia();
        gestorTitular = new GestorTitular();
        this.titularDTO = titularDTO;
        this.mostrarClase();
        this.mostrarTitular(titularDTO);
        this.validarEdad(titularDTO);
        this.mostrarFechasVigencia(titularDTO);
    }
    
    public void mostrarTitular(TitularDTO titularDTO){
        this.titularDTO = titularDTO;
        try{
            Titular titular = this.gestorTitular.buscarTitular(titularDTO).get(0);
            TipoDocumento documento = titular.getTipoDocumento();
            txtNroDocumento.setText(documento.getEspecificacion() + " / " + titular.getNumeroDocumento());
            txtApellidoNombre.setText(titular.getApellido() + ", " + titular.getNombre());
        } catch (ValidationException ve) {
            Util.mensajeAdvertencia("Advertencia: Mostrar Titular", ve.getMessage());
        } catch (Exception e) {
            Util.mensajeError("Error: Mostrar Titular", "Hubo un error: \n" + e.getMessage());
        }
    }
    
    public void mostrarFechasVigencia(TitularDTO titularDTO){
        this.titularDTO = titularDTO;
        try{
            /*txtInicioVigencia.setText(gestorLicencia.mostrarInicioVigencia(titularDTO).get(0).toString());
            txtFinVigencia.setText(gestorLicencia.mostrarFinVigencia(titularDTO).get(0).toString());*/
            
            //Le hardcodeo la fecha para ver chequear que ande todo, lo de arriba sería en realidad lo que habría que hacer (puse
            // los metodos de calcular la vigencia en el gestor pero no se donde irian).
            txtInicioVigencia.setText("15/06/2024");
            txtFinVigencia.setText("15/06/2025");       
            
        } catch (ValidationException ve) {
            Util.mensajeAdvertencia("Advertencia: Mostrar Titular", ve.getMessage());
        } catch (Exception e) {
            Util.mensajeError("Error: Mostrar Titular", "Hubo un error: \n" + e.getMessage());
        }
    }
    
    public void mostrarClase(){
    try{
        boxClase.addItem("Seleccionar");
        List<ClaseLicencia> clases = gestorLicencia.mostrarClases();
        for (int i=0; i<clases.size(); i++){
                    boxClase.addItem(clases.get(i).getEspecificacion());
        }
        
        
    } catch(ValidationException ve){
        Util.mensajeAdvertencia("Advertencia: Mostrar Clases", ve.getMessage());
    } catch(Exception e){
        Util.mensajeError("Error: Mostrar Clases", "Hubo un error: \n" + e.getMessage());
    }
}
    
    public boolean validarEdad(TitularDTO titularDTO) {
        boolean validez;
            
        Titular titular = gestorTitular.buscarTitular(titularDTO).get(0);
        Date currentDate = new Date();
       
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(titular.getFechaNacimiento());
            
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(currentDate);
            
        int edad=calcularEdad(calendar1,calendar2);
        String clase = (String) boxClase.getSelectedItem();
            
        if (clase.equals("C") || clase.equals("D") || clase.equals("E")){
            validez=(edad>=21);
        }
        else{
            validez=(edad>=17);
        }
            
        return validez;
    }
    
    public Integer calcularEdad(Calendar calendar1, Calendar calendar2){
        
        int yearTitular=calendar1.get(Calendar.YEAR);
        int monthTitular=calendar1.get(Calendar.MONTH)+1;
        int dayTitular=calendar1.get(Calendar.DAY_OF_MONTH);
        
        int yearActual=calendar2.get(Calendar.YEAR);
        int monthActual=calendar2.get(Calendar.MONTH)+1;
        int dayActual=calendar2.get(Calendar.DAY_OF_MONTH);
        
        int edad = yearActual - yearTitular;
        if (monthActual<monthTitular || (monthActual==monthTitular && dayActual<dayTitular)){
            edad--;
        }
        
        return edad;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EmitirLicencia1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel_Documento3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtApellidoNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNroDocumento = new javax.swing.JTextField();
        jLabel_Documento1 = new javax.swing.JLabel();
        jLabel_Documento2 = new javax.swing.JLabel();
        boxClase = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtNroCopia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtInicioVigencia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFinVigencia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        EmitirLicencia1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        EmitirLicencia1.setText("Emitir Licencia");
        EmitirLicencia1.setToolTipText("");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel_Documento3.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel_Documento3.setText("Datos del titular");
        jLabel_Documento3.setMaximumSize(new java.awt.Dimension(141, 17));
        jLabel_Documento3.setMinimumSize(new java.awt.Dimension(141, 17));
        jLabel_Documento3.setPreferredSize(new java.awt.Dimension(141, 17));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Apellido, Nombre");

        txtApellidoNombre.setEditable(false);
        txtApellidoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoNombreActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nro. de Documento");

        txtNroDocumento.setEditable(false);
        txtNroDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroDocumentoActionPerformed(evt);
            }
        });

        jLabel_Documento1.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel_Documento1.setText("Datos de la licencia");
        jLabel_Documento1.setMaximumSize(new java.awt.Dimension(141, 17));
        jLabel_Documento1.setMinimumSize(new java.awt.Dimension(141, 17));
        jLabel_Documento1.setPreferredSize(new java.awt.Dimension(141, 17));

        jLabel_Documento2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento2.setText("Clase");

        boxClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxClaseActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Nro. de copia");

        txtNroCopia.setEditable(false);
        txtNroCopia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroCopiaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Inicio de Vigencia");

        txtInicioVigencia.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Fin de Vigencia");

        txtFinVigencia.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Observaciones");

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Documento1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Documento3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInicioVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(12, 12, 12)
                        .addComponent(btnCancelar)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(EmitirLicencia1)
                        .addGap(223, 223, 223))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel_Documento2)
                        .addGap(18, 18, 18)
                        .addComponent(boxClase, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtApellidoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNroCopia)
                            .addComponent(txtFinVigencia))
                        .addGap(30, 30, 30))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addGap(23, 23, 23)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(EmitirLicencia1)
                .addGap(42, 42, 42)
                .addComponent(jLabel_Documento3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jLabel_Documento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Documento2)
                    .addComponent(boxClase, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNroCopia, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtInicioVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtFinVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(398, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtApellidoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoNombreActionPerformed

    private void txtNroDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroDocumentoActionPerformed

    private void boxClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxClaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxClaseActionPerformed

    private void txtNroCopiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroCopiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroCopiaActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{
            Titular titular = this.gestorTitular.buscarTitular(titularDTO).get(0);
            String documento = titular.getNumeroDocumento();
            String clase = (String) boxClase.getSelectedItem();
            if (clase.equals("Seleccionar")){
                Util.mensajeAdvertencia("Advertencia", "No se permiten campo/s vacío/s");
            }
       
            else{
                if(validarEdad(this.titularDTO)){
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateInicio = formatter.parse(txtInicioVigencia.getText());
                    Date dateFin = formatter.parse(txtFinVigencia.getText());
               
                    LicenciaDTO licenciaDTO = new LicenciaDTO();
                    licenciaDTO.setTitular(gestorTitular.buscarTitular(titularDTO).get(0));
                    licenciaDTO.setClase(gestorLicencia.mostrarClases(clase).get(0));
                    licenciaDTO.setFechaInicioVigencia(dateInicio);
                    licenciaDTO.setFechaFinVigencia(dateFin);
                    licenciaDTO.setNumeroCopia(Integer.parseInt(txtNroCopia.getText()));
                    licenciaDTO.setNumeroLicencia(documento);
                    licenciaDTO.setObservacion(txtObservaciones.getText());
                    
                    if (gestorLicencia.altaLicencia(licenciaDTO)) {
                        Mappers.Util.mensajeInformacion("Exito", "Se ha emitido la licencia con éxtio");
                    } else {
                        Mappers.Util.mensajeInformacion("Error", "No se ha emitido la licencia con éxtio");
                    }
               
                //JFrame pantalla = new AltaTitular2(licenciaDTO, entityManager);
                this.setVisible(false);
                //pantalla.setVisible(true);
                //pantalla.setLocationRelativeTo(null);
                this.dispose();
                }
                else{
                    Mappers.Util.mensajeInformacion("Error", "El titular no cumple con la edad mínima requerida para licencias de esta clase");
                }
            }
        } catch (ValidationException ve) {
            Util.mensajeAdvertencia("Advertencia: Validar Datos", ve.getMessage());
        } catch (Exception e) {
            Util.mensajeError("Error: Validar Datos", "Hubo un error: \n" + e.getMessage());
        }
        
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {
            if ( Mappers.Util.mensajeConfirmacion("Confirmación", "¿Está seguro que desea salir?") ){
                this.setVisible(false);
                this.dispose();
            }
        } catch (ValidationException ve) {
            Mappers.Util.mensajeAdvertencia("Advertencia: Cancelar", ve.getMessage());
        } catch (Exception e) {
            Mappers.Util.mensajeError("Error: Cancelar", "Hubo un error: \n" + e.getMessage());
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmitirLicencia1;
    private javax.swing.JComboBox<String> boxClase;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_Documento1;
    private javax.swing.JLabel jLabel_Documento2;
    private javax.swing.JLabel jLabel_Documento3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtApellidoNombre;
    private javax.swing.JTextField txtFinVigencia;
    private javax.swing.JTextField txtInicioVigencia;
    private javax.swing.JTextField txtNroCopia;
    private javax.swing.JTextField txtNroDocumento;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables

}
