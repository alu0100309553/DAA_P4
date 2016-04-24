package multi;

import java.util.Locale;

import grasp.Grasp;
import parser.Problem;

public class Test {

  public static void main(String[] args) {
    long inicio = System.currentTimeMillis();
    Multi problem = new Multi("100nodos.txt");
    long fin = System.currentTimeMillis();
    long tiempo = fin - inicio;
    System.out.println(problem.toString());
    System.out.format(Locale.ENGLISH,"%.2f%n", problem.fObj());
    System.out.println(tiempo);
    System.out.println("MultiArranque");
  }

}
