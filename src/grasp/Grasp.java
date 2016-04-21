package grasp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import grasp.Solucion;
import parser.Problem;

public class Grasp {
  private Problem problema;
  public Solucion sol;
  public Solucion solP;
  private Eval eval;

  public Grasp (String filename){
    problema = new Problem (filename);
    problema.read();
    eval = new Eval(problema);
    sol = new Solucion(problema.getNodos());
    int contador = 0;
    // while (contador < 10){
    sol = randomGreedy();
    sol = busquedaLocal(sol);

    //fase constructiva



    //busqueda local

    //}
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
    for (Solucion vecino : listadevecinos){
      if (eval.md(vecino)> eval.md(mejor)){
        mejor = vecino;
      }
    }
    if (!mejor.iguales(sol_)){
      mejor = busquedaLocal(mejor);
    }
    return mejor;
  }


  private Solucion randomGreedy(){
    Solucion aux = new Solucion(problema.getNodos());
    randominit(aux);
    Solucion auxP;
    do {
      auxP = new Solucion(aux);
      int n = randommejornodo();
      if (eval.md(aux, n)> eval.md(aux)){
        aux.addnodo(n);
      }
    }while (!aux.iguales(auxP));
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
  
  private int randommejornodo(){
    
    Random rm = new Random();
    ArrayList <Nodo> nodos = new ArrayList <Nodo>();
    for (int i = 0; i < problema.getNodos(); i ++){
      if (!sol.getSol()[i]){
        nodos.add(new Nodo (i, eval.md(sol, i)));
      }
    }
    Collections.sort(nodos);
    int aux = rm.nextInt(3);
    return nodos.get(aux).nodo;
  }
}
