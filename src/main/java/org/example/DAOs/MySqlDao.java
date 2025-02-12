package org.example.DAOs;

import org.example.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDao {

    public Connection getConnection() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/finance_manager";
        String username = "root";
        String password = "";
        Connection connection = null;

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url ,username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to find driver class: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Failed to connect to a database: "+ e.getMessage());
        }

        return connection;
    }

    public void freeConnection(Connection connection) throws DaoException
    {
        try {
            if(connection != null)
            {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection: "+ e.getMessage());
            System.exit(1);
        }
    }


}
