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
        guardarProductosEnArchivo();
        System.out.println("producto registrado correctamente");
    }

    
    public void listarProductos()
    {
        System.out.println("lista de productos disponibles:");
        for(Producto p : lista_productos)
        {
            p.mostrarDatos();
        }
    }

    
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
            guardarCreditosEnArchivo();
        }
        else
        {
            System.out.println("opcion no valida");
        }
    }

    
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
            guardarCreditosEnArchivo();
        }
        else
        {
            System.out.println("monto insuficiente, pago no completado");
        }
    }

    
    public void reporteInventario()
    {
        double total = 0;
        try
        {
            java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter("reporte_inventario.txt", true));

            java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String fecha = dtf.format(java.time.LocalDateTime.now());

            writer.println("=========================================");
            writer.println("REPORTE DE INVENTARIO - TIENDITA SANCHEZ");
            writer.println("Fecha: " + fecha);
            writer.println("-----------------------------------------");

            for(Producto p : lista_productos)
            {
                double subtotal = p.getPrecio() * p.getStock();
                total += subtotal;
                writer.println("Codigo: " + p.getCodigo() + " | Nombre: " + p.getNombre() +
                        " | Precio: " + p.getPrecio() + " | Stock: " + p.getStock() +
                        " | Subtotal: " + subtotal);
            }

            writer.println("-----------------------------------------");
            writer.println("Valor total del inventario: " + total);
            writer.println("=========================================");
            writer.println();
            writer.close();

            System.out.println("reporte de inventario guardado correctamente");
        }
        catch(Exception e)
        {
            System.out.println("error al guardar reporte");
        }
    }

    
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

    
    public void limpiarPantalla()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }

    public void guardarProductosEnArchivo()
    {
        try
        {
            java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter("productos.txt"));
            for(Producto p : lista_productos)
            {
                writer.println("-----------------------------------------");
                writer.println("Codigo: " + p.getCodigo());
                writer.println("Nombre: " + p.getNombre());
                writer.println("Precio: " + p.getPrecio());
                writer.println("Stock: " + p.getStock());
                writer.println("Fecha de registro: " + p.getFechaRegistro());
                writer.println("-----------------------------------------");
                writer.println();
            }
            writer.close();
            System.out.println("productos guardados correctamente en productos.txt");
        }
        catch(Exception e)
        {
            System.out.println("error al guardar productos");
        }
    }

    public void guardarCreditosEnArchivo()
    {
        try
        {
            java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter("creditos.txt"));
            for(Cliente cli : lista_clientes)
            {
                if(cli.getDeudaTotal() > 0)
                {
                    for(Credito c : cli.getCreditos())
                    {
                        writer.println("-----------------------------------------");
                        writer.println("DNI: " + cli.getDni());
                        writer.println("Nombre: " + cli.getNombre());
                        writer.println("Direccion: " + cli.getDireccion());
                        writer.println("Telefono: " + cli.getTelefono());
                        writer.println("Producto: " + c.getNombreProducto());
                        writer.println("Codigo producto: " + c.getCodigoProducto());
                        writer.println("Monto fiado: " + c.getMonto());
                        writer.println("-----------------------------------------");
                        writer.println();
                    }
                }
            }
            writer.close();
            System.out.println("creditos guardados correctamente en creditos.txt");
        }
        catch(Exception e)
        {
            System.out.println("error al guardar creditos");
        }
    }

    public void cargarProductosDesdeArchivo()
    {
        try
        {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("productos.txt"));
            String linea;
            while((linea = reader.readLine()) != null)
            {
                String[] partes = linea.split(";");
                Producto p = new Producto();
                p.setCodigo(partes[0]);
                p.setNombre(partes[1]);
                p.setPrecio(Double.parseDouble(partes[2]));
                p.setStock(Integer.parseInt(partes[3]));
                lista_productos.add(p);
            }
            reader.close();
            System.out.println("productos cargados desde archivo");
        }
        catch(Exception e)
        {
            System.out.println("no se encontraron productos guardados");
        }
    }
}