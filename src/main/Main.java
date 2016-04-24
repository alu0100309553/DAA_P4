package main;

import java.util.Locale;
import java.util.Scanner;
import constructivo.Constructivo;
import constructivo2.Constructivo2;
import grasp.Grasp;
import multi.Multi;

public class Main {

  public static void main(String[] args) {
    String filename;
    long inicio;
    long fin;
    long tiempo;
    int alg = 0;
    System.out.println("Indique el nombre del archivo:");
    Scanner textint = new Scanner(System.in);
    filename = textint.nextLine();
    System.out.println("Idique el algoritmo a aplicar; \n -1 Constructivo aditivo \n -2 Contructivo sustractivo \n -3 GRASP \n -4 Multi-Arranque \n -5 VNS");
    alg = textint.nextInt();
    
    switch (alg){
    case 1:
      System.out.println("Constructivo Aditivo");
      inicio = System.currentTimeMillis();
      Constructivo problem = new Constructivo(filename);
      fin = System.currentTimeMillis();
      tiempo = fin - inicio;
      System.out.println("Solucion:\n" + problem.toString() + "\nValor objetivo:");
      System.out.format(Locale.ENGLISH,"%.2f%n", problem.fObj());
      System.out.println("Tiempo de CPU: " +  tiempo + "ms");
      break;
    case 2:
      System.out.println("Constructivo Sustractivo");
      inicio = System.currentTimeMillis();
      Constructivo2 problem2 = new Constructivo2(filename);
      fin = System.currentTimeMillis();
      tiempo = fin - inicio;
      System.out.println("Solucion:\n" + problem2.toString() + "\nValor objetivo:");
      System.out.format(Locale.ENGLISH,"%.2f%n", problem2.fObj());
      System.out.println("Tiempo de CPU: " +  tiempo  + "ms");
      break;
    case 3:
      System.out.println("Indique el tamaño máximo de la Lista Restringida de Candidatos:");
      int lrc = textint.nextInt();
      System.out.println("GRASP");
      inicio = System.currentTimeMillis();
      Grasp problem3 = new Grasp(filename, lrc );
      fin = System.currentTimeMillis();
      tiempo = fin - inicio;
      System.out.println("Solucion:\n" + problem3.toString() + "\nValor objetivo:");
      System.out.format(Locale.ENGLISH,"%.2f%n", problem3.fObj());
      System.out.println("Tiempo de CPU: " +  tiempo + "ms");
      break;
    case 4:
      System.out.println("Multi-Arranque");
      inicio = System.currentTimeMillis();
      Multi problem4 = new Multi(filename);
      fin = System.currentTimeMillis();
      tiempo = fin - inicio;
      System.out.println("Solucion:\n" + problem4.toString() + "\nValor objetivo:");
      System.out.format(Locale.ENGLISH,"%.2f%n", problem4.fObj());
      System.out.println("Tiempo de CPU: " +  tiempo + "ms");
      break;
    case 5:
      System.out.println("Indique el valor k máximo:");
      int k = textint.nextInt();
      System.out.println("VNS");
      inicio = System.currentTimeMillis();
      Grasp problem5 = new Grasp(filename, k );
      fin = System.currentTimeMillis();
      tiempo = fin - inicio;
      System.out.println("Solucion:\n" + problem5.toString() + "\nValor objetivo:");
      System.out.format(Locale.ENGLISH,"%.2f%n", problem5.fObj());
      System.out.println("Tiempo de CPU: " +  tiempo + "ms");
      break;
    default:
      System.out.println("Algoritmo no válido");
    }
    
    textint.close();
    
  }

}
