/**
 * Diseño y Análisis de Algoritmos - Práctica 4
 * Algoritmos constructivos y búsquedas por entornos.
 * 3º Grado en Ingeniería Informática - Computación.
 * Rubén Labrador Páez
 * alu0100309553@ull.edu.es
 */

package aux;

//Clase que almacena un par de nodos y la distancia entre ellos, implementa comparable para poder ordenar los objetos que se creen de la clase
public class Distancia implements Comparable<Distancia> {
  public int nodoA;
  public int nodoB;
  public double distancia;
  //Constructor
  public Distancia (int a, int b, double dist){
    nodoA = a;
    nodoB = b;
    distancia = dist;
  }
  //Método para comparar objetos distancia
  @Override
  public int compareTo(Distancia o) {
    if (distancia < o.distancia){
      return 1;
    }else if (distancia > o.distancia){
      return -1;
    }else {
      return 0;
    }
  }

}
