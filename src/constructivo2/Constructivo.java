package constructivo2;

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
      int n = peornodo();
      if (eval.md(n, sol)> eval.md(sol)){
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
}
