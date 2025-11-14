/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.aprendizado.Todolist.Model;

import java.util.List;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

public interface TarefaDAO {
//Assinatura dos métodos (apenas o "contrato")

    public void inserir(String tarefa) throws SQLException, ClassNotFoundException;
    public List<String> listarTodos() throws SQLException, ClassNotFoundException;
    public void removerUltima() throws SQLException, ClassNotFoundException;
}