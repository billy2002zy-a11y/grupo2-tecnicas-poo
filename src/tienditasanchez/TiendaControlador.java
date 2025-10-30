/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienditasanchez;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author luis2
 */
public class TiendaControlador 
{
    ArrayList<Producto> lista_productos = new ArrayList();
    ArrayList<Cliente> lista_clientes = new ArrayList();

    Scanner sc = new Scanner(System.in);

    public void menu()
    {
        String opcion = "";
        while(!opcion.equals("7"))
        {
            System.out.println("=======================================");
            System.out.println("  sistema de la tiendita sanchez");
            System.out.println("=======================================");
            System.out.println("1. registrar producto");
            System.out.println("2. listar productos");
            System.out.println("3. vender producto");
            System.out.println("4. ver lista de fiados");
            System.out.println("5. pagar cuenta");
            System.out.println("6. reporte valor total de inventario");
            System.out.println("7. salir");
            System.out.println("=======================================");
            System.out.print("elija una opcion: ");
            opcion = sc.nextLine();

            if(opcion.equals("1"))
            {
                registrarProducto();
            }
            else if(opcion.equals("2"))
            {
                listarProductos();
            }
            else if(opcion.equals("3"))
            {
                venderProducto();
            }
            else if(opcion.equals("4"))
            {
                verFiados();
            }
            else if(opcion.equals("5"))
            {
                pagarCuenta();
            }
            else if(opcion.equals("6"))
            {
                reporteInventario();
            }
            else if(opcion.equals("7"))
            {
                System.out.println("saliendo del sistema...");
            }
            else
            {
                System.out.println("opcion no valida");
            }
            limpiarPantalla();
        }
    }

    // ==============================
    // REGISTRAR PRODUCTO
    // ==============================
    public void registrarProducto()
    {
        Producto p = new Producto();

        System.out.print("ingrese codigo del producto: ");
        String codigo = sc.nextLine();
        if(buscarProducto(codigo) != null)
        {
            System.out.println("error: el codigo ya existe");
            return;
        }
        p.setCodigo(codigo);

        System.out.print("ingrese nombre del producto: ");
        p.setNombre(sc.nextLine());

        System.out.print("ingrese precio: ");
        p.setPrecio(Double.parseDouble(sc.nextLine()));

        System.out.print("ingrese stock: ");
        p.setStock(Integer.parseInt(sc.nextLine()));

        lista_productos.add(p);
        System.out.println("producto registrado correctamente");
    }

    // ==============================
    // LISTAR PRODUCTOS
    // ==============================
    public void listarProductos()
    {
        System.out.println("lista de productos disponibles:");
        for(Producto p : lista_productos)
        {
            p.mostrarDatos();
        }
    }

    // ==============================
    // VENDER PRODUCTO
    // ==============================
    public void venderProducto()
    {
        System.out.print("buscar producto por (1-codigo / 2-nombre): ");
        String tipoBusqueda = sc.nextLine();
        Producto prod = null;

        if(tipoBusqueda.equals("1"))
        {
            System.out.print("ingrese codigo del producto: ");
            prod = buscarProducto(sc.nextLine());
        }
        else if(tipoBusqueda.equals("2"))
        {
            System.out.print("ingrese nombre del producto: ");
            prod = buscarProductoPorNombre(sc.nextLine());
        }

        if(prod == null)
        {
            System.out.println("producto no encontrado");
            return;
        }

        System.out.print("ingrese cantidad a comprar: ");
        int cant = Integer.parseInt(sc.nextLine());

        if(cant > prod.getStock())
        {
            System.out.println("stock insuficiente");
            return;
        }

        System.out.print("forma de pago (1-efectivo / 2-fiado): ");
        String tipoPago = sc.nextLine();

        if(tipoPago.equals("1"))
        {
            System.out.print("ingrese monto pagado: ");
            double monto = Double.parseDouble(sc.nextLine());

            Venta v = new Venta();
            v.setProducto(prod);
            v.setCantidad(cant);
            v.setMontoPagado(monto);
            v.mostrarVenta();

            prod.setStock(prod.getStock() - cant);
        }
        else if(tipoPago.equals("2"))
        {
            System.out.print("ingrese dni del cliente: ");
            String dni = sc.nextLine();
            Cliente cli = buscarCliente(dni);

            if(cli == null)
            {
                cli = new Cliente();
                cli.setDni(dni);
                System.out.print("ingrese nombre del cliente: ");
                cli.setNombre(sc.nextLine());
                System.out.print("ingrese direccion: ");
                cli.setDireccion(sc.nextLine());
                System.out.print("ingrese telefono: ");
                cli.setTelefono(sc.nextLine());
                lista_clientes.add(cli);
            }

            Credito c = new Credito();
            c.setCodigoProducto(prod.getCodigo());
            c.setNombreProducto(prod.getNombre());
            c.setMonto(prod.getPrecio() * cant);
            cli.agregarCredito(c);

            prod.setStock(prod.getStock() - cant);
            System.out.println("venta fiada registrada correctamente");
        }
        else
        {
            System.out.println("opcion no valida");
        }
    }

    // ==============================
    // VER FIADOS
    // ==============================
    public void verFiados()
    {
        System.out.println("lista de clientes con productos fiados:");
        for(Cliente cli : lista_clientes)
        {
            if(cli.getDeudaTotal() > 0)
            {
                cli.mostrarDatos();
                System.out.println("total adeudado: " + cli.getDeudaTotal());
                for(Credito c : cli.getCreditos())
                {
                    c.mostrarCredito();
                }
                System.out.println("--------------------------------------");
            }
        }
    }

    // ==============================
    // PAGAR CUENTA
    // ==============================
    public void pagarCuenta()
    {
        System.out.print("ingrese dni del cliente: ");
        String dni = sc.nextLine();
        Cliente cli = buscarCliente(dni);

        if(cli == null)
        {
            System.out.println("cliente no encontrado");
            return;
        }

        double deuda = cli.getDeudaTotal();
        if(deuda == 0)
        {
            System.out.println("no tiene deuda pendiente");
            return;
        }

        System.out.println("deuda total: " + deuda);
        System.out.print("ingrese monto a pagar: ");
        double montoPago = Double.parseDouble(sc.nextLine());

        if(montoPago >= deuda)
        {
            cli.registrarPago(deuda);
            cli.getCreditos().clear();
            System.out.println("cuenta cancelada correctamente");
        }
        else
        {
            System.out.println("monto insuficiente, pago no completado");
        }
    }

    // ==============================
    // REPORTE INVENTARIO
    // ==============================
    public void reporteInventario()
    {
        double total = 0;
        for(Producto p : lista_productos)
        {
            total += (p.getPrecio() * p.getStock());
        }
        System.out.println("valor total del inventario: " + total);
    }

    // ==============================
    // BUSCAR PRODUCTO / CLIENTE
    // ==============================
    public Producto buscarProducto(String codigo)
    {
        for(Producto p : lista_productos)
        {
            if(p.getCodigo().equals(codigo))
            {
                return p;
            }
        }
        return null;
    }

    public Producto buscarProductoPorNombre(String nombre)
    {
        for(Producto p : lista_productos)
        {
            if(p.getNombre().equalsIgnoreCase(nombre))
            {
                return p;
            }
        }
        return null;
    }

    public Cliente buscarCliente(String dni)
    {
        for(Cliente c : lista_clientes)
        {
            if(c.getDni().equals(dni))
            {
                return c;
            }
        }
        return null;
    }

    // ==============================
    // LIMPIAR PANTALLA (SIMULADO)
    // ==============================
    public void limpiarPantalla()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }
}
