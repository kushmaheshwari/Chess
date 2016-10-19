package ChessClasses;

import java.awt.Color;

public class CustomB extends Piece {
	
	public CustomB(Color color, Coordinate coord,String name){
		super(color,coord,name);
		
	}
	
	/**
	 * 
	 * This piece can move up 1, down 1, left 1, or right 1
	 */
	
	public boolean canMove(Coordinate destination,Coordinate[][] coords){
		
		if(!super.canMove(destination,coords)){
			return false;
		}
		
	
		int myX = currentCoord.getX();
		int myY = currentCoord.getY();
	
	
		int destX = destination.getX();
		int destY = destination.getY();
		
		
		if((destY == myY && destX == myX - 1) || (destY == myY && destX == myX + 1) || (destY == myY+1 && destX == myX) || (destY == myY-1 && destX == myX)){
			return true;
		}
		return false;
			
		
			
	}
	
	
}
