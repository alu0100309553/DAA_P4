/**
 * Diseño y Análisis de Algoritmos - Práctica 4
 * Algoritmos constructivos y búsquedas por entornos.
 * 3º Grado en Ingeniería Informática - Computación.
 * Rubén Labrador Páez
 * alu0100309553@ull.edu.es
 */

package vns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import aux.Nodo;
import aux.Solucion;
import aux.Distancia;
import aux.Eval;
import parser.Problem;

public class VNS {
	private Problem problema;
	public Solucion sol;
	public Solucion solP;
	private Eval eval;
	private int kmax;

	//Constructor de la clase
	public VNS (String filename, int kmax_){
		kmax = kmax_;
		problema = new Problem (filename);
		problema.read();
		eval = new Eval(problema);
		sol = new Solucion(problema.getNodos());
		sol = randomSolucion();									//Inicializar sol con una solución aleatoria
		solP = new Solucion(sol);
		int k = 0;
		do {
			sol = new Solucion(solP);							//Actualizar sol con solP para comparar al fina del bucle
			k = 1;                                //Inicializar k con 1
			Solucion aux;
			do {
				aux = agitacion(k, solP);           //Seleccionar un candidatos al azar de entre los vecinos según k
				aux = busquedaLocal(aux);           //Búsqueda Lical sobre el candidato
				if (eval.md(aux)> eval.md(solP)){   //Si la solución es mejor actualizar solP
					solP = new Solucion(aux);          
					k = 1;
				}else{                              //Si no aumentar k 
					k ++;
				}
			} while (k<=kmax);                     //Repetir el bucle mientras no se haya superado k max
		}while (!sol.iguales(solP));             //Repetir el bucle mientras las solución mejores
	}

	//Método que devuelve uno de los vecinos de manera aleatoria, el vecino se genera en función de k
	private Solucion agitacion(int k, Solucion sol_){
		Random rm = new Random();
		ArrayList <Solucion> aux = new ArrayList <Solucion>();
		Solucion auxSol = new Solucion (sol_);
		for (int j = 0; j<k; j++){								//Bucle que se ejecuta k veces, cámbia la forma de buscar vecino
			for (int i = 0; i < problema.getNodos(); i++){		//Generar todos los vecinos de auxsol
				if (auxSol.getSol()[i]){
					auxSol.quitarnodo(i);
					aux.add(auxSol);
				}
				else {
					auxSol.addnodo(i);
					aux.add(auxSol);
				}
			}
			auxSol = aux.get(rm.nextInt(aux.size()));				//Actualizar auxSol con uno de los vecinos generados
		}
		return auxSol;
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
