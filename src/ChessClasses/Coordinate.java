package ChessClasses;

/** 
 * This class creates a Coordinate object that represents a square in a 2D chess board
 */

public class Coordinate {
	private Piece currentPiece;			//the piece on the coordinate
	private final String coordinateName;//the name in respect to the board
	private final int xCoord;				//the x coordinate
	private final int yCoord;				//the y coordinate
	
	
	public Coordinate(String name, int coordX, int coordY){
		currentPiece = null;
		coordinateName = name;
		xCoord = coordX;
		yCoord = coordY;
	}
	
	/**
	 * this will actually be the vertical position due to a computer coordinate
	 */
	
	public int getX(){
		return xCoord;
	}
	
	/**
	 * this will actually be the horizontal position due to a computer coordinate
	 */
	
	public int getY(){
		return yCoord;
	}
	
	public String getCoordinateName(){
		return coordinateName;
	}
	
	public Piece getCurrentPiece(){
		return currentPiece;
	}
	
	
	/**
	 * Checks is the coordinate is occupied or not
	 */
	public boolean isOccupied(){
		if( currentPiece == null){
			return false;
		}
		return true;
	}
	
	/**
	 * Sets a piece to the coordinate which is the passed in piece.
	 */
	public void setPiece(Piece newPiece) {
		currentPiece = newPiece;
	}


}
