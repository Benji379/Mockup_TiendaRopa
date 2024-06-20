package com.mockup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtils {

    public static void cerrar(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
//                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    public static void cerrar(ResultSet resultado) {
        if (resultado != null) {
            try {
                resultado.close();
            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    public static void cerrar(PreparedStatement consulta) {
        if (consulta != null) {
            try {
                consulta.close();
            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
