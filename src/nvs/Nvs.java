package nvs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import nvs.Solucion;
import parser.Problem;

public class Nvs {
  private Problem problema;
  public Solucion sol;
  public Solucion solP;
  private Eval eval;

  public Nvs (String filename){
    problema = new Problem (filename);
    problema.read();
    eval = new Eval(problema);
    sol = new Solucion(problema.getNodos());
    sol = randomGreedy();
    //init();
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
          k +=1;
        }
      } while (k<3);
    }while (!sol.iguales(solP));
   sol = solP;
  }
  
  
  private Solucion agitacion(int k, Solucion sol_){
    Random rm = new Random();
    ArrayList <Solucion> aux = new ArrayList <Solucion>();
    Solucion fin = new Solucion(problema.getNodos());
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
      if (j==k-1){
        fin = auxSol;
      }
    }
    return fin;
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
