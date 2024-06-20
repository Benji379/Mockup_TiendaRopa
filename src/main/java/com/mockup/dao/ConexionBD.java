package com.mockup.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexionBD {
    
    public Connection conexion() {
        Connection conectar = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/mockup", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return conectar;
    }
}
