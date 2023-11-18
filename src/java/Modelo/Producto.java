
package Modelo;

public class Producto {
    private int Id;
    private String Nombre;
    private double Precio;
    private String Descripcion;
    private double Stock;
    private String CodigoBarra;

    public Producto(int id, String Nombre, double Precio, String Descripcion, double Stock, String CodigoBarra) {
        this.Id = id;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Descripcion = Descripcion;
        this.Stock = Stock;
        this.CodigoBarra = CodigoBarra;
    }
    
    public Producto() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getStock() {
        return Stock;
    }

    public void setStock(double Stock) {
        this.Stock = Stock;
    }

    public String getCodigoBarra() {
        return CodigoBarra;
    }

    public void setCodigoBarra(String CodigoBarra) {
        this.CodigoBarra = CodigoBarra;
    }
    
    
}
