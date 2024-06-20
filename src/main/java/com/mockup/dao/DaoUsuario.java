package com.mockup.dao;

import com.mockup.modelo.Proceso;
import com.mockup.modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;

public class DaoUsuario {

    public static boolean registrarUsuario(Usuario u) {
        Connection conexion = null;
        PreparedStatement consulta = null;
        String sql = "INSERT INTO usuarios (nombreUsuario, correoUsuario, password, fecha) VALUES (?,?,?,?)";
        try {
            conexion = new ConexionBD().conexion();
            consulta = conexion.prepareStatement(sql);
            consulta.setString(1, u.getNombreUsuario());
            consulta.setString(2, u.getCorreoUsuario());
//            String hash = PasswordHasher.hashearPassword(u.getPassword());
            consulta.setString(3, u.getPassword());
            consulta.setTimestamp(4, Proceso.getDateTime());
            int res = consulta.executeUpdate();
            if (res > 0) {
                System.out.println("REGISITRADO EXITOSAMENTE");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            DaoUtils.cerrar(conexion);
            DaoUtils.cerrar(consulta);
        }
        return false;
    }

    public static ArrayList<Usuario> listaUsuarios() {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        String sql = "SELECT * FROM usuarios";
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            conexion = new ConexionBD().conexion();
            consulta = conexion.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(resultado.getInt("idUsuario"));
                u.setNombreUsuario(resultado.getString("nombreUsuario"));
                u.setCorreoUsuario(resultado.getString("correoUsuario"));
                u.setPassword(resultado.getString("password"));
                u.setFecha(resultado.getTimestamp("fecha"));
                lista.add(u);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            DaoUtils.cerrar(consulta);
            DaoUtils.cerrar(conexion);
            DaoUtils.cerrar(resultado);
        }
        return lista;
    }
}
