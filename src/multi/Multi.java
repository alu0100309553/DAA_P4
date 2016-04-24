package multi;

import java.util.ArrayList;
import java.util.Random;
import aux.Solucion;
import aux.Eval;
import parser.Problem;

public class Multi {
  private Problem problema;
  public Solucion sol;
  public Solucion solP;
  private Eval eval;

  public Multi (String filename){
    problema = new Problem (filename);
    problema.read();
    eval = new Eval(problema);
    sol = new Solucion(problema.getNodos());
    sol = randomSolucion();
    for (int i = 0; i < problema.getNodos(); i ++){
      solP = randomSolucion();
      solP = busquedaLocal(solP);
      if (eval.md(solP)> eval.md(sol)){
        sol = solP;
      }
    }
  }
  
  private ArrayList <Solucion> generarVecinos(Solucion sol_) {
    ArrayList <Solucion> aux = new ArrayList <Solucion>();
    for (int i = 0; i < problema.getNodos(); i++){
      Solucion auxSol = new Solucion(sol_);
      if (auxSol.getSol()[i]){
        auxSol.quitarnodo(i);
        aux.add(auxSol);
      }
      else {
        auxSol.addnodo(i);
        aux.add(auxSol);
      }
    }
    return aux;
  }
  
  private Solucion busquedaLocal(Solucion sol_){
    ArrayList <Solucion> listadevecinos = new ArrayList <Solucion>();
    listadevecinos = generarVecinos(sol_);
    Solucion mejor = new Solucion (sol_);
    Solucion inicial = new Solucion (sol_);

    do{
      inicial = mejor;
      listadevecinos = generarVecinos(inicial);
      for (Solucion vecino : listadevecinos){
        if (eval.md(vecino)> eval.md(mejor)){
          mejor = vecino;
        }
      }
    } while (!mejor.iguales(inicial));

    return mejor;
  }

    
    
    
  
  private Solucion randomSolucion(){
    Random rm = new Random();
    Solucion aux = new Solucion(problema.getNodos());
    for (int i = 0; i < problema.getNodos(); i ++){
      aux.getSol()[i]= rm.nextBoolean();
    }
    return aux;
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
