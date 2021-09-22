/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

/**
 *
 * @author Carlos Ortigoza
 */
public class connection {

    private static Connection connection = null;
    private static Statement statement;
    private static ResultSet resultSet;

    private PreparedStatement preparedStatement;

    private static connection instance;

    private static final String url = "jdbc:mysql://localhost:3306/control_output";
    private static final String username = "root";
    private static final String password = "prueba";

    private static void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            connection();
        }
        return connection;
    }

    public static boolean closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static ResultSet executeQuery(String sql) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            return null;
        }
        return resultSet;
    }

    public static int updateQuery(String sql) throws SQLException {
        String[] returnId = {"BATCHID"};
        int key = 0;
        statement = connection.createStatement();
        int affect = statement.executeUpdate(sql, returnId);

        if (affect == 0) {
            throw new SQLException("Created failed insertion");
        }
        try (ResultSet rs = statement.getGeneratedKeys()) {
            if (rs.next()) {
                key = rs.getInt(1);
            }
            rs.close();
        }

        return key;
    }
}
