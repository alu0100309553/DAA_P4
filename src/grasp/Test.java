package grasp;

import java.util.Locale;

import constructivo.Constructivo;
import parser.Problem;

public class Test {

  public static void main(String[] args) {
    long inicio = System.currentTimeMillis();
    Grasp problem = new Grasp("10nodos.txt", 2);
    long fin = System.currentTimeMillis();
    long tiempo = fin - inicio;
    
      System.out.println(problem.toString());
      System.out.format(Locale.ENGLISH,"%.2f%n", problem.fObj());
      System.out.println(tiempo);

    System.out.println("Grasp");
    
  

  }

}
