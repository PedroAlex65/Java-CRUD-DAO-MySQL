/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.aprendizado.Todolist.Model;

/**
 *
 * @author Pedro Alex
 */
//importações do mysql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet; //Para o método SELECT futuro
import java.lang.ClassNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAOImpl implements TarefaDAO {

    ResultSet rs = null;
    Connection conexao = null;
    PreparedStatement stmt = null;

    public void inserir(String tarefa) throws SQLException {

        try {
            //1. OBTÉM A CONEXÃO E O STATEMENT
            conexao = Conexao.getConnection();

            String sqlInsert = "INSERT INTO tarefas(nome_tarefa) VALUES (?)";
            stmt = conexao.prepareStatement(sqlInsert);
            // ... resto do código ...
            //2. SETA O PARÂMETRO (substitui o '?')
            //COMO A 'tarefa é uma String, você usa setString'
            stmt.setString(1, tarefa);

            //3. EXECUTA O COMANDO (INSERT, UPDATE, DELETE usam executeUpdate)
            stmt.executeUpdate();

        } catch (ClassNotFoundException e) { // <-- ADICIONE ESTE BLOCO!
            // Trata o erro do Driver (se a classe não for encontrada)
            System.out.println("Erro: Driver JDBC não encontrado. " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("erro ao inserir tarefa no BD: " + e.getMessage());
        } finally { // <-- inicio do finally
            //1. Tenta fechar o PreparedStatement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //2. Tenta fehcar a Conexão
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } //<-- fim do finally
    }

    public List<String> listarTodos() throws ClassNotFoundException, SQLException {
        ArrayList<String> tarefas = new ArrayList<>();
        String fromSQL = "SELECT*FROM tarefas";

        try {

            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(fromSQL);
            tarefas.clear();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tarefaLida = rs.getString("nome_tarefa");
                tarefas.add(tarefaLida);
            }
        } catch (ClassNotFoundException e) { // <-- ADICIONE ESTE BLOCO!
            // Trata o erro do Driver (se a classe não for encontrada)
            System.out.println("Erro: Driver JDBC não encontrado. " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

// <-- inicio do finally
            //1. Tenta fechar o PreparedStatement
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //2. Tenta fehcar a Conexão
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } //<-- fim do finally
        return tarefas;
    }

    public void removerUltima() throws SQLException, ClassNotFoundException {

        String SQLDelete = "DELETE FROM tarefas ORDER BY id DESC LIMIT 1";
        try {
            conexao = Conexao.getConnection();

            stmt = conexao.prepareStatement(SQLDelete);
            stmt.executeUpdate();

        } catch (ClassNotFoundException e) { // <-- ADICIONE ESTE BLOCO!

            // Trata o erro do Driver (se a classe não for encontrada)
            System.out.println("Erro: Driver JDBC não encontrado. " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
