package constructivo2;

import aux.Eval;
import aux.Solucion;
import parser.Problem;

public class Constructivo2 {
  private Problem problema;
  private Solucion sol;
  public Solucion solP;
  private Eval eval;

  public Constructivo2 (String filename){
    problema = new Problem (filename);
    problema.read();
    eval = new Eval(problema);
    sol = new Solucion(problema.getNodos());
    init();
    do {
      solP = new Solucion(sol);
      int n = peornodo();
      if (eval.md(n, sol)>= eval.md(sol)){
        sol.quitarnodo(n);
      }
    }while (!sol.iguales(solP));
  }

  private void init(){
    for (int i = 0; i < problema.getNodos(); i++) {
      sol.addnodo(i);
    }

  }
  
  private int peornodo(){
    double maxDist = -100000000;
    int tempN = 0; 
    for (int i = 0; i < problema.getNodos(); i ++){
      if (sol.getSol()[i]){
          if (eval.md(i, sol) > maxDist){
            maxDist = eval.md(i, sol);
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
