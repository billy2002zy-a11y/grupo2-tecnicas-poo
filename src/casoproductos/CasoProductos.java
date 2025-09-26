/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package casoproductos;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author luis2
 */
public class CasoProductos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        ProductoControlador controla = new ProductoControlador();
        String rpta = "si";

        while (rpta.equalsIgnoreCase("si")) {
            Producto p = new Producto();

            System.out.println("Ingrese el codigo del producto (4 caracteres):");
            p.setCodigo(sc.nextLine());

            System.out.println("Ingrese el nombre del producto:");
            p.setNombre(sc.nextLine());

            try {
                System.out.println("Ingrese el precio del producto:");
                p.setPrecio(sc.nextDouble());

                System.out.println("Ingrese el stock del producto:");
                p.setStock(sc.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un numero válido.");
                sc.nextLine(); 
                continue; 
            }
            sc.nextLine(); 

            controla.agregarProducto(p);

            System.out.println("¿Desea ingresar otro producto? (si/no)");
            rpta = sc.nextLine();
        }

        controla.listarProductos();
    }
    
}
