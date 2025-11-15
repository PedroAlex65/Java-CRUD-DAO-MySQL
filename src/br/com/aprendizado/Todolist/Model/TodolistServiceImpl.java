/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.aprendizado.Todolist.Model;

import java.util.ArrayList;

//importações do mysql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet; //Para o método SELECT futuro
import java.lang.ClassNotFoundException;
import java.util.List;

public class TodolistServiceImpl implements TodolistService {

    List<String> tarefas = new ArrayList<>();
    private TarefaDAO tarefaDAO;

    public TodolistServiceImpl() {
        this.tarefaDAO = new TarefaDAOImpl();

    }

    public void addTarefa(String tarefa) throws SQLException, ClassNotFoundException {
        if (tarefas.size() >= 5 || tarefa.trim().isEmpty()) {
            System.out.println("Aviso: Bloqueio por limite de 5 tarefas ou input vazio.");
            return;
        }
        System.out.println("LOG: Regras de Negócio Aprovadas. Tentando INSERT no BD...");
        tarefaDAO.inserir(tarefa);
        carregarArquivo();
    }

    public void carregarArquivo() throws ClassNotFoundException, SQLException {

        try {

            this.tarefas = tarefaDAO.listarTodos();
        } catch (ClassNotFoundException e) { // <-- ADICIONE ESTE BLOCO!

            // Trata o erro do Driver (se a classe não for encontrada)
            System.out.println("Erro: Driver JDBC não encontrado. " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerTarefas() throws SQLException, ClassNotFoundException {
        if (tarefas.size() == 0) {
            return;
        }
        tarefaDAO.removerUltima();
        carregarArquivo();
    }

    public List<String> getTarefas() {
        return tarefas;
    }

    public boolean testarConexao() {
        try (Connection conexao = Conexao.getConnection()) {
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro ao testar Conexão: " + e.getMessage());
            return false;
        }
    }
}
