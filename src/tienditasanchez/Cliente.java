/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienditasanchez;

import java.util.ArrayList;

/**
 *
 * @author luis2
 */
public class Cliente 
{
    private String dni;
    private String nombre;
    private String direccion;
    private String telefono;
    private ArrayList<Credito> lista_creditos = new ArrayList();

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public ArrayList<Credito> getCreditos() { return lista_creditos; }

    public void agregarCredito(Credito c)
    {
        lista_creditos.add(c);
    }

    public double getDeudaTotal()
    {
        double total = 0;
        for(Credito c : lista_creditos)
        {
            total += c.getMonto();
        }
        return total;
    }

    public void registrarPago(double monto)
    {
        System.out.println("registrando pago de " + monto + " para " + nombre);
    }

    public void mostrarDatos()
    {
        System.out.println("dni: " + dni + " | nombre: " + nombre +
                " | direccion: " + direccion + " | telefono: " + telefono);
    }
}
