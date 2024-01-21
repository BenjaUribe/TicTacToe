package cl.tictactoe;

import javax.swing.JButton;
import java.util.*;

/**
 * Clase Casilla conciste en el tipo de objeto que es cada casilla
 * @author B. Uribe
 */

public class Casilla extends JButton{
	private String simbolo;
	
	/**
	 * Constructor de Casilla
	 * @param simbolo el simbolo del jugador que marco la casilla o marcada como vacio
	 */
	public Casilla(String simbolo) {
		this.simbolo = simbolo;
	}

	/**
	 * Metodo que obtiene el simbolo de la casilla
	 * @return el simbolo de la casilla
	 */
	public String getSimbolo() {
		return simbolo;
	}

	/**
	 * Metodo para definir el simbolo de la casilla
	 * @param simbolo que guardara en la casilla
	 */
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	

}
