package multi;

public class Solucion {
  private boolean[] sol;

  Solucion(int N) {
    sol = new boolean[N];
    /**
    for (int i = 0; i < sol.length; i++) {
      if ((i == nodoA) | (i == nodoB)) {
        sol[i] = true;
      } else {
        sol[i] = false;
      }
    }
    **/
  }

  Solucion(Solucion sol_, int nodo) {
    sol = new boolean[sol_.sol.length];
    for (int i = 0; i < sol.length; i++){
      sol[i] = sol_.sol[i];
    }
    sol[nodo] = true;
  }
  
  Solucion(Solucion sol_) {
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
