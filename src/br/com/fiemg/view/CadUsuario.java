/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fiemg.view;

import br.com.fiemg.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class CadUsuario extends javax.swing.JInternalFrame {

    //iniciar variáveis de conexão
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public CadUsuario() {
        initComponents();
        //iniciar a conexão com a classe Conexao
        con = Conexao.conectar();
        desabilitarCampos();

    }
    public static void fechar(){
        
    }

    //método de pesquisa no banco
    private void consultar() {   //Comando para consulta no banco
        String sql = "Select * from usuario "
                + "where id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuLogin.setText(rs.getString(3));
                txtUsuSenha.setText(rs.getString(4));
                cbUsuPerfil.setSelectedItem(rs.getString(5));

            } else {
                JOptionPane.showMessageDialog(null,
                        "Usuário não encontrado!");
                desabilitarCampos();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    private void inserir() {
        String sql = "Insert into usuario(nome, login, senha, perfil) values(?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuLogin.getText());
            pst.setString(3, txtUsuSenha.getText());
            pst.setString(4, cbUsuPerfil.getSelectedItem().toString());
            int op = pst.executeUpdate();
            if (op > 0) {
                JOptionPane.showMessageDialog(null,
                        "Inserido com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void alterar()
    {
        String sql = "UPDATE usuario "
                + "SET nome = ?, login = ?, senha=?, perfil=?\n" 
                +" WHERE id = ?";
        try {
            if(txtUsuNome.getText().isEmpty()||txtUsuLogin.getText().isEmpty()
                    ||txtUsuSenha.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                
            }else{
 
            pst = con.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuLogin.getText());
            pst.setString(3, txtUsuSenha.getText());
            pst.setString(4, cbUsuPerfil.getSelectedItem().toString());
            pst.setString(5, txtUsuId.getText());
            int valida = pst.executeUpdate();
            if(valida>0)
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                
            }    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    private void remover()
    {
        String sql = "Delete from usuario where id=?";
        int confirma=JOptionPane.showConfirmDialog(null, "Deseja realmente deletar?", 
                "Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());
                int op = pst.executeUpdate();
                if(op>0)
                    JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
                desabilitarCampos();
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        cbUsuPerfil = new javax.swing.JComboBox<>();
        btnUsuExcluir = new javax.swing.JButton();
        btnUsuEditar = new javax.swing.JButton();
        btnUsuPesquisar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnUsuNovo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Usuário");
        setPreferredSize(new java.awt.Dimension(430, 280));

        jLabel8.setText("Perfil:");

        cbUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "administrador", "usuario" }));

        btnUsuExcluir.setBackground(new java.awt.Color(51, 102, 255));
        btnUsuExcluir.setText("Excluir");
        btnUsuExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuExcluirActionPerformed(evt);
            }
        });

        btnUsuEditar.setBackground(new java.awt.Color(51, 102, 255));
        btnUsuEditar.setText("Editar");
        btnUsuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuEditarActionPerformed(evt);
            }
        });

        btnUsuPesquisar.setBackground(new java.awt.Color(51, 102, 255));
        btnUsuPesquisar.setText("Pesquisar");
        btnUsuPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuPesquisarActionPerformed(evt);
            }
        });

        jLabel1.setText("Id:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Login:");

        jLabel5.setText("Senha:");

        btnUsuNovo.setBackground(new java.awt.Color(51, 102, 255));
        btnUsuNovo.setText("Inserir");
        btnUsuNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtUsuNome)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(23, 23, 23)
                                .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(btnUsuNovo)
                                .addGap(18, 18, 18)
                                .addComponent(btnUsuPesquisar)
                                .addGap(18, 18, 18)
                                .addComponent(btnUsuEditar)
                                .addGap(18, 18, 18)
                                .addComponent(btnUsuExcluir)))
                        .addGap(0, 54, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuExcluir)
                    .addComponent(btnUsuEditar)
                    .addComponent(btnUsuPesquisar)
                    .addComponent(btnUsuNovo))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuPesquisarActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_btnUsuPesquisarActionPerformed

    private void btnUsuNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuNovoActionPerformed
        // TODO add your handling code here:
        inserir();
    }//GEN-LAST:event_btnUsuNovoActionPerformed

    private void btnUsuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuEditarActionPerformed
        alterar();
    }//GEN-LAST:event_btnUsuEditarActionPerformed

    private void btnUsuExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuExcluirActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnUsuExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuEditar;
    private javax.swing.JButton btnUsuExcluir;
    private javax.swing.JButton btnUsuNovo;
    private javax.swing.JButton btnUsuPesquisar;
    private javax.swing.JComboBox<String> cbUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables

    private void desabilitarCampos() {
        txtUsuNome.setText(null);
        txtUsuLogin.setText(null);
        txtUsuSenha.setText(null);
        cbUsuPerfil.setSelectedItem(null);

    }
}
