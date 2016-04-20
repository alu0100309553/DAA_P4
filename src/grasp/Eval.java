package grasp;

import parser.Problem;

public class Eval {
  private Solucion sol;
  private Problem distancias; 
  
  Eval (Problem distancias_){
    distancias = distancias_;
  }
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
