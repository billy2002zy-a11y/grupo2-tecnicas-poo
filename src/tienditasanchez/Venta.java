/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienditasanchez;

/**
 *
 * @author luis2
 */
public class Venta 
{
    private Producto producto;
    private int cantidad;
    private double montoPagado;

    public void setProducto(Producto producto)
    {
        this.producto = producto;
    }

    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }

    public void setMontoPagado(double montoPagado)
    {
        this.montoPagado = montoPagado;
    }

    public void mostrarVenta()
    {
        double total = producto.getPrecio() * cantidad;
        double cambio = montoPagado - total;

        System.out.println("venta registrada:");
        System.out.println("producto: " + producto.getNombre());
        System.out.println("cantidad: " + cantidad);
        System.out.println("total a pagar: " + total);
        System.out.println("monto pagado: " + montoPagado);
        System.out.println("cambio: " + cambio);
    }
}
