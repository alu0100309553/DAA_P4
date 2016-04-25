package constructivo;

import java.util.Locale;

import parser.Problem;

public class Test {

  public static void main(String[] args) {
    long inicio = System.currentTimeMillis();
    Constructivo problem = new Constructivo("10nodos.txt");
    long fin = System.currentTimeMillis();
    long tiempo = fin - inicio;
    System.out.println(problem.toString());
    System.out.format(Locale.ENGLISH,"%.2f%n", problem.fObj());
    System.out.println(tiempo);
    System.out.println("Constructivo A");
    

  }

}
