/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author CCNAR
 */
public class usuario {
    private int id; 
    private String Codigo;
    private String Nombres;
    private String Apellidos;
    private String Telefonos;
    private String Usuario;
    private String Contraseña;
    private int Activo;

    public usuario(int id, String Codigo, String Nombres, String Apellidos, String Telefonos, String Usuario, String Contraseña,int Activo) {
        this.id = id;
        this.Codigo = Codigo;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Telefonos = Telefonos;
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
        this.Activo = Activo;
    }

    public usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getTelefonos() {
        return Telefonos;
    }

    public void setTelefonos(String Telefonos) {
        this.Telefonos = Telefonos;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int Activo) {
        this.Activo = Activo;
    }
    
    
}
