package com.mockup.modelo;

import java.sql.*;

public class Ingreso {

    private int idIngreso;
    private String ipIngreso;
    private int idUsuario;
    private Timestamp fecha;

    public Ingreso() {
    }

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }

    public String getIpIngreso() {
        return ipIngreso;
    }

    public void setIpIngreso(String ipIngreso) {
        this.ipIngreso = ipIngreso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
}
