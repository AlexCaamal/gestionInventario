/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class Respuesta<TResult> {
    
    public String mensaje;
    public TResult contenido;

    public Respuesta(String mensaje) {
        this.mensaje = mensaje;
        this.contenido = null;
    }

    public Respuesta(TResult contenido) {
        this.contenido = contenido;
        this.mensaje = null;
    }
    
    public boolean esExito() {
        return this.mensaje == null;
    }

    public boolean esError() {
        return !esExito();
    }
}
