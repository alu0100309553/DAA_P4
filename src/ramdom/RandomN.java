/**
 * Diseño y Análisis de Algoritmos - Práctica 4
 * Algoritmos constructivos y búsquedas por entornos.
 * 3º Grado en Ingeniería Informática - Computación.
 * Rubén Labrador Páez
 * alu0100309553@ull.edu.es
 */

package ramdom;

import java.util.Locale;
import java.util.Random;
//Clase para genera problemas de manera aleatoria
public class RandomN {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int n =100;
    Random rm = new Random();
    System.out.println(n);
    for (int i = 0; i < n; i++){
      for (int j = i+1; j < n; j++){
        System.out.format(Locale.ENGLISH,"%.2f%n", ((rm.nextDouble()*100) -50));
       // System.out.println("" + ((rm.nextDouble()*100) -50));
      }
    }
  }
}
