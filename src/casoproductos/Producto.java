/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoproductos;

/**
 *
 * @author luis2
 */
public class Producto {
    
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;

    // Sobrecarga de constructores
    public Producto() {
    }

    public Producto(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && codigo.length() == 4) {
            this.codigo = codigo;
        } else {
            System.out.println("Error: el codigo debe tener 4 caracteres.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        } else {
            System.out.println("Error: el precio no puede ser negativo.");
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
       if (stock >= 0) {
            this.stock = stock;
        } else {
            System.out.println("Error: el stock no puede ser negativo.");
        }
    }
     void verProducto() {
        System.out.println("Producto [Codigo: " + this.codigo +
                ", Nombre: " + this.nombre +
                ", Precio: " + this.precio +
                ", Stock: " + this.stock + "]");
    }
}
