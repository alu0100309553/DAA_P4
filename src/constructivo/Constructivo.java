/**
 * Diseño y Análisis de Algoritmos - Práctica 4
 * Algoritmos constructivos y búsquedas por entornos.
 * 3º Grado en Ingeniería Informática - Computación.
 * Rubén Labrador Páez
 * alu0100309553@ull.edu.es
 */

package constructivo;

import aux.Eval;
import aux.Solucion;
import parser.Problem;

public class Constructivo {
	private Problem problema;
	private Solucion sol;
	private Solucion solP;
	private Eval eval;

	//Constructor de la clase
	public Constructivo (String filename){
		problema = new Problem (filename);
		problema.read();
		eval = new Eval(problema);
		sol = new Solucion(problema.getNodos());
		init(); 										//Inicializar la solución con la arista de mayor afinidad
		do {
			solP = new Solucion(sol);					//Copiar la mejor solución a solP para compararla al final
			int n = mejornodo();						//Buscar el mejor nodo de los que no están para incluir en la solución
			if (eval.md(sol, n)>= eval.md(sol)){		//Comprobar si la solución añadiendo el nodo es mejor
				sol.addnodo(n);							//Añadir el nodo a la solución
			}
		}while (!sol.iguales(solP));					//Repetir el bucle mientras la solución haya mejorado
	}

	//Método para inicializar la solución
	private void init(){
		double maxDist = 0;
		int tempA = 0; 
		int tempB = 0; 
		for (int i = 0; i < problema.getNodos(); i++) {	//Bucle para buscar la arista con mayor afinidad
			for (int j = i + 1 ; j < problema.getNodos(); j++){
				if (problema.getDist(i, j) >= maxDist){
					maxDist = problema.getDist(i, j);
					tempA = i; 
					tempB = j;
				}
			}
		}
		sol.addnodo(tempA);								//Añadir los dos nodos de la arista con mayor afinidad a la solución
		sol.addnodo(tempB);
	}

	//Método para buscar el mejor nodoa añadir a la solución
	private int mejornodo(){
		double maxDist = -100000;
		int tempN = 0; 
		for (int i = 0; i < problema.getNodos(); i ++){	//Bucle que recorre los nodos que no están en la solución actual y devuelve el mejor a añadir
			if (!sol.getSol()[i]){
				if (eval.md(sol, i) > maxDist){
					maxDist = eval.md(sol, i);
					tempN = i;
				}

			}
		}
		return tempN;
	}

	//Médoto para convertir la solución en un string
	public String toString(){
		String aux = "";
		if (sol.getSol()[0]){
			aux += "{" + 1;
		}
		else {
			aux += "{" + 0;
		}
		for (int i = 1; i< problema.getNodos(); i++){
			if (sol.getSol()[i]){
				aux += ", " + 1;
			}else{
				aux += ", " + 0;
			}
		}
		aux += "}";
		return aux;  
	}

	//Método que devuelve el valor de la función objetivo con la solución actual.
	public double fObj(){
		return eval.md(sol);
	}
}
