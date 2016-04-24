package aux;

public class Distancia implements Comparable<Distancia> {
  public int nodoA;
  public int nodoB;
  public double distancia;
  
  public Distancia (int a, int b, double dist){
    nodoA = a;
    nodoB = b;
    distancia = dist;
  }

  @Override
  public int compareTo(Distancia o) {
    if (distancia < o.distancia){
      return 1;
    }else if (distancia > o.distancia){
      return -1;
    }else {
      return 0;
    }
  }

}
