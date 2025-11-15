/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.aprendizado.Todolist.View;

import br.com.aprendizado.Todolist.Model.TodolistServiceImpl;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; //Para o método SELECT futuro

/**
 *
 * @author pedro
 */
public class TodolistView extends javax.swing.JFrame {

    private TodolistServiceImpl todoList;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TodolistView.class.getName());

    public TodolistView(TodolistServiceImpl model) {
        //1. Atribuição: Guarda a instância que foi "injetada" pelo main
        this.todoList = model;
        //2. Inicialização: o restante do código do construtor
        initComponents();

        listaLabels = new javax.swing.JLabel[]{jLabelTarefa1, jLabelTarefa2, jLabelTarefa3, jLabelTarefa4, jLabelTarefa5};

        try {
            todoList.carregarArquivo();
            atualizarLista();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro: o sistema falhou ao carregar tarefas do MySQL: " + e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTxtTarefa = new javax.swing.JTextField();
        jLabelTarefa1 = new javax.swing.JLabel();
        jLabelTarefa2 = new javax.swing.JLabel();
        jLabelTarefa3 = new javax.swing.JLabel();
        jLabelTarefa4 = new javax.swing.JLabel();
        jLabelTarefa5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTxtTarefa.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jTxtTarefa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtTarefa.setBorder(null);
        jTxtTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtTarefaActionPerformed(evt);
            }
        });
        getContentPane().add(jTxtTarefa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 420, 30));

        jLabelTarefa1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        getContentPane().add(jLabelTarefa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 420, 30));

        jLabelTarefa2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        getContentPane().add(jLabelTarefa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 420, 30));

        jLabelTarefa3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        getContentPane().add(jLabelTarefa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 420, 30));

        jLabelTarefa4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        getContentPane().add(jLabelTarefa4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 420, 30));

        jLabelTarefa5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        getContentPane().add(jLabelTarefa5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 420, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/todo.PNG"))); // NOI18N
        jLabel1.setInheritsPopupMenu(false);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 841, 683));

        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.setInheritsPopupMenu(true);
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 135, 156, 42));

        jButtonRemover.setText("REMOVER");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 360, 160, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void atualizarLista() {
        //Loop de limpeza (ok)...
        for (int i = 0; i < listaLabels.length; i++) {
            listaLabels[i].setText("");
        }
        //Pegua o ArrayList<String> e converte para um array de objetos
        Object[] tarefas = todoList.getTarefas().toArray();

        //3. O loop de Exibição Correto (Usando o array de objetos)
        for (int i = 0;
                i < listaLabels.length && i < tarefas.length;
                i++) {
            if (tarefas[i] != null) {
                //Conversão de Objetc para String
                listaLabels[i].setText((i + 1) + ". " + (String) tarefas[i]);

            }
        }

    }


    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        // TODO add your handling code here:
        //1. Adiciona a tarefa ao Model
        try {
            todoList.addTarefa(jTxtTarefa.getText());

            //2. Chama o método único de atualização (que faz o resto do trabalho)
            atualizarLista();

            //3. Limpa o campo de entrada
            jTxtTarefa.setText("");
        } catch (SQLException | ClassNotFoundException e) {
            // Mostramos a mensagem de erro no BD
            System.out.println("Erro ao inserir tarefa no BD: " + e.getMessage());

            // E rodamos o stack trace para depuração
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jTxtTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtTarefaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtTarefaActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        // TODO add your handling code here:
        //1. Diga ao Model para remover a última tarefa
        try {
            todoList.removerTarefas();         //unreported exception ClassNotFoundException; must be caught or declared to be  thrown

            //2. Diga ao view para se atualizar (o método faz o resto!)
            atualizarLista();
        } catch (SQLException | ClassNotFoundException e) { // exception SQLException is never thrown in body of corresponding try statement
            // Se a remoção falhou (MySQL offline, erro de driver)
            System.out.println("ERRO: Falha ao remover a tarefa. Verifique o BD.");
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButtonRemoverActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        TodolistServiceImpl todoList = new TodolistServiceImpl();

       // if (todoList.testarConexao()) {
            // ...
       //     System.out.println("Status: Conexão com MySQL OK.");
       //     TodolistView todoView = new TodolistView(todoList);

            java.awt.EventQueue.invokeLater(() -> todoView.setVisible(true));
       // } else {
       //     System.err.println("ERRO CRÍTICO: Falha ao conectar com o MySql.");
       //     return;
       // }
    }

    // Array para agrupar os JLabels de tarefas

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTarefa1;
    private javax.swing.JLabel jLabelTarefa2;
    private javax.swing.JLabel jLabelTarefa3;
    private javax.swing.JLabel jLabelTarefa4;
    private javax.swing.JLabel jLabelTarefa5;
    private javax.swing.JTextField jTxtTarefa;
    // End of variables declaration//GEN-END:variables
private javax.swing.JLabel[] listaLabels;
}
