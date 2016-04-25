/**
 * Diseño y Análisis de Algoritmos - Práctica 4
 * Algoritmos constructivos y búsquedas por entornos.
 * 3º Grado en Ingeniería Informática - Computación.
 * Rubén Labrador Páez
 * alu0100309553@ull.edu.es
 */

package aux;

import parser.Problem;
//Clase eval, clase que se encarga de evaluar soluciones bajo diferencest condiciones.
public class Eval {
  private Solucion sol;
  private Problem distancias; 
  
  //Contructor que inizializa la clase con las distancias del problema
  public Eval (Problem distancias_){
    distancias = distancias_;
  }
  
  //Método que evalua la solución
  public Double md (Solucion sol_){
    sol = sol_;
    int contador = 0;
    double suma = 0;

    for (int i = 0; i < sol.getSol().length; i++){
      if (sol.getSol()[i]){
        contador++;
        for (int j = i + 1 ; j < sol.getSol().length; j++){
          if (sol.getSol()[j]){
            suma += distancias.getDist(i, j);
          }
        }
      }
    }
    return suma/(double)contador;
  }
  
  //Método que evalua la solución añadiendo un nodo que no pertenece a la misma
  public Double md (Solucion sol_, int n){
    sol = sol_;
    int contador = 0;
    double suma = 0;

    for (int i = 0; i < sol.getSol().length; i++){
      if (sol.getSol()[i] | i==n){
        contador++;
        for (int j = i + 1 ; j < sol.getSol().length; j++){
          if (sol.getSol()[j] | j == n){
            suma += distancias.getDist(i, j);
          }
        }
      }
    }
    return suma/(double)contador;
  }
  
  //Método que evalua la solución quitando un nodo que pertenece a la misma
  public Double md (int n, Solucion sol_){
    sol = sol_;
    int contador = 0;
    double suma = 0;

    for (int i = 0; i < sol.getSol().length; i++){
      if (sol.getSol()[i] && i!=n){
        contador++;
        for (int j = i + 1 ; j < sol.getSol().length; j++){
          if (sol.getSol()[j] && j != n){
            suma += distancias.getDist(i, j);
          }
        }
      }
    }
    return suma/(double)contador;
  }


}
