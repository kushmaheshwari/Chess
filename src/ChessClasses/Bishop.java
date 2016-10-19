package ChessClasses;
import java.awt.Color;


public class Bishop extends Piece{
	

	public Bishop(Color color, Coordinate coord,String name){
		super(color,coord,name);
		
	}
	
	/** 
	 * A bishop can move diagonally in any direction but cannot jump over pieces
	 */
	
	public boolean canMove(Coordinate destination,Coordinate[][] coords){
		
		if(!super.canMove(destination,coords)){
			return false;
		}
		
		int myX = currentCoord.getX();
		int myY = currentCoord.getY();

		
		int destX = destination.getX();
		int destY = destination.getY();

		
		int yDif = Math.abs(destY - myY);
		int xDif = Math.abs(destX - myX);
		
		if(yDif != xDif){ // x and y difference must be the same 
			return false;
		}
		
		if((destX-myX > 0) && (destY-myY < 0)){//going down and left
			int i = myX+1;
			int j = myY-1;
			while(i<destX && j>destY){
				if(coords[i][j].isOccupied()){
					return false;
				}
				i++;
				j--;
			}
			return true;
		}else if((destX-myX>0) && (destY-myY > 0)){//going down and right
			int i = myX+1;
			int j = myY+1;
			while(i<destX && j<destY){
				if(coords[i][j].isOccupied()){
					return false;
				}
				i++;
				j++;
			}
			return true;
		}else if((destX-myX<0) && (destY-myY > 0)){//going up and right
			int i = myX-1;
			int j = myY+1;
			while(i>destX && j<destY){
				if(coords[i][j].isOccupied()){
					return false;
				}
				i--;
				j++;
			}
			return true;
		}else if((destX-myX<0) && (destY-myY < 0)){//going up and left
			int i = myX-1;
			int j = myY-1;
			while(i>destX && j>destY){
				if(coords[i][j].isOccupied()){
					return false;
				}
				i--;
				j--;
			}
			return true;
		}
		
		return false;
		
	}

}
