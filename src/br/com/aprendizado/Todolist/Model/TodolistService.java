/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.aprendizado.Todolist.Model;

import java.sql.SQLException;

/**
 *
 * @author Pedro Alex
 */
public interface TodolistService {

    public void addTarefa(String tarefa) throws SQLException, ClassNotFoundException;

    public void carregarArquivo() throws ClassNotFoundException, SQLException;

    public void removerTarefas() throws ClassNotFoundException, SQLException;
}
