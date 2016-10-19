package ChessClasses;
import java.awt.Color;



public class King extends Piece{
	
	public King(Color color,Coordinate coord, String name){
		super(color,coord,name);

	}
	
	/**
	 * A king can move any direction by 1 square.
	 */
	
	public boolean canMove(Coordinate destination,Coordinate[][] coords){

		if(!super.canMove(destination,coords)){
			return false;
		}
		int myX = currentCoord.getX();
		int myY = currentCoord.getY();

		
		int destX = destination.getX();
		int destY = destination.getY();

		
		
		if(destY == myY && ((destX == myX + 1) || (destX == myX -1))){
			return true;
		}else if(destX == myX && ((destY == myY + 1) || (destY == myY -1))){
			return true;
		}else if((destX == myX + 1) &&((destY == myY-1) || destY == myY+1)){
			return true;
		}else if((destX == myX - 1) &&((destY == myY-1) || destY == myY+1)){
			return true;
		}
		return false;
		
	}
	

}
