package br.com.aprendizado.Todolist.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;

        //1. Declaração e inicialização de variáveis (fora do try/catch)
        String driverName = "com.mysql.cj.jdbc.Driver";
        // Endereço do servidor do BD
        String serverName = "localhost";
        // Nome do seu banco de dados
        String mydatabase = "todolist_db";

        // String de Conexão.
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useTimezone=true&serverTimezone=UTC";
        // Nome de um usuário de seu BD
        String username = "root";
        // A senha de acesso do usuário informado acima.
        String password = "";

        try {

            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            // Trata erro se o JAR do MySQL não foi encontrado
            System.err.println("Erro: Driver JDBC não encontrado. " + e.getMessage());

        } catch (SQLException e) {
            // Trata erro se a conexão falhar (senha errada, MySQL desligado, etc.)
            System.err.println("Erro: Falha na conexão com o BD. Verifique o WAMP/MySQL.");
            e.printStackTrace();
        }

        return connection;
    }
;

}
