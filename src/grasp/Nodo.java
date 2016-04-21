package grasp;

public class Nodo implements Comparable<Nodo> {
  public int nodo;

  public double md;
  
  public Nodo (int nodo_, double md_){
    nodo = nodo_;
    md = md_;
  }

  @Override
  public int compareTo(Nodo o) {
    if (md > o.md){
      return 1;
    }else if (md < o.md){
      return -1;
    }else {
      return 0;
    }
  }
}