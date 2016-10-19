package ChessClasses;
import java.awt.Color;
import java.util.Scanner;

/** 
 * A game of Chess is started by running this class.
 *  It instantiates a board, reads from standard in to make moves, and asks players for their moves.
 */

public class Game {
	
	private static ChessGUI gameGUI;
	Color color = Color.WHITE;//starting color
	

	
	public static void main(String[] args){
		gameGUI = new ChessGUI();
	}
	
	

}


//gui has a board,game has a gui and a board
//