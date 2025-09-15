
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author luis2
 */
public class PracticaBilly 
{
    // Sobrecarga de métodos
    public int sumar(int a, int b) {
        return a + b;
    }

    public double sumar(double a, double b) {
        return a + b;
    }
    public static void main(String[] args) 
    {
         PracticaBilly p = new PracticaBilly();
           try {
            // Ejemplo de sobrecarga
            System.out.println("Suma de enteros: " + p.sumar(5, 10));
            System.out.println("Suma de decimales: " + p.sumar(2.5, 3.1));

            // Ejemplo de colecciones
            ArrayList<String> nombres = new ArrayList<>();
            nombres.add("Luis");
            nombres.add("Ana");
            nombres.add("Diego");

            System.out.println("Lista de nombres:");
            for (String n : nombres) {
                System.out.println("- " + n);
            }

            // Forzar un error con división por cero
            int resultado = 10 / 0;
            System.out.println("Resultado: " + resultado);

        } catch (Exception ex) {
            System.out.println("Se capturó un error: " + ex.getMessage());
        }
    }
    
}
