package grasp;

import parser.Problem;

public class Test {

  public static void main(String[] args) {
    Grasp problem = new Grasp("datos3.txt");
    for (int i = 0; i<problem.sol.getSol().length; i++){
      System.out.println(problem.sol.getSol()[i]);
    }

    System.out.println("hola");
    

  }

}
