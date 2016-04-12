package constructivo;

public class Eval {
  private Solucion sol;
  private int [][] distancias; 
  
  Eval (int [][] distancias_){
    distancias = distancias_;
  }
  public Double md (Solucion sol_){
    sol = sol_;
    int contador = 0;
    double suma = 0;
    
    for (int i = 0; i < sol.getSol().length; i++){
      if (sol.getSol()[1]){
        contador++;  //me quede por aqui
      }
      
    }
    
    return suma; //coregir esto
    
    
  }

  
}
