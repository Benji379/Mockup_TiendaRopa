package com.mockup.modelo;

import java.sql.Timestamp;

public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String password;
    private Timestamp fecha;

    public Usuario() {
        
    }

    public Usuario(String nombreUsuario, String correoUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

}
