package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Problem {
  private BufferedReader reader;
  private int nodos;
  private double [][] distancias;
  
  Problem(String filename){
    try{
      reader = new BufferedReader( new FileReader(filename));
    }
    catch (Exception e){
      System.err.println("File not foun or arguments wrong");
      System.err.println("try java ArrayRank inputtextfile");
    }
  }
  
  public void read(){
    try {
      while (reader.ready()) {
        String data;
        data = reader.readLine();
        String [] token = data.split("[\\D]");
        nodos = Integer.parseInt(token[0]);
        distancias = new double [nodos][nodos];
        for (int j = 0; j < nodos; ++j){
          for (int k = j+1; k < nodos; k++){
            data = reader.readLine();
            token = data.split("[\\s]");
            distancias[j][k] = distancias[k][j] = Double.parseDouble(token[0]);
          }
        }
      }
    } catch (IOException e) {
      System.err.println("File error");
    }
    for (int i = 0; i < nodos; i++){
      distancias[i][i] = 0;
    }
    System.out.println(distancias + "" + nodos);
  }
  

}
