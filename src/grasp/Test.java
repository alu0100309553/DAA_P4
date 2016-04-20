package grasp;

import parser.Problem;

public class Test {

  public static void main(String[] args) {
    Grasp problem = new Grasp("datos2.txt");
    for (int i = 0; i<problem.solP.getSol().length; i++){
      System.out.println(problem.solP.getSol()[i]);
    }

    System.out.println("hola");
    

  }

}
