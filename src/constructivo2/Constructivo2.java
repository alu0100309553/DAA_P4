package constructivo2;

import aux.Eval;
import aux.Solucion;
import parser.Problem;

public class Constructivo2 {
	private Problem problema;
	private Solucion sol;
	private Solucion solP;
	private Eval eval;

	//Constructor de la clase
	public Constructivo2 (String filename){
		problema = new Problem (filename);
		problema.read();
		eval = new Eval(problema);
		sol = new Solucion(problema.getNodos());
		init();											//Inicializar la solución añadiendo todos los nodos
		do {
			solP = new Solucion(sol);					//Copiar la solución actual para compararla al final
			int n = peornodo();							//Buscar el peor nodo para quitarlo de la solución
			if (eval.md(n, sol)>= eval.md(sol)){		//Comprobar si la solución sin el nodo indicado es mejor
				sol.quitarnodo(n);						//Actualizar solución
			}
		}while (!sol.iguales(solP));					//Ejecutar bucle mientras la solución haya mejorado
	}

	//Método para inicializar la solución
	private void init(){
		for (int i = 0; i < problema.getNodos(); i++) {	//Bucle que añade todos los nodos a la solución
			sol.addnodo(i);
		}

	}

	//Método que busca el nodo a quitar de la solución
	private int peornodo(){
		double maxDist = -100000000;
		int tempN = 0; 
		for (int i = 0; i < problema.getNodos(); i ++){	//Bucle que recorre todos los nodos que están en la solución actual y comprueba cual es el peor
			if (sol.getSol()[i]){
				if (eval.md(i, sol) > maxDist){
					maxDist = eval.md(i, sol);
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
