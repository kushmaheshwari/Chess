package ChessClasses;

import java.awt.Color;

/**
 * This class is as an abstract class for the Pieces.
 * Each piece will extend this class and add on functionality that only that piece will have
 */


public abstract class Piece {
	
	protected Coordinate currentCoord;  //The coordinate the piece is sitting on
	protected final Color myColor; 		//The Color/team of the piece
	protected String myName;			//The name of the piece
	
	public Piece(Color color, Coordinate coord,String name){
		myColor = color;
		currentCoord = coord;
		myName = name;
		
	}
	
	public Color getColor(){
		return myColor;
	}
	
	public Coordinate getCoordinate(){
		return currentCoord;
	}
	
	public String getName(){
		return myName;
	}
	
	/*
	 * This method in this class does the basic checking that all pieces will follow
	 */

	public boolean canMove(Coordinate destination, Coordinate[][] coords){
		if(destination == null){//destination is null
			return false;
		}
		if(destination.getCoordinateName().equals(currentCoord.getCoordinateName())){//destination is current coordinate of piece
			return false;
		}
		Piece p = destination.getCurrentPiece();
		if(p != null && p.getColor() == myColor){//piece is the player's piece
			return false;
		}
		
		//destination is on the board
		if(destination.getX() > coords[0].length || destination.getX()<0 || destination.getY() > coords[0].length || destination.getY()<0){
			return false;
		}
		return true;
	}
	
	/*
	 * Undos a move by reverting a coordinate
	 */
	public void undoMove(Coordinate start){
		currentCoord = start;
	}
	
	/*
	 * This method will only get overriden by the Pawn class
	 */
	
	public void makeMove(Coordinate destination){
		currentCoord = destination;
		
		
	}
	
	

}
