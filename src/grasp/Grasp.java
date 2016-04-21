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
    // while (contador < 10){
    sol = randomGreedy();

    //fase constructiva



    //busqueda local

    //}
  }
  
  private ArrayList <Solucion> generarVecinos() {
    return null;
  }
  
  private Solucion busquedaLocal(Solucion sol_){
    ArrayList <Solucion> listadevecinos = new ArrayList <Solucion>();
    listadevecinos = generarVecinos();
    for (Solucion vecino : listadevecinos){
      
    }
    //lista de vecinos
    //Comprobar si hay mejor
    //if existe mejor busquedalocal de mejor
    //return mejor
    return sol_;
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
