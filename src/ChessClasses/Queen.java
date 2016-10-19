package ChessClasses;
import java.awt.Color;



public class Queen extends Piece {
	public Queen(Color color,Coordinate coord,String name){
		super(color,coord,name);
		
	}
	
	/**
	 * A queen can move in any direction as many sqaures as it wants.
	 * It combine the logic of a rook and a bishop
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

		int yDif = Math.abs(destY - myY);
		int xDif = Math.abs(destX - myX);

		
		if(yDif != xDif){
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
