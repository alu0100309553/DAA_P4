package constructivo;

import aux.Eval;
import aux.Solucion;
import parser.Problem;

public class Constructivo {
  private Problem problema;
  private Solucion sol;
  public Solucion solP;
  private Eval eval;

  public Constructivo (String filename){
    problema = new Problem (filename);
    problema.read();
    eval = new Eval(problema);
    sol = new Solucion(problema.getNodos());
    init();
    do {
      solP = new Solucion(sol);
      int n = mejornodo();
      if (eval.md(sol, n)>= eval.md(sol)){
        sol.addnodo(n);
      }
    }while (!sol.iguales(solP));
  }

  private void init(){
    double maxDist = 0;
    int tempA = 0; 
    int tempB = 0; 
    for (int i = 0; i < problema.getNodos(); i++) {
      for (int j = i + 1 ; j < sol.getSol().length; j++){
        if (problema.getDist(i, j) >= maxDist){
          maxDist = problema.getDist(i, j);
          tempA = i; 
          tempB = j;
        }
      }
    }
    sol.addnodo(tempA);
    sol.addnodo(tempB);
  }
  
  private int mejornodo(){
    double maxDist = -100000;
    int tempN = 0; 
    for (int i = 0; i < problema.getNodos(); i ++){
      if (!sol.getSol()[i]){
        if (eval.md(sol, i) > maxDist){
          maxDist = eval.md(sol, i);
          tempN = i;
        }
        
      }
    }

    return tempN;
    
  }
  
  public String toString(){
    String aux = "";
    if (sol.getSol()[0]){
      aux += "{" + 1;
    }
    else {
      aux += "{" + 0;
    }
    for (int i = 1; i< problema.getNodos(); i++){
      if (sol.getSol()[i]){
        aux += ", " + 1;
      }else{
        aux += ", " + 0;
      }
    }

    aux += "}";
    return aux;  
  }
  
  public double fObj(){
    return eval.md(sol);
  }
}
