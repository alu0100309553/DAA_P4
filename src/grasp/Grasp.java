package grasp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import aux.Distancia;
import aux.Solucion;
import aux.Eval;
import aux.Nodo;
import parser.Problem;

public class Grasp {
  private Problem problema;
  public Solucion sol;
  public Solucion solP;
  private Eval eval;
  private int lrc;

  public Grasp (String filename, int lrc_){
    lrc = lrc_;
    problema = new Problem (filename);
    problema.read();
    eval = new Eval(problema);
    sol = new Solucion(problema.getNodos());
    sol = randomGreedy();
    int contador = 0;
    while (contador < 10){
      solP = randomGreedy();
      solP = busquedaLocal(solP);
      if (eval.md(solP)>eval.md(sol)){
        sol = new Solucion(solP);
        contador = 0;
      }
      else {
        contador++;
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


  private Solucion randomGreedy(){
    Solucion aux = new Solucion(problema.getNodos());
    randomInit(aux);
    Solucion auxP;
    do {
      auxP = new Solucion(aux);
      int n = randomMejornodo();
      if (eval.md(aux, n)> eval.md(aux)){
        aux.addnodo(n);
      }
    }while (!aux.iguales(auxP));
    return auxP;
    
    
    
  }
  
  private void randomInit(Solucion auxSol){
    Random rm = new Random();
    ArrayList <Distancia> dist = new ArrayList <Distancia>();
    for (int i = 0 ; i < problema.getNodos(); i++){
      for (int j = i+1 ; j < problema.getNodos(); j++){
        dist.add(new Distancia(i, j, problema.getDist(i, j)));
      }
    }
    Collections.sort(dist);
    int rLimit = Math.min(lrc, dist.size());
    int aux = rm.nextInt(rLimit);
    auxSol.addnodo(dist.get(aux).nodoA);
    auxSol.addnodo(dist.get(aux).nodoB);
    
  }
  
  private int randomMejornodo(){
    Random rm = new Random();
    ArrayList <Nodo> nodos = new ArrayList <Nodo>();
    for (int i = 0; i < problema.getNodos(); i ++){
      if (!sol.getSol()[i]){
        nodos.add(new Nodo (i, eval.md(sol, i)));
      }
    }
    Collections.sort(nodos);
    if (nodos.size()==0){
      return 0;
    }else{
      int rLimit = Math.min(lrc, nodos.size());
      int aux = rm.nextInt(rLimit);
      return nodos.get(aux).nodo;
    }
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
