package ChessClasses;
import java.awt.Color;

public class CustomA extends Piece{
	
	public CustomA(Color color, Coordinate coord,String name){
		super(color,coord,name);
		
	}
	
	/**
	 * This piece can move diagonal by one square in one direction(White goes up and Black goes down)
	 */
	
	
	
	public boolean canMove(Coordinate destination,Coordinate[][] coords){
			
		if(!super.canMove(destination,coords)){
			return false;
		}
		
	
		int myX = currentCoord.getX();
		int myY = currentCoord.getY();
	
	
		int destX = destination.getX();
		int destY = destination.getY();
		
		if(myColor == Color.WHITE){
			if((destY == myY+1 && destX == myX - 1) || (destY == myY-1 && destX == myX - 1)){
				return true;
			}
			return false;
		}else{
			if((destY == myY+1 && destX == myX + 1) || (destY == myY-1 && destX == myX + 1)){
				return true;
			}
			return false;
			
		}
			
	}
	
//	
	

}
