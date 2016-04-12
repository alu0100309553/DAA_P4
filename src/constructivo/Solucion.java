package constructivo;

public class Solucion {
  private boolean[] sol;

  Solucion(int N, int nodoA, int nodoB) {
    sol = new boolean[N];
    for (int i = 0; i < sol.length; i++) {
      if ((i == nodoA) | (i == nodoB)) {
        sol[i] = true;
      } else {
        sol[i] = false;
      }
    }
  }

  Solucion(Solucion sol_, int nodo) {
    sol = sol_.sol;
    sol[nodo] = true;
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
}
