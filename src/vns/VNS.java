package vns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import aux.Nodo;
import aux.Solucion;
import aux.Distancia;
import aux.Eval;
import parser.Problem;

public class VNS {
  private Problem problema;
  public Solucion sol;
  public Solucion solP;
  private Eval eval;
  private int kmax;

  public VNS (String filename, int kmax_){
    kmax = kmax_;
    problema = new Problem (filename);
    problema.read();
    eval = new Eval(problema);
    sol = new Solucion(problema.getNodos());
    //sol = randomGreedy();
    sol = randomSolucion();
    solP = new Solucion(sol);
    int k = 0;
    
    do {
      sol = new Solucion(solP);
      k = 1;
      Solucion aux;
      do {
        aux = agitacion(k, solP);
        aux = busquedaLocal(aux);
        if (eval.md(aux)> eval.md(solP)){
          solP = new Solucion(aux);
          k = 1;
        }else{
          k ++;
        }
      } while (k<=kmax);
    }while (!sol.iguales(solP));
   sol = solP;
  }
  
  private Solucion agitacion(int k, Solucion sol_){
    Random rm = new Random();
    ArrayList <Solucion> aux = new ArrayList <Solucion>();
    Solucion auxSol = new Solucion (sol_);
    for (int j = 0; j<k; j++){
      
      for (int i = 0; i < problema.getNodos(); i++){
        if (auxSol.getSol()[i]){
          auxSol.quitarnodo(i);
          aux.add(auxSol);
        }
        else {
          auxSol.addnodo(i);
          aux.add(auxSol);
        }
      }
      auxSol = aux.get(rm.nextInt(aux.size()));
    }
    return auxSol;
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
  
  private int randommejornodo(){
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
      int rLimit = Math.min(3, nodos.size());
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
  
  private Solucion randomSolucion(){
    Random rm = new Random();
    Solucion aux = new Solucion(problema.getNodos());
    for (int i = 0; i < problema.getNodos(); i ++){
      aux.getSol()[i]= rm.nextBoolean();
    }
    return aux;
  }
  
}
