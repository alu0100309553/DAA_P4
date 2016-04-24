package vns;

import java.util.Locale;

import multi.Multi;
import parser.Problem;

public class Test {

  public static void main(String[] args) {
    long inicio = System.currentTimeMillis();
    VNS problem = new VNS("100nodos.txt", 3);
    long fin = System.currentTimeMillis();
    long tiempo = fin - inicio;
    System.out.println(problem.toString());
    System.out.format(Locale.ENGLISH,"%.2f%n", problem.fObj());
    System.out.println(tiempo);
    System.out.println("NVS");
 
    
  }

}
