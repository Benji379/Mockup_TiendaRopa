package com.mockup.dao;

import java.sql.*;
import com.mockup.modelo.Proceso;
import com.mockup.modelo.Producto;
import java.util.ArrayList;

public class DaoProducto {

    public static boolean registrarProducto(Producto p) {
        Connection conectar = null;
        PreparedStatement consulta = null;
        String sql = "INSERT INTO productos (nombreProducto, descripcionProducto, precioProducto, fecha, img) VALUES (?,?,?,?,?)";
        try {
            conectar = new ConexionBD().conexion();
            consulta = conectar.prepareStatement(sql);
            consulta.setString(1, p.getNombreProducto());
            consulta.setString(2, p.getDescripcionProducto());
            consulta.setDouble(3, p.getPrecioProducto());
            consulta.setTimestamp(4, Proceso.getDateTime());
            consulta.setBinaryStream(5, p.getFoto());
            int res = consulta.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DaoUtils.cerrar(conectar);
            DaoUtils.cerrar(consulta);
        }
        return false;
    }

    public static ArrayList<Producto> listaProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        String sql = "SELECT * FROM productos";
        try {
            conexion = new ConexionBD().conexion();
            consulta = conexion.prepareStatement(sql);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Producto p = new Producto();
                p.setIdProducto(resultado.getInt("idProducto"));
                p.setNombreProducto(resultado.getString("nombreProducto"));
                p.setPrecioProducto(resultado.getDouble("precioProducto"));
                p.setFecha(resultado.getTimestamp("fecha"));
                p.setDescripcionProducto(resultado.getString("descripcionProducto"));
                p.setFoto(resultado.getBinaryStream("img"));
                lista.add(p);
                System.out.println(p);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }finally{
            DaoUtils.cerrar(conexion);
            DaoUtils.cerrar(consulta);
            DaoUtils.cerrar(resultado);
        }
        return lista;
    }

}
