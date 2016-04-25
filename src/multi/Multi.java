package multi;

import java.util.ArrayList;
import java.util.Random;
import aux.Solucion;
import aux.Eval;
import parser.Problem;

public class Multi {
	private Problem problema;
	private Solucion sol;
	private Solucion solP;
	private Eval eval;

	//Constructor de la clase
	public Multi (String filename){
		problema = new Problem (filename);
		problema.read();
		eval = new Eval(problema);
		sol = new Solucion(problema.getNodos());
		sol = randomSolucion();								//Inicializar la solución con una solución aleatoria
		for (int i = 0; i < problema.getNodos(); i ++){		//Ejecutar N veces el bucle (N tamaño del problema)
			solP = randomSolucion();						//Buscar una solución aleatoria
			solP = busquedaLocal(solP);						//Búsqueda local de la solción
			if (eval.md(solP)> eval.md(sol)){				//Si mejora la solucón global actualizarla
				sol = solP;
			}
		}
	}

	//Método para general los vecinos dada una solución
	private ArrayList <Solucion> generarVecinos(Solucion sol_) {
		ArrayList <Solucion> aux = new ArrayList <Solucion>();
		for (int i = 0; i < problema.getNodos(); i++){		//Se recorren todos los nodos de la solución actual
			Solucion auxSol = new Solucion(sol_);			//Se copia la solución pasada
			if (auxSol.getSol()[i]){						//Para cada nodo se le cambia su estado y se almacena la solución
				auxSol.quitarnodo(i);
				aux.add(auxSol);
			}
			else {
				auxSol.addnodo(i);
				aux.add(auxSol);
			}
		}
		return aux;
	}

	//Método que realiza búsqueda local y devuelve la solución encontrada
	private Solucion busquedaLocal(Solucion sol_){
		ArrayList <Solucion> listadevecinos = new ArrayList <Solucion>();
		Solucion mejor = new Solucion (sol_);				
		Solucion inicial = new Solucion (sol_);

		do{
			inicial = mejor;								//Se igual el inicial al mejor para comparar al final
			listadevecinos = generarVecinos(inicial);		//Generar vecinos
			for (Solucion vecino : listadevecinos){			//Recorrer la lista de vecinos
				if (eval.md(vecino)> eval.md(mejor)){		//Comprobar si el vecino es mejor que la solución actual
					mejor = vecino;							//Actualizar la solución
				}
			}
		} while (!mejor.iguales(inicial));					//Si la solución ha mejorado repetir el bucle
		return mejor;
	}

	
	//Método que genera una solución aletatoria
	private Solucion randomSolucion(){
		Random rm = new Random();
		Solucion aux = new Solucion(problema.getNodos());
		for (int i = 0; i < problema.getNodos(); i ++){		//Se recorre todos los nodos de la solución añadiendolos o quitándolos de manera aleatoria
			aux.getSol()[i]= rm.nextBoolean();
		}
		return aux;
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
