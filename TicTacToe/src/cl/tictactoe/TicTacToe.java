package cl.tictactoe;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;


/**
 * La clase TicTacToe es donde se crea la interface grafica para el funcionamiento del VideoJuego TicTacToe
 * Cuenta con los metodos tableroLLeno, coincidenciaFila, determinarGanador, etc, metodos necesarios para
 * el correcto funcionamiento del programa
 * 
 * @author B. Uribe
 */

public class TicTacToe extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static TicTacToe juego = new TicTacToe();
	
	private final String Jugador1 = "X";
	private final String Jugador2 = "O";
	private final String Vacio = "-";
	
	private Casilla[][] casillasb;
	
	//Player1 = true, Player2 = false
	private boolean Turno;
	
	private static final int W = 600;
	private static final int H = 600;
	private JFrame main;
	private JButton[][] casillas;
	
	
	/**
	 * Metodo que hace que TicTacToe sea Singleton
	 * @return juego solo si no existe otra instancia
	 */
	
	public static TicTacToe getInstance() {
		if(juego == null) {
			juego = new TicTacToe();
		}
		return juego;
	}
	
	/**
	 * Constructor de TicTacToe que crea la ventana y toda la interface grafica con todas las casillas 
	 * disponibles donde se puede jugar, marcando cada casilla con el simbolo correspondiente
	 */
	
	public TicTacToe() {
		this.Turno = true;
		main = new JFrame("Tic Tac Toe");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setResizable(false);
		main.setBounds(100, 100, W, H);
		casillas = new JButton[3][3];
		casillasb = new Casilla[3][3];
		main.setLayout(new GridLayout(3,3));
				
		for(int i = 0; i < casillas.length; i++) 
			for(int j = 0; j < casillas[0].length; j++) {
				casillas[i][j] = new JButton();
				casillas[i][j].setText(Vacio);
				main.add(casillas[i][j]);
				casillasb[i][j] = new Casilla("-");
				final int fila = i, columna = j; 
				
				casillas[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton botonPresionado = (JButton) e.getSource();
						if(botonPresionado.getText().equals(Vacio)) {
							if(Turno) {
								botonPresionado.setText(Jugador1);
								botonPresionado.setBackground(new Color(174, 208, 236));
								casillasb[fila][columna].setSimbolo(Jugador1);
							}else {
								botonPresionado.setText(Jugador2);
								botonPresionado.setBackground(new Color(249, 234, 108));
								casillasb[fila][columna].setSimbolo(Jugador2);
							}
							botonPresionado.setEnabled(false);
							CambioTurno();
							if(finPartida()) {
								mostrarGanador();
							}
						}
						
					}
				});
			}
		
		main.setVisible(true);
	}
	
	
	/**
	 * Metodo para poder realizar el cambio de turno luego de una jugada
	 */
	
	public void CambioTurno() {
		this.Turno = !this.Turno;
	}

	
	/**
	 * Metodo utilizado para comprobar si el tablero esta lleno
	 * @return true en caso de que el tablero tenga todas las casillas ocupadas
	 */
	
	public boolean tableroLLeno() {
		for(int i = 0; i < casillas.length; i++) {
			for(int j = 0; j < casillas[0].length; j++) {
				if(casillasb[i][j].getSimbolo().equalsIgnoreCase(Vacio)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Metodo que comprueba si hay algun ganador en las filas
	 * @return simbolo del jugador que gano
	 */
	
	public String coincidenciaFila() {
		String simbolo;
		boolean coincidencia;
		
		for(int i = 0; i < casillas.length; i++) {
			coincidencia = true;
			simbolo = casillasb[i][0].getSimbolo();
			if(simbolo != Vacio) {
				for(int j = 1; j < casillas[0].length; j++) {
					if(simbolo != casillasb[i][j].getSimbolo()) {
						coincidencia = false;
					}
			}
			if(coincidencia)
				return simbolo;		
			}
		}
		//Nadie gano en las filas
		return Vacio;
	}
	
	/**
	 * Metodo que comprueba si hay algun ganador en las columnas
	 * @return simbolo del jugador que gano
	 */
	
	public String coincidenciaColumna() {
		String simbolo;
		boolean coincidencia;
		
		for(int j = 0; j < casillas[0].length; j++) {
			coincidencia = true;
			simbolo = casillasb[0][j].getSimbolo();
			if(simbolo != Vacio) {
				for(int i = 1; i < casillas.length; i++) {
					if(simbolo != casillasb[i][j].getSimbolo()) {
						coincidencia = false;
					}
				}
				if(coincidencia) {
					return simbolo;
				}
			}
		}
		//Nadie gano en las columnas
		return Vacio;
	}
	
	/**
	 * Metodo que comprueba si hay algun ganador en las diagonales
	 * @return simbolo del jugador que gano
	 */
	
	public String coincidenciaDiag() {
		String simbolo;
		boolean coincidencia = true;
		
		simbolo = casillasb[0][0].getSimbolo();
		if(simbolo != Vacio) {
			for(int i = 0; i < casillas.length; i++) {
				if(simbolo != casillasb[i][i].getSimbolo()) {
					coincidencia = false;
				}
			}
			if(coincidencia) {
				return simbolo;
			}
		}
		
		//Diagonal inversa
		simbolo = casillasb[2][0].getSimbolo();
		if(simbolo != Vacio) {
			for(int i = 0, j = 2; i < casillas.length; i++, j--) {
				if(simbolo != casillasb[i][j].getSimbolo()) {
					coincidencia = false;
				}
			}
			if(coincidencia) {
				return simbolo;
			}
		}
		//Nadie gano en las diagonales
		return Vacio;
	}
	
	/**
	 * Metodo que comprueba si la partida ya ha finalizado
	 * @return true en caso de que la partia haya finalizado ya sea por ganador o empate
	 */
	
	public boolean finPartida() {
		if(tableroLLeno() 
				|| coincidenciaFila() != Vacio
				|| coincidenciaColumna() != Vacio
				|| coincidenciaDiag() != Vacio) {
			return true;
		}else
			return false;
	}
	
	/**
	 * Metodo utilizado para determinar el ganador
	 * @return simbolo del jugador que gano
	 */
	
	public String determinarGanador() {
		String simbolo = coincidenciaFila();
		if(simbolo != Vacio) {
			return simbolo;
		}
		simbolo = coincidenciaColumna();
		if(simbolo != Vacio) {
			return simbolo;
		}
		simbolo = coincidenciaDiag();
		if(simbolo != Vacio) {
			return simbolo;
		}
		
		//Ningun ganador
		return simbolo;
	}
	
	/**
	 * Metodo que despliega una ventana emergente en donde se muestra el resultado de la partida
	 */
	
	public void mostrarGanador() {
		String ganador = determinarGanador();
		if(!ganador.equals(Vacio)) {
			String mensaje = "El jugador " + (ganador.equals(Jugador1)? "1 (X)" : "2 (O)") + " ha ganado";
			JOptionPane.showMessageDialog(main, mensaje, "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
			reiniciarTablero();
		}else {
			JOptionPane.showMessageDialog(main, "Es un empate", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
			reiniciarTablero();
		}
	}
	
	/**
	 * Metodo que reinicia todas las casillas dejandolas vacias y sin color
	 */
	
	private void reiniciarTablero() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                casillas[i][j].setText(Vacio);
                casillasb[i][j].setSimbolo(Vacio);
                casillas[i][j].setEnabled(true);
                casillas[i][j].setBackground(null);
            }
        }
        Turno = true; // Reiniciar el turno
    }
	
	/**
	 * Main donde se crea la instancia de TicTacToe
	 * @param args
	 */
	
	public static void main(String[] args) {
		TicTacToe.getInstance();
	}

	/**
	 * Metodo necesario para el actionPerformed
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
