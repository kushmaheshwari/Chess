package ChessClasses;

import java.awt.Color;


public class Pawn extends Piece {
	
	private boolean isFirstMove;

	public Pawn(Color color, Coordinate coord, String name){
		super(color,coord,name);
		isFirstMove = true;
		
	}
	
	/**
	 * A pawn can move only straight. On the first move it can go up 2, but after that it can only go up 1.
	 * To kill a piece a pawn must go diagonal up to the left or right.
	 */
	
	public boolean canMove(Coordinate destination, Coordinate[][] coords){
		if(!super.canMove(destination,coords)){
			return false;
		}
		Piece p = destination.getCurrentPiece();

		int myX = currentCoord.getX();
		int myY = currentCoord.getY();


		int destX = destination.getX();
		int destY = destination.getY();

		
		if(myColor == Color.WHITE){
			if(destY == myY && destX == myX - 1 && destination.isOccupied() == false){
				return true;
			}else if(destY == myY && destX == myX - 2 && destination.isOccupied() == false && isFirstMove == true){
				return true;
			}else if((destY == myY -1 || destY == myY+1) && destX == myX - 1 && destination.isOccupied() == true){
				if(p.getColor() == Color.BLACK){
					return true;
				}
			}
			return false;
		}else{
			if(destY == myY && destX == myX + 1 && destination.isOccupied() == false){
				return true;
			}else if(destY == myY && destX == myX + 2 && destination.isOccupied() == false && isFirstMove == true){
				return true;
			}else if((destY == myY -1 || destY == myY + 1) && destX == myX + 1 && destination.isOccupied() == true){
				if(p.getColor() == Color.WHITE){
					return true;
				}
			}
			return false;
			
		}
		
		
	}
	
	/**
	 * This method overrides because the Pawns possible move changes after the first move
	 */
	
	public void makeMove(Coordinate destination){
		isFirstMove = false;
		currentCoord = destination;
		
	}
	
}
