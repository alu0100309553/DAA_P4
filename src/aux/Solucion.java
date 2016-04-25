/**
 * Diseño y Análisis de Algoritmos - Práctica 4
 * Algoritmos constructivos y búsquedas por entornos.
 * 3º Grado en Ingeniería Informática - Computación.
 * Rubén Labrador Páez
 * alu0100309553@ull.edu.es
 */

package aux;

//Clase que almacena soluciones en forma de vector booleano, dispone de métodos para añadir o quitar nodos así como para comparar soluciones
public class Solucion {
  private boolean[] sol;

  public Solucion(int N) {
    sol = new boolean[N];
  }

  Solucion(Solucion sol_, int nodo) {
    sol = new boolean[sol_.sol.length];
    for (int i = 0; i < sol.length; i++){
      sol[i] = sol_.sol[i];
    }
    sol[nodo] = true;
  }
  
  public Solucion(Solucion sol_) {
    sol = new boolean[sol_.sol.length];
    for (int i = 0; i < sol.length; i++){
      sol[i] = sol_.sol[i];
    }
  }

  public void addnodo(int nodo) {
    sol[nodo] = true;
  }

  public void quitarnodo(int nodo) {
    sol[nodo] = false;
  }
  
  public boolean [] getSol(){
    return sol;
  }
  
  public boolean iguales (Solucion sol_){
    boolean iguales = true;
    for (int i = 0; i < sol_.getSol().length ; i ++)
      if (sol_.getSol()[i] != sol[i] && iguales){
        iguales = false;
      }
    return iguales;
  }
}
