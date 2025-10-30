/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienditasanchez;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Producto 
{
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    private String fechaRegistro;

    public Producto()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.fechaRegistro = dtf.format(LocalDateTime.now());
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getFechaRegistro() { return fechaRegistro; }

    public void mostrarDatos()
    {
        System.out.println("codigo: " + codigo + " | nombre: " + nombre + 
                " | precio: " + precio + " | stock: " + stock + 
                " | registrado: " + fechaRegistro);
    }
}
