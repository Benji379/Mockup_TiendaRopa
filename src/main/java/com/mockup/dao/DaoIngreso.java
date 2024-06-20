package com.mockup.dao;

import com.mockup.modelo.Proceso;
import java.sql.*;

public class DaoIngreso {

    public static boolean registrarIngreso(int idUsuario) {
        Connection conexion = null;
        PreparedStatement consulta = null;
        String sql = "INSERT INTO ingreso (idUsuario, ipIngreso, fecha) VALUES (?,?,?)";
        try {
            conexion = new ConexionBD().conexion();
            consulta = conexion.prepareStatement(sql);
            consulta.setInt(1, idUsuario);
            consulta.setString(2, Proceso.getIp());
            consulta.setTimestamp(3, Proceso.getDateTime());
            int res = consulta.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DaoUtils.cerrar(conexion);
            DaoUtils.cerrar(consulta);
        }
        return false;
    }

}
