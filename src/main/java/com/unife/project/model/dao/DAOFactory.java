package com.unife.project.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.unife.project.util.DatabaseConnection;

public class DAOFactory {
    private static Connection connection;

    static {
        // Initialize the database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
    }

    public static GenericDAO getAnimaleDAO() {
        return new AnimaleDAOImpl(connection);
    }

    /*public static GenericDAO getIrrigazioneDAO() {
        return new IrrigazioneDAO(connection);
    }*/
}