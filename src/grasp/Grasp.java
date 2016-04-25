package grasp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import aux.Distancia;
import aux.Solucion;
import aux.Eval;
import aux.Nodo;
import parser.Problem;

public class Grasp {
	private Problem problema;
	private Solucion sol;
	private Solucion solP;
	private Eval eval;
	private int lrc;

	//Constructor de la clase
	public Grasp (String filename, int lrc_){
		lrc = lrc_;
		problema = new Problem (filename);
		problema.read();
		eval = new Eval(problema);
		sol = new Solucion(problema.getNodos());
		sol = randomGreedy();								//Se inicializa la solución con una solución randomGreedy()
		int contador = 0;
		while (contador < 10){								//Se repite el bucle hasta 10 veces sin mejorar
			solP = randomGreedy();							//Se inicializa la solución provisional con randomGreedy()
			solP = busquedaLocal(solP);						//Se explora la lista de vecinos
			if (eval.md(solP)>eval.md(sol)){				//Si la solucion provisional es mejor actualizar la solución general
				sol = new Solucion(solP);					//Actualizamos la solución
				contador = 0;								//Si mejorarmos se resetea el contador
			}
			else {
				contador++;									//Si no mejoramos aumentamos el contador
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

	//Método para calcular soluciones Greedy aleatorias
	private Solucion randomGreedy(){
		Solucion aux = new Solucion(problema.getNodos());
		randomInit(aux);									//Iniciar de manera aleatoria
		Solucion auxP;
		do {
			auxP = new Solucion(aux);						//Copiar solución actual para comparar al final
			int n = randomMejornodo();						//Buscar el mejor nodo aleatorio para añadir a la sol
			if (eval.md(aux, n)> eval.md(aux)){				//Comprobar si la solución es mejor
				aux.addnodo(n);								//Añadirlo a la solución
			}
		}while (!aux.iguales(auxP));						//Si la solución mejora repetir el bucle
		return auxP;



	}
	
	//Método que inicializa la solución de manera aleatoria para el método randomGreedy
	private void randomInit(Solucion auxSol){
		Random rm = new Random();
		ArrayList <Distancia> dist = new ArrayList <Distancia>();
		for (int i = 0 ; i < problema.getNodos(); i++){		//Se añade cada arista con su par de nodos en en array
			for (int j = i+1 ; j < problema.getNodos(); j++){
				dist.add(new Distancia(i, j, problema.getDist(i, j)));
			}
		}
		Collections.sort(dist);								//Se ordena el array por el valor de la distancia
		int rLimit = Math.min(lrc, dist.size());
		int aux = rm.nextInt(rLimit);						//Se selecciona una de los LRC mejor arista
		auxSol.addnodo(dist.get(aux).nodoA);				//Se añaden los nodos impliados a la solución
		auxSol.addnodo(dist.get(aux).nodoB);

	}
	
	//Método que devuelve uno de los mejores nodos a añadir a la solución para el método randomGreedy
	private int randomMejornodo(){
		Random rm = new Random();
		ArrayList <Nodo> nodos = new ArrayList <Nodo>();
		for (int i = 0; i < problema.getNodos(); i ++){		//Se añade todos los nodos que no están en la solución a un arrayList junto con el valor objetivo al añadirlo 
			if (!sol.getSol()[i]){							
				nodos.add(new Nodo (i, eval.md(sol, i)));
			}
		}
		Collections.sort(nodos);							//Se ordena el array de nodos
		if (nodos.size()==0){								//Condicional para evitar errores si el array está vacio
			return 0;
		}else{
			int rLimit = Math.min(lrc, nodos.size());		
			int aux = rm.nextInt(rLimit);	
			return nodos.get(aux).nodo;						//Se retorna uno de LRC mejores nodos
		}
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
