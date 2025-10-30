/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoproductos;

import java.util.ArrayList;

/**
 *
 * @author luis2
 */
public class ProductoControlador {
    ArrayList<Producto> lista_productos = new ArrayList<>();

    void agregarProducto(Producto p) {
        lista_productos.add(p);
    }

    void listarProductos() {
        System.out.println("Lista de productos:");
        for (Producto p : lista_productos) {
            p.verProducto();
        }
    }
}
