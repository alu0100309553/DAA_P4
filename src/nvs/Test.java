package nvs;

import parser.Problem;

public class Test {

  public static void main(String[] args) {
    Nvs problem = new Nvs("datos2.txt");
    for (int i = 0; i<problem.sol.getSol().length; i++){
      System.out.println(problem.sol.getSol()[i]);
    }
    System.out.println("hola");
  }

}
