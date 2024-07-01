/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;

import controllers.GestorDomicilio;
import controllers.GestorTitular;
import dto.TitularDTO;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import messages.Util;
import models.entities.Contribuyente;
import models.entities.Domicilio;
import models.entities.FactorRHEnum;
import models.entities.GrupoSanguineoEnum;
import models.entities.TipoDocumento;
import models.entities.Titular;

/**
 *
 * @author estan
 */
public class AltaTitular extends javax.swing.JFrame {
    
    GestorTitular gestorTitular = new GestorTitular();
    GestorDomicilio gestorDomicilio = new GestorDomicilio();
    
    //Bordes de los TXT y del Box para pintarlos
    Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
    Border originalTXTBorder;
    Border originalBOXBorder;

    /** Creates new form AltaTitular */
    public AltaTitular() {
        
        initComponents();
        
        originalTXTBorder  = txtNroDocumento.getBorder();
        originalBOXBorder = boxTipo.getBorder();
        mostrarTipoDoc();
    }
    
    public void mostrarTipoDoc(){
        
        List<TipoDocumento> tipos = gestorTitular.mostrarTipos();
        for(TipoDocumento t: tipos){
            boxTipo.addItem(t.getEspecificacion());
        }
    }
    
     //Funcion que pinta de rojo los TXT  y el Box de la busqueda en caso de que sean invalidos
    public void pintarInvalidosBuscar(String nroDocumento, boolean no_esta){
        if(nroDocumento.isEmpty() || !isInteger(nroDocumento)|| no_esta){
            txtNroDocumento.setBorder(redBorder);
        } else {
            txtNroDocumento.setBorder(originalTXTBorder);
        }
        if(no_esta){
            boxTipo.setBorder(redBorder);
        } else{
            boxTipo.setBorder(originalBOXBorder);
        }
    }
    
    
    
    public boolean pintarInvalidosAceptar(){
        Boolean invalidos = false;
        
        if(txtNroDocumento.getText().isEmpty() || !isInteger(txtNroDocumento.getText())){
            txtNroDocumento.setBorder(redBorder);
            invalidos = true;
        } else {
            txtNroDocumento.setBorder(originalTXTBorder);
        }
        
        if(txtNombre.getText().isEmpty()){
            txtNombre.setBorder(redBorder);
            invalidos = true;
        } else {
            txtNombre.setBorder(originalTXTBorder);
        }
        
        if(txtApellido.getText().isEmpty()){
            txtApellido.setBorder(redBorder);
            invalidos = true;
        } else {
            txtApellido.setBorder(originalTXTBorder);
        }
        
        if(txtCalle.getText().isEmpty()){
            txtCalle.setBorder(redBorder);
            invalidos = true;
        } else {
            txtCalle.setBorder(originalTXTBorder);
        }
        
        if(txtNumero.getText().isEmpty() || !isInteger(txtNumero.getText())){
            txtNumero.setBorder(redBorder);
            invalidos = true;
        } else {
            txtNumero.setBorder(originalTXTBorder);
        }
        
        if(txtCUIL.getText().isEmpty() || !isInteger(txtCUIL.getText())){
            txtCUIL.setBorder(redBorder);
            invalidos = true;
        } else {
            txtCUIL.setBorder(originalTXTBorder);
        }
        
        if(txtFechaDeNacimiento.getText().isEmpty()){
            txtFechaDeNacimiento.setBorder(redBorder);
            invalidos = true;
        } else {
            txtFechaDeNacimiento.setBorder(originalTXTBorder);
        }
        
        if(txtGrupoSanguineo.getText().isEmpty() || !estaEnEnum(txtGrupoSanguineo.getText(),GrupoSanguineoEnum.class)){
            txtGrupoSanguineo.setBorder(redBorder);
            invalidos = true;
        } else {
            txtGrupoSanguineo.setBorder(originalTXTBorder);
        }
        
        if(txtFactor.getText().isEmpty() || !estaEnEnum(txtFactor.getText(),FactorRHEnum.class)){
            txtFactor.setBorder(redBorder);
            invalidos = true;
        } else {
            txtFactor.setBorder(originalTXTBorder);
        }
        
        return invalidos;
    }
    
    //Funcion para comprobar que un string es un numero
    public boolean isInteger(String numero){
        try{
            Long.parseLong(numero);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public boolean estaEnEnum(String valor, Class<? extends Enum<?>> enumClass){
        for (Enum<?> enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.name().equals(valor)) {
                return true;
            }
        }
        return false;
    }
    
    public void rellenarInformacion(TitularDTO titularDTO, boolean es_titular){
        
        txtNombre.setEditable(!es_titular);
        txtApellido.setEditable(!es_titular);
        txtCalle.setEditable(!es_titular);
        txtNumero.setEditable(!es_titular);
        txtPiso.setEditable(!es_titular);
        txtDepto.setEditable(!es_titular);
        txtCUIL.setEditable(!es_titular);
        txtFechaDeNacimiento.setEditable(!es_titular);
        txtGrupoSanguineo.setEditable(!es_titular);
        txtFactor.setEditable(!es_titular);
        checkDonante.setEnabled(!es_titular);
        
        try {
            if(es_titular){
                Titular titular = gestorTitular.buscarTitular(titularDTO).get(0);
               
                txtNombre.setText(titular.getNombre());
                txtApellido.setText(titular.getApellido());
                txtCalle.setText(titular.getDomicilio().getCalle());
                txtNumero.setText(titular.getDomicilio().getPiso().toString());
                if(titular.getDomicilio().getNumero() != null){
                   txtPiso.setText(titular.getDomicilio().getNumero().toString());
                }
                if(titular.getDomicilio().getDepartamento() != null){
                   txtDepto.setText(titular.getDomicilio().getDepartamento());
                }
                txtCUIL.setText(titular.getCuil());
                txtFechaDeNacimiento.setText(new SimpleDateFormat("dd/MM/yyyy").format(titular.getFechaNacimiento()));
                txtGrupoSanguineo.setText(titular.getGrupoSanguineo().toString());
                txtFactor.setText(titular.getFactorRH().toString());
                checkDonante.setSelected(titular.getDonanteDeOrganos());
            } else {
                Contribuyente contribuyente = gestorTitular.buscarContribuyente(titularDTO.getNroDoc(), titularDTO.getTipoDoc().getEspecificacion());
                
                txtNombre.setText(contribuyente.getNombre());
                txtApellido.setText(contribuyente.getApellido());
                txtCalle.setText(contribuyente.getCalle());
                txtNumero.setText(contribuyente.getPiso().toString());
                if(contribuyente.getNumero() != null){
                   txtPiso.setText(contribuyente.getNumero().toString());
                }
                if(contribuyente.getDepartamento() != null){
                   txtDepto.setText(contribuyente.getDepartamento());
                }
                txtCUIL.setText(contribuyente.getCuil());
                txtFechaDeNacimiento.setText(new SimpleDateFormat("dd/MM/yyyy").format(contribuyente.getFechaNacimiento()));
                txtGrupoSanguineo.setText(contribuyente.getGrupoSanguineo().toString());
                txtFactor.setText(contribuyente.getFactorRH().toString());
                checkDonante.setSelected(contribuyente.getDonanteDeOrganos());
            }
        } catch (Exception e) {
        }
    }
    
    public void vaciarInformacion(){
        
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);
        txtCalle.setEditable(false);
        txtNumero.setEditable(false);
        txtPiso.setEditable(false);
        txtDepto.setEditable(false);
        txtCUIL.setEditable(false);
        txtFechaDeNacimiento.setEditable(false);
        txtGrupoSanguineo.setEditable(false);
        txtFactor.setEditable(false);
        checkDonante.setEnabled(false);
        
        txtNombre.setText(null);
        txtApellido.setText(null);
        txtCalle.setText(null);
        txtNumero.setText(null);
        txtPiso.setText(null);
        txtDepto.setText(null);
        txtCUIL.setText(null);
        txtFechaDeNacimiento.setText(null);
        txtGrupoSanguineo.setText(null);
        txtFactor.setText(null);
        checkDonante.setSelected(false);
    }
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel_Documento = new javax.swing.JLabel();
        txtNroDocumento = new javax.swing.JTextField();
        jLabel_Documento1 = new javax.swing.JLabel();
        boxTipo = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel_Documento2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel_Documento3 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel_Documento4 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        jLabel_Documento5 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel_Documento6 = new javax.swing.JLabel();
        txtPiso = new javax.swing.JTextField();
        jLabel_Documento7 = new javax.swing.JLabel();
        txtDepto = new javax.swing.JTextField();
        jLabel_Documento8 = new javax.swing.JLabel();
        txtCUIL = new javax.swing.JTextField();
        jLabel_Documento9 = new javax.swing.JLabel();
        jLabel_Documento10 = new javax.swing.JLabel();
        txtGrupoSanguineo = new javax.swing.JTextField();
        jLabel_Documento11 = new javax.swing.JLabel();
        txtFactor = new javax.swing.JTextField();
        checkDonante = new javax.swing.JCheckBox();
        jLabel_Documento12 = new javax.swing.JLabel();
        btnBuscar1 = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        txtFechaDeNacimiento = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Alta de Titular");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel_Documento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento.setText("Nro. de Documento:");

        jLabel_Documento1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento1.setText("Tipo:");

        boxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxTipoActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel_Documento2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento2.setText("Nombre");

        txtNombre.setEditable(false);

        jLabel_Documento3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento3.setText("Apellido");

        txtApellido.setEditable(false);

        jLabel_Documento4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento4.setText("Calle:");

        txtCalle.setEditable(false);

        jLabel_Documento5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento5.setText("Numero:");

        txtNumero.setEditable(false);

        jLabel_Documento6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento6.setText("Piso:");

        txtPiso.setEditable(false);

        jLabel_Documento7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento7.setText("Depto:");

        txtDepto.setEditable(false);

        jLabel_Documento8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento8.setText("CUIL:");

        txtCUIL.setEditable(false);

        jLabel_Documento9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento9.setText("Fecha de Nacimiento:");

        jLabel_Documento10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento10.setText("Grupo Sanguineo:");

        txtGrupoSanguineo.setEditable(false);

        jLabel_Documento11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento11.setText("Factor:");

        txtFactor.setEditable(false);

        checkDonante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        checkDonante.setEnabled(false);
        checkDonante.setFocusable(false);
        checkDonante.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        checkDonante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDonanteActionPerformed(evt);
            }
        });

        jLabel_Documento12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Documento12.setText("Donante:");

        btnBuscar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscar1.setText("BUSCAR");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        txtFechaDeNacimiento.setEditable(false);
        txtFechaDeNacimiento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNroDocumento)
                            .addComponent(jLabel_Documento, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Documento1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre)
                                    .addComponent(jLabel_Documento2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtApellido)
                                    .addComponent(jLabel_Documento3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCalle)
                                            .addComponent(jLabel_Documento4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(53, 53, 53)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel_Documento5))
                                        .addGap(46, 46, 46)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel_Documento6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(43, 43, 43))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtGrupoSanguineo)
                                            .addComponent(jLabel_Documento10, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(53, 53, 53)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFactor)
                                            .addComponent(jLabel_Documento11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel_Documento12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDepto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_Documento7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkDonante, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCUIL)
                                    .addComponent(jLabel_Documento8, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_Documento9, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaDeNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(523, Short.MAX_VALUE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(114, 114, 114)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Documento)
                    .addComponent(jLabel_Documento1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Documento2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Documento3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Documento4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Documento5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel_Documento6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_Documento7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDepto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_Documento8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCUIL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_Documento9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaDeNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_Documento10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGrupoSanguineo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_Documento11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Documento12)
                            .addComponent(checkDonante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(134, 134, 134)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(486, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxTipoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
 
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void checkDonanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDonanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkDonanteActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        
        //Obtenemos los valores
        String nroDocumento = (String) txtNroDocumento.getText();
        String tipo = (String) boxTipo.getSelectedItem();
        
        if(nroDocumento.isEmpty()){
            pintarInvalidosBuscar(nroDocumento, false);
            vaciarInformacion();
            Util.mensajeAdvertencia("Advertencia", "No se permiten campo/s vacío/s");
        } else if(!isInteger(nroDocumento)){
            pintarInvalidosBuscar(nroDocumento, false);
            vaciarInformacion();
            Util.mensajeAdvertencia("Advertencia", "Formato invalido");
        } else {
            
            //Buscamos en la tabla de titulares
            try {
                List<TitularDTO> titulares;
                TitularDTO titularDTO = new TitularDTO();
                titularDTO.setNroDoc(nroDocumento);
                titularDTO.setTipoDoc(gestorTitular.buscarTipoDoc(tipo));
                titulares = gestorTitular.buscarTitularDTO(titularDTO);
                if(titulares.isEmpty()){
                    try {
                        Contribuyente contribuyente = gestorTitular.buscarContribuyente(nroDocumento, tipo);
                        if(contribuyente == null){
                            pintarInvalidosBuscar(nroDocumento, true);
                            vaciarInformacion();
                            Util.mensajeAdvertencia("Advertencia", "No se encuentra la combinacion de numero y tipo documento en ninguna base de datos");
                        } else {
                            pintarInvalidosBuscar(nroDocumento, false);
                            rellenarInformacion(titularDTO, false);
                            Util.mensajeInformacion("Confirmado","Datos traidos de tabla Contribuyente");
                        }
                    } catch (Exception e) {
                    }
                    
                } else {
                    titularDTO = titulares.get(0);
                    pintarInvalidosBuscar(nroDocumento, false);
                    rellenarInformacion(titularDTO, true);
                    Util.mensajeInformacion("Confirmado","Datos traidos de tabla Titulares");
                }
                
            } catch (Exception e) {
                Util.mensajeError("Error", e.toString());
            }
        }
                
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        if(!txtNombre.isEditable() && txtNombre.getText().isEmpty()){
            Util.mensajeAdvertencia("Advertencia", "Tiene que buscar un titular o contribuyente primero");
            
        } else if(txtNombre.isEditable()){
            if(pintarInvalidosAceptar()){
                Util.mensajeAdvertencia("Advertencia", "Campos con formatos invalidos");
                return;
            }
            Titular titular = new Titular();
            titular.setNumeroDocumento(txtNroDocumento.getText());
            titular.setNombre(txtNombre.getText());
            titular.setApellido(txtApellido.getText());
            titular.setCuil(txtCUIL.getText());
            try {
                titular.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(txtFechaDeNacimiento.getText()));
            } catch (ParseException ex) {
                Logger.getLogger(AltaTitular.class.getName()).log(Level.SEVERE, null, ex);
            }
            titular.setGrupoSanguineo(GrupoSanguineoEnum.valueOf(txtGrupoSanguineo.getText()));
            titular.setFactorRH(FactorRHEnum.valueOf(txtFactor.getText()));
            titular.setDonanteDeOrganos(checkDonante.isSelected());
            titular.setTipoDocumento(gestorTitular.buscarTipoDoc((String) boxTipo.getSelectedItem()));
            
            Domicilio domicilio = new Domicilio();
            domicilio.setCalle(txtCalle.getText());
            domicilio.setPiso(Integer.parseInt(txtNumero.getText()));
            if(!txtPiso.getText().isEmpty()){
                domicilio.setNumero(Integer.parseInt(txtPiso.getText()));
            }
            if(!txtDepto.getText().isEmpty()){
                domicilio.setDepartamento(txtDepto.getText());
            }
            
            try {
                titular.setDomicilio(gestorDomicilio.altaDomicilio(domicilio));
                gestorTitular.altaTitular(titular);
            } catch (Exception e) {
            }
            
            
        }
        
    }//GEN-LAST:event_btnAceptarActionPerformed
    
    
    
    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxTipo;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox checkDonante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Documento;
    private javax.swing.JLabel jLabel_Documento1;
    private javax.swing.JLabel jLabel_Documento10;
    private javax.swing.JLabel jLabel_Documento11;
    private javax.swing.JLabel jLabel_Documento12;
    private javax.swing.JLabel jLabel_Documento2;
    private javax.swing.JLabel jLabel_Documento3;
    private javax.swing.JLabel jLabel_Documento4;
    private javax.swing.JLabel jLabel_Documento5;
    private javax.swing.JLabel jLabel_Documento6;
    private javax.swing.JLabel jLabel_Documento7;
    private javax.swing.JLabel jLabel_Documento8;
    private javax.swing.JLabel jLabel_Documento9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCUIL;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtDepto;
    private javax.swing.JTextField txtFactor;
    private javax.swing.JFormattedTextField txtFechaDeNacimiento;
    private javax.swing.JTextField txtGrupoSanguineo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNroDocumento;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPiso;
    // End of variables declaration//GEN-END:variables

}
