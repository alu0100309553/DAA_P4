/**
 * Diseño y Análisis de Algoritmos - Práctica 4
 * Algoritmos constructivos y búsquedas por entornos.
 * 3º Grado en Ingeniería Informática - Computación.
 * Rubén Labrador Páez
 * alu0100309553@ull.edu.es
 */

package aux;

//Clase que almacena un nodo vinculado a la función objetivo que produce la adición o la substracción del mismo
//Implementa los métodos para poder ordenar nodos
public class Nodo implements Comparable<Nodo> {
  public int nodo;

  public double md;
  
  public Nodo (int nodo_, double md_){
    nodo = nodo_;
    md = md_;
  }

  @Override
  public int compareTo(Nodo o) {
    if (md < o.md){
      return 1;
    }else if (md > o.md){
      return -1;
    }else {
      return 0;
    }
  }
}