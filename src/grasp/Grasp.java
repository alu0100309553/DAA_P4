package grasp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import grasp.Solucion;
import parser.Problem;

public class Grasp {
  private Problem problema;
  private Solucion sol;
  public Solucion solP;
  private Eval eval;

  public Grasp (String filename){
    problema = new Problem (filename);
    problema.read();
    eval = new Eval(problema);
    sol = new Solucion(problema.getNodos());
    int contador = 0;
    while (contador < 10){
      
    //fase constructiva
      
      
      
    //busqueda local
    
    }
  }
  
  
  private Solucion randomGreedy(){
    Solucion aux = new Solucion(problema.getNodos());
    randominit(aux);
    Solucion auxP;
    do {
      auxP = new Solucion(aux);
      int n = mejornodo();
      if (eval.md(sol, n)> eval.md(sol)){
        sol.addnodo(n);
      }
    }while (!sol.iguales(solP));
    return auxP;
    
    
    
  }
  
  private void randominit(Solucion auxSol){
    Random rm = new Random();
    ArrayList <Distancia> dist = new ArrayList <Distancia>();
    for (int i = 0 ; i < problema.getNodos(); i++){
      for (int j = i+1 ; j < problema.getNodos(); j++){
        dist.add(new Distancia(i, j, problema.getDist(i, j)));
      }
    }
    Collections.sort(dist);
    int aux = rm.nextInt(3);
    auxSol.addnodo(dist.get(aux).nodoA);
    auxSol.addnodo(dist.get(aux).nodoB);
    
  }

  private void init(){
    for (int i = 0; i < problema.getNodos(); i++) {
      sol.addnodo(i);
    }

  }
  
  private int mejornodo(){
    
    //estoy por aqui
    
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
