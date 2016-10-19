package ChessClasses;
import java.awt.Color;



public class Knight extends Piece {
	
	
	public Knight(Color color, Coordinate coord,String name){
		super(color,coord,name);
		
	}
	
	/**
	 * A knight can move 2 square(up,down,left,right) and then 1 square over in respect to the original 2.
	 */
	
	public boolean canMove(Coordinate destination,Coordinate[][] coords){
		
		if(!super.canMove(destination,coords)){
			return false;
		}
		int myX = currentCoord.getX();
		int myY = currentCoord.getY();
		
		int destX = destination.getX();
		int destY = destination.getY();
		
		
		if(destX == myX+2 && ((destY == myY - 1) || (destY == myY + 1))){
			return true;
		}else if(destX == myX-2 && ((destY == myY - 1) || (destY == myY +1))){
			return true;
		}else if((destY == myY + 2) &&((destX == myX-1) || destY == myX+1)){
			return true;
		}else if((destY == myY - 2) &&((destX == myX-1) || destX == myX+1)){
			return true;
		}
		return false;
		
	}
	

}
