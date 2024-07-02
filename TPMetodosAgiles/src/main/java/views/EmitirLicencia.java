package views;

import controllers.GestorLicencia;
import controllers.GestorTitular;
import dto.LicenciaDTO;
import dto.TitularDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import messages.Util;
import models.entities.ClaseLicencia;
import models.entities.HistoricoLicencia;
import models.entities.Licencia;
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
    public EmitirLicencia (TitularDTO titular){
        initComponents();
        txtApellidoNombre.setEditable(false);
        txtNroDocumento.setEditable(false);
        txtNroCopia.setEditable(false);
        txtInicioVigencia.setEditable(false);
        txtFinVigencia.setEditable(false);
        txtNroCopia.setText("0");
        gestorLicencia = new GestorLicencia();
        gestorTitular = new GestorTitular();
        this.titularDTO = titular;
        this.mostrarClase();
        this.mostrarTitular();
        this.validarEdad();
        this.mostrarFechasVigencia();
    }
    
    public void mostrarTitular(){
        try{
            titularDTO = this.gestorTitular.buscarTitularDTO(titularDTO).get(0);
            txtNroDocumento.setText(titularDTO.getTipoDoc().getEspecificacion() + " / " + titularDTO.getNroDoc());
            txtApellidoNombre.setText(titularDTO.getApellido() + ", " + titularDTO.getNombre());
        } catch (ValidationException ve) {
            Util.mensajeAdvertencia("Advertencia: Mostrar Titular", ve.getMessage());
        } catch (Exception e) {
            Util.mensajeError("Error: Mostrar Titular", "Hubo un error: \n" + e.getMessage());
        }
    }
    
    public void mostrarFechasVigencia(){
        try{
            txtInicioVigencia.setText(calcularInicioVigencia());
            txtFinVigencia.setText(calcularFinVigencia());     
            
        } catch (ValidationException ve) {
            Util.mensajeAdvertencia("Advertencia: Mostrar Fecha", ve.getMessage());
        } catch (Exception e) {
            Util.mensajeError("Error: Mostrar Fecha", "Hubo un error: \n" + e.getMessage());
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
    
    public boolean validarEdad() {
        boolean validez;
            
        titularDTO = gestorTitular.buscarTitularDTO(titularDTO).get(0);
        
        
        int edad=calcularEdad();
        String clase = (String) boxClase.getSelectedItem();
            
        if (clase.equals("C") || clase.equals("D") || clase.equals("E")){
            validez=(edad>=21);
        }
        else{
            validez=(edad>=17);
        }
            
        return validez;
    }
    
    public boolean validarClase(String clase){
        
        try{
            boolean restriccionVigencia;
            boolean restriccionHistorico;
            if (clase.equals('A') || clase.equals('B') || clase.equals('F') || clase.equals('G')){
                restriccionVigencia = true;
                restriccionHistorico=true;
            }
            else{
                titularDTO = gestorTitular.buscarTitularDTO(titularDTO).get(0);
                List<LicenciaDTO> licencias = gestorLicencia.buscarLicenciaByTitularDTO(titularDTO);
                Titular titular = gestorTitular.buscarTitular(titularDTO).get(0);
                List<HistoricoLicencia> historicoClaseB = gestorLicencia.buscarHistoricoByTitularDTOyClase(titular, "B");
                List<LicenciaDTO> licenciasClaseB = new ArrayList();
                for (LicenciaDTO licenciaDTO: licencias){
                   if (licenciaDTO.getClase().getId()==2){
                       licenciasClaseB.add(licenciaDTO);
                   }
                }
              
                //ACA SE CHEQUEA QUE LA PERSONA HAYA SACADO UNA LICENCIA DE CLASE B HACE 1 AÑO O MAS
                if (!licenciasClaseB.isEmpty()){
                    if(calcularAnios(licenciasClaseB.get(0))>=1){
                        restriccionVigencia = true;
                    }
                    else{
                        restriccionVigencia = false;
                    }
                }
                else {
                    if (!historicoClaseB.isEmpty()){
                        restriccionVigencia=true;
                    }
                    else{
                        restriccionVigencia=false;
                    }
                }
                
                //ACA SE CHEQUEA QUE LA PERSONA, EN CASO DE TENER MAS DE 65 AÑOS YA HAYA SACADO UNA LICENCIA CLASE C, D o E    
                if (calcularEdad()>65){
                   if (gestorLicencia.buscarHistoricoByTitularDTOyClase(titular, "C").isEmpty() && gestorLicencia.buscarHistoricoByTitularDTOyClase(titular, "D").isEmpty() && gestorLicencia.buscarHistoricoByTitularDTOyClase(titular, "E").isEmpty()){ //TENGO QUE TERMINAR LA FUNCION ESA DE BUSCAR HISTORICO
                       restriccionHistorico=false;
                   }
                   else{
                       restriccionHistorico=true;
                    }
                }
                else{
                    
                     restriccionHistorico=true;
                }
            }
            return restriccionVigencia && restriccionHistorico;
            
        } catch (Exception e){
            Util.mensajeError("Error: Validar Clase", "Hubo un error: \n" + e.getMessage());
        }
        return false;
    }
    
    public Integer calcularEdad(){
        
        Date currentDate = new Date();
       
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(titularDTO.getFechaNacimiento());
        
        
            
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(currentDate);
        
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
    
    public Integer calcularAnios(LicenciaDTO licenciaDTO){
        
        Date currentDate = new Date();
       
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(licenciaDTO.getFechaInicioVigencia());
            
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(currentDate);
        
        int yearLicencia=calendar1.get(Calendar.YEAR);
        int monthLicencia=calendar1.get(Calendar.MONTH)+1;
        int dayLicencia=calendar1.get(Calendar.DAY_OF_MONTH);
        
        int yearActual=calendar2.get(Calendar.YEAR);
        int monthActual=calendar2.get(Calendar.MONTH)+1;
        int dayActual=calendar2.get(Calendar.DAY_OF_MONTH);
        
        int anio = yearActual - yearLicencia;
        if (monthActual<monthLicencia || (monthActual==monthLicencia && dayActual<dayLicencia)){
            anio--;
        }
        
        return anio;
    }
    
    private String calcularInicioVigencia() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        String fechaInicioVigencia = calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
        return fechaInicioVigencia;
    }

    private String calcularFinVigencia() {
        titularDTO = gestorTitular.buscarTitularDTO(titularDTO).get(0);
        Integer edad = calcularEdad();
        Date currentDate = new Date();
        Date fechaNacimiento = titularDTO.getFechaNacimiento();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(currentDate);
        calendar2.setTime(fechaNacimiento);
        String fechaFinVigencia;
        
        if (edad<21){
            if (gestorLicencia.buscarLicencia(titularDTO).isEmpty()){
                fechaFinVigencia = calendar2.get(Calendar.DAY_OF_MONTH)+"/"+(calendar2.get(Calendar.MONTH)+1)+"/"+(calendar1.get(Calendar.YEAR)+1);
            }
            else{
                fechaFinVigencia = calendar2.get(Calendar.DAY_OF_MONTH)+"/"+(calendar2.get(Calendar.MONTH)+1)+"/"+(calendar1.get(Calendar.YEAR)+3);
            }
        }
        else if (edad<=46){
            fechaFinVigencia = calendar2.get(Calendar.DAY_OF_MONTH)+"/"+(calendar2.get(Calendar.MONTH)+1)+"/"+(calendar1.get(Calendar.YEAR)+5);
        }
        else if (edad<=60){
            fechaFinVigencia = calendar2.get(Calendar.DAY_OF_MONTH)+"/"+(calendar2.get(Calendar.MONTH)+1)+"/"+(calendar1.get(Calendar.YEAR)+4);
        }
        else if (edad<=70){
            fechaFinVigencia = calendar2.get(Calendar.DAY_OF_MONTH)+"/"+(calendar2.get(Calendar.MONTH)+1)+"/"+(calendar1.get(Calendar.YEAR)+3);
        }
        else{
            fechaFinVigencia = calendar2.get(Calendar.DAY_OF_MONTH)+"/"+(calendar2.get(Calendar.MONTH)+1)+"/"+(calendar1.get(Calendar.YEAR)+1);
        }
        
        return fechaFinVigencia;
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
            this.titularDTO = this.gestorTitular.buscarTitularDTO(titularDTO).get(0);
            String documento = titularDTO.getNroDoc();
            String clase = (String) boxClase.getSelectedItem();
            if (clase.equals("Seleccionar")){
                Util.mensajeAdvertencia("Advertencia", "No se permiten campo/s vacío/s");
            }
       
            else{
                if(validarEdad() && validarClase(clase)){
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateInicio = formatter.parse(txtInicioVigencia.getText());
                    Date dateFin = formatter.parse(txtFinVigencia.getText());
               
                    LicenciaDTO licenciaDTO = new LicenciaDTO();
                    licenciaDTO.setTitular(gestorTitular.buscarTitularDTO(titularDTO).get(0));
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
                else if (validarClase(clase)){
                    Mappers.Util.mensajeInformacion("Error", "El titular no cumple con la edad mínima requerida para licencias de esta clase");
                }
                else{
                     Mappers.Util.mensajeInformacion("Error", "El titular no cumple con los requisitos para sacar licencias de esta clase");
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
                if (gestorLicencia.buscarHistorico(titularDTO).isEmpty() && gestorLicencia.buscarLicencia(titularDTO).isEmpty()){
                    Titular titular = gestorTitular.buscarTitular(titularDTO).get(0);
                    gestorTitular.eliminarTitular(titularDTO);
                    if (titular.getDomicilio()!=null && gestorTitular.buscarTitularByDireccion(titular.getDomicilio()).isEmpty()){
                        gestorTitular.eliminarDomicilio(titular.getDomicilio());
                    }
                    Mappers.Util.mensajeInformacion("Exito", "Se ha borrado el titular con éxito, ya que el mismo no tenia asociada ninguna licencia vigente o vencida");
                }
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
