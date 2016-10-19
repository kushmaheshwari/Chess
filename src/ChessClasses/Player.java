package ChessClasses;
import java.awt.Color;


import java.util.ArrayList;


/**
 * This class represents a Player object. A player has an assigned color and a list of remaining pieces.
 */
public class Player {
	ArrayList<Piece> myPieces;	//the pieces remaining for the player
	Color myColor;				//the color that player corresponds too
	
	public Player(Color color){
		myPieces = new ArrayList<Piece>();
		myColor = color;
	}
	
	public Color getColor(){
		return myColor;
	}
	
	
	//add a piece bcz the move that caused death was invalid
	public void addPiece(Piece p){
		myPieces.add(p);
	}
	
	
	//remove a piece bcz it died or bcz code has to simulate death
	public void removePiece(Piece p){
		myPieces.remove(p);
	}
	
	//is the piece my piece
	public boolean isMyPiece(Piece p){
		if(myPieces.contains(p)){
			return true;
		}
		return false;
	}
	
	//get the list of remaining pieces
	public ArrayList<Piece> getPieces(){
		return myPieces;
	}
	
	public void printPieces(){
		for(int i = 0;i<myPieces.size();i++){
			Piece p = myPieces.get(i);
			Coordinate c = p.getCoordinate(); 
			System.out.print("[" + p.getName() +";" + c.getCoordinateName() + "]");
		}
		System.out.println();
	}
	
	
}
