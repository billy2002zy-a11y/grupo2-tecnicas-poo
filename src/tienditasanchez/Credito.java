/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienditasanchez;

/**
 *
 * @author luis2
 */
public class Credito 
{
    private String codigoProducto;
    private String nombreProducto;
    private double monto;

    public void setCodigoProducto(String codigoProducto)
    {
        this.codigoProducto = codigoProducto;
    }

    public void setNombreProducto(String nombreProducto)
    {
        this.nombreProducto = nombreProducto;
    }

    public void setMonto(double monto)
    {
        this.monto = monto;
    }

    public String getCodigoProducto() { return codigoProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public double getMonto() { return monto; }

    public void mostrarCredito()
    {
        System.out.println("producto: " + nombreProducto + " | codigo: " + codigoProducto + " | monto: " + monto);
    }
}
