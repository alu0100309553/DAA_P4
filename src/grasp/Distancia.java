package grasp;

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
    return ((int)(distancia*100))-((int)(o.distancia*100));
  }

}
