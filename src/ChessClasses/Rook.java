package ChessClasses;
import java.awt.Color;



public class Rook extends Piece {
	public Rook(Color color, Coordinate coord,String name){
		super(color,coord,name);
	
		
	}
	
	/**
	 * A rook can move up, down, left, and right as many squares as it wants.
	 */
	
	public boolean canMove(Coordinate destination,Coordinate[][] coords){

		if(!super.canMove(destination,coords)){
			return false;
		}
		int myX = currentCoord.getX();
		int myY = currentCoord.getY();

		
		int destX = destination.getX();
		int destY = destination.getY();

		
		
		if(destY == myY && (destX >= 0 && destX <= 7)){
			if(destX > myX){
				for(int i = myX+1;i < destX;i++){
					if(coords[i][myY].isOccupied() == true){
						return false;
					}
				}
			}else{
				for(int i = myX-1;i > destX;i--){
					if(coords[i][myY].isOccupied() == true){
						return false;
					}
				}
				
			}
			
			return true;
		}else if(destX == myX && (destY >= 0 && destY <= 7)){
			if(destY > myY){
				for(int i = myY+1;i < destY;i++){
					if(coords[myX][i].isOccupied() == true){
						return false;
					}
				}
			}else{
				for(int i = myY-1;i > destY;i--){
					if(coords[myX][i].isOccupied() == true){
						return false;
					}
				}
				
			}
			return true;
		}
		return false;
		
	}
	
}
