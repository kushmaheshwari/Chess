package ChessClasses;
import java.awt.Color;
import java.util.ArrayList;

/**
 *  This class represents a Chess Board and is the main controller for this project
 */


public class ChessBoard {
	private static Coordinate[][] coords = null; //2D array of coordinate objects
	private static Player White;				 //Player White Object which contains pieces/colors
	private static Player Black;				 //Player Black Object which contains pieces/colors
	private static int myWidth;				 	 //The width of the board (in number of tiles).
	private static int myHeight;			 	 //The width of the board (in number of tiles).
	
	
	private static Piece piece1;
	private static Piece piece2;
	private static Coordinate coordinate1;
	private static Coordinate coordinate2;
	
	/**
	 * Instantiates a board
	 * @param: myHeight which is the height of the board
	 * @param: myWidth which is width of the board
	 */
	
	public ChessBoard(int height, int width){
	 	myHeight = height;
		myWidth = width;
		
		White = new Player(Color.WHITE);
		Black = new Player(Color.BLACK);
		
		coords = new Coordinate[myHeight][myWidth];
		this.makeBoard(); 
		this.initializePieces(false);
		printBoardWithPieces();
		
	}
	
	public ChessBoard(int height, int width,boolean custom){
	 	myHeight = height;
		myWidth = width;
		
		White = new Player(Color.WHITE);
		Black = new Player(Color.BLACK);
		
		coords = new Coordinate[myHeight][myWidth];
		this.makeBoard(); 
		this.initializePieces(true);
		printBoardWithPieces();
		
	}
	
	/**
	 * gets width of board
	 */
	
	public int getWidth(){
		return myWidth;
	}
	
	/**
	 * gets height of board
	 */
	
	public int getHeight(){
		return myHeight;
	}
	
	/**
	 * returns board
	 */
	
	public Coordinate[][] getCoords(){
		return coords;
	}
	/**
	 * Checks if the King of the c color team is in check
	 * @param Player player: the player that is calling this function
	 * @param Coordinate kingCoord: where the king is
	 * @param boolean myKing: if true get king of player else king is of other player
	 * return true if king is in check else return false
	 */
	
	public static boolean isKingInCheck(Player player,Coordinate kingCoord, boolean myKing){
		ArrayList<Piece> pieces;
		if((player.getColor() == Color.WHITE && myKing) || (player.getColor() ==Color.BLACK && !myKing) ){
			pieces = Black.getPieces();
		}else{
			pieces = White.getPieces();
		}
		
		for(int i = 0;i < pieces.size();i++){
			Piece piece = pieces.get(i);
			if(piece.canMove(kingCoord, coords)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 *  Looks for Coordinate of my King
	 * @param Player player: the player calling the function
	 * @param boolean myKing: if true get get king of player else get opposite king
	 * return coordinate of respective King
	 */
	public static Coordinate getCoordinateOfKing(Player player, boolean myKing){
		if((player.getColor() == Color.WHITE && myKing) || (player.getColor() == Color.BLACK && !myKing)){
			for(int i = 0; i<myWidth;i++){
				for(int j=0;j<myHeight;j++){
					Piece p = coords[i][j].getCurrentPiece();
					if(p!= null && p.getName().equals("K") && p.getColor() == Color.WHITE){
						return coords[i][j];
						
					}
				}
			}
		}else{
			for(int i = 0; i<myWidth;i++){
				for(int j=0;j<myHeight;j++){
					Piece p = coords[i][j].getCurrentPiece();
					if(p!=null && p.getName() == "K" && p.getColor() == Color.BLACK){
						return coords[i][j];	
					}
				}
			}
			
		}
		return null;
	}
	
	
	
	/**
	 *  Checks if the other king is in checkmate 
	 * @param Player player: which is the player calling this function
	 */
	
	public static boolean theirKingInCheckMate(Player player){//check if any move opposing player makes still puts their king in check from your players
		ArrayList<Piece> pieces;
		if(player.getColor() == Color.WHITE){
			pieces = Black.getPieces();
		}else{
			pieces = White.getPieces();
		}
		
		for(int i = 0;i < pieces.size();i++){
			Piece p = pieces.get(i);
			Coordinate originalC = p.getCoordinate();//get original coordinate of piece that is moving
			for(int xCoord = 0;xCoord < myWidth;xCoord++){
				for(int yCoord= 0;yCoord < myHeight;yCoord++){
					if(p.canMove(coords[xCoord][yCoord],coords)){//if this piece can move to this coordinate
						Piece currentEnd = moveThePiece(p, originalC, xCoord, yCoord);
						Coordinate otherKing = getCoordinateOfKing(player,false);
						if(isKingInCheck(player,otherKing,false)==false){//check if king is still in check
							System.out.println(p.getName()+ " " + coords[xCoord][yCoord].getCoordinateName()+" "+p.getColor());
							undoTheMove(p,originalC,currentEnd,coords[xCoord][yCoord]);
							return false;
						}else{//remove changes
							undoTheMove(p,originalC,currentEnd,coords[xCoord][yCoord]);
							
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Undos the move if it needs to revert to old board
	 */
	
	public static void undo(){
		undoTheMove(piece1,coordinate1,piece2,coordinate2);
		printBoardWithPieces();
	}
	
	public static void undoTheMove(Piece piece1,Coordinate coord1, Piece piece2, Coordinate coord2){
		piece1.makeMove(coord1);//set piece's coord to start
		coord1.setPiece(piece1);
		
		coord2.setPiece(piece2);
		if(piece2 != null){
			piece2.makeMove(coord2);
			if(piece2.getColor() == Color.WHITE){
				White.addPiece(piece2);
			}else{
				Black.addPiece(piece2);
			}
		}
		
	}
	
	/**
	 * moves the piece and keeps track of previous data points to undo the move
	 */

	private static Piece moveThePiece(Piece piece, Coordinate originalC, int xCoord, int yCoord) {
		Piece currentEnd = coords[xCoord][yCoord].getCurrentPiece();//get piece on square that piece is moving too
		piece.makeMove(coords[xCoord][yCoord]);//move piece here
		coords[xCoord][yCoord].setPiece(piece);//set coordinate to new piece
		originalC.setPiece(null);
		if(currentEnd != null){
			if(currentEnd.getColor() == Color.WHITE){
				White.removePiece(currentEnd);
			}else{
				Black.removePiece(currentEnd);
			}
		}
		return currentEnd;
	}
	
	/**
	 * This method is called by the Game class and signifies a part of the loop.
	 * The logic is started from this method
	 */
	
	public static BoardReturn play(String source, String dest,Color color){
		//current player making move
		Player player;
		if(color == Color.WHITE){
			player = White;
		}else{
			player = Black;
		}
		
		//Find the starting and ending coordinate
		Coordinate start = null;
		Coordinate end = null;
		for(int i = 0;i < myWidth;i++){
			for(int j=0;j < myHeight;j++){
				if(coords[i][j].getCoordinateName().equals(source)){
					start = coords[i][j];//get starting coord
				}else if(coords[i][j].getCoordinateName().equals(dest)){
					end = coords[i][j];//get ending coord
				}
			}
		}
		
		/**
		 * This if statement makes all the necessary checks when a player moves a piece
		 * Start/End coordinates, pieces is player's piece, piece can make the move, check, checkmate
		 */
		BoardReturn returnValues = null;

		if(start == null && end == null){
			returnValues = new BoardReturn(false,"Not a coordinate.Try Again");
			return returnValues;
		}
		
		Piece p = start.getCurrentPiece();//piece that is moving
		if(p == null){
			returnValues = new BoardReturn(false,"No piece on this sqaure");
			return returnValues;
		}
		if(player.isMyPiece(p) != true){
			
			returnValues = new BoardReturn(false,"Not your piece.Try Again");
			return returnValues;
		}
		if(p.canMove(end, coords) == false){
			returnValues = new BoardReturn(false,"Cant move here.Try Again");
			return returnValues;
		}
		
		Piece currentEnd = end.getCurrentPiece();   //make move and setset piece's coord
		end.setPiece(p); 							
		p.makeMove(end);							
		start.setPiece(null);                       
		if(currentEnd != null){
			if(player.getColor() == Color.WHITE){
				Black.removePiece(currentEnd);
			}else{
				White.removePiece(currentEnd);
			}
		}
		
		Coordinate myKing = getCoordinateOfKing(player,true);
		Coordinate otherKing = getCoordinateOfKing(player,false);
		if(isKingInCheck(player,myKing,true)){
			undoTheMove(p,start,currentEnd,end);
			returnValues = new BoardReturn(false,"Your king is in check.Try Again");
			return returnValues;
			
		}else if(isKingInCheck(player,otherKing,false)){
			if(theirKingInCheckMate(player)){//print Checkmate
				returnValues = new BoardReturn(true,"CheckMate");
				printBoardWithPieces();
				return returnValues;
			}
			piece1 = p;
			piece2 = currentEnd;
			coordinate1 = start;
			coordinate2 = end;
			
			
			returnValues = new BoardReturn(true,"Check");
			printBoardWithPieces();
			return returnValues;
		}
		piece1 = p;
		piece2 = currentEnd;
		coordinate1 = start;
		coordinate2 = end;
		
	
		printBoardWithPieces();
		returnValues = new BoardReturn(true,null);
		return returnValues;
		
	}
	
	
	/**
	 *  Creates the board and initializes each Coordinate to the proper location(x,y)
	 * 
	 */
	
	private void makeBoard(){
		int verticalChar = myHeight;//for naming vertical part of square
		char horizontalChar = 'a';//for naming horizontal part of square
		
		for(int i = 0;i < myWidth;i++){
			horizontalChar = 'a';
			for(int j = 0;j < myHeight;j++){
				String name = horizontalChar + Integer.toString(verticalChar);
				coords[i][j] = new Coordinate(name,i,j);
				horizontalChar++;
			}
			verticalChar--;
		}
	}
	
	/** 
	 * Prints the board and where each piece is on the board. 
	 * Tells players the board landscape
	 */

	private static void printBoardWithPieces(){
		System.out.println("    a   b   c   d   e   f   g   h");
		System.out.println();
		int count = 8;
		for(int i = 0;i < myWidth;i++){
			System.out.print(count + "  ");
			for(int j = 0;j < myHeight;j++){
				
				Piece piece = coords[i][j].getCurrentPiece();
				if(piece!=null){
					if(piece.getColor() == Color.WHITE){
						System.out.print(" " + "W" + piece.getName() + " ");
					}else{
						System.out.print(" " + "B" + piece.getName() + " ");
					}
				}else{
					System.out.print(" " + "_" + "  ");
				}
			}
			count--;
			System.out.println();
		}
		
	}
	
	/**
	 * Initializes all the pieces to their appropriate position on the board
	 * If custom is 'true' then add the custom pieces to the board 
	 */
	private void initializePieces(boolean custom){
		//Initialize White Pawns
		Pawn a2 = new Pawn(Color.WHITE,coords[6][0],"P");
		Pawn b2 = new Pawn(Color.WHITE,coords[6][1],"P");
		Pawn c2 = new Pawn(Color.WHITE,coords[6][2],"P");
		Piece d2;
		Piece e2;
		if(custom){
			 d2 = new CustomA(Color.WHITE,coords[6][3],"A");
			 e2 = new CustomB(Color.WHITE,coords[6][4],"C");
		}else{
			d2 = new Pawn(Color.WHITE,coords[6][3],"P");
			e2 = new Pawn(Color.WHITE,coords[6][4],"P");
		}
		
		Pawn f2 = new Pawn(Color.WHITE,coords[6][5],"P");
		Pawn g2 = new Pawn(Color.WHITE,coords[6][6],"P");
		Pawn h2 = new Pawn(Color.WHITE,coords[6][7],"P");
		coords[6][0].setPiece(a2);
		coords[6][1].setPiece(b2);
		coords[6][2].setPiece(c2);
		coords[6][3].setPiece(d2);
		coords[6][4].setPiece(e2);
		coords[6][5].setPiece(f2);
		coords[6][6].setPiece(g2);
		coords[6][7].setPiece(h2);
		White.addPiece(a2);
		White.addPiece(b2);
		White.addPiece(c2);
		White.addPiece(d2);
		White.addPiece(e2);
		White.addPiece(f2);
		White.addPiece(g2);
		White.addPiece(h2);
		
		
		//Initialize BlackPawns
		Pawn a7 = new Pawn(Color.BLACK,coords[1][0],"P");
		Pawn b7 = new Pawn(Color.BLACK,coords[1][1],"P");
		Pawn c7 = new Pawn(Color.BLACK,coords[1][2],"P");
		Piece d7;
		Piece e7;
		if(custom){
			 d7 = new CustomA(Color.BLACK,coords[1][3],"A");
			 e7 = new CustomB(Color.BLACK,coords[1][4],"C");
		}else{
			d7 = new Pawn(Color.BLACK,coords[1][3],"P");
			e7 = new Pawn(Color.BLACK,coords[1][4],"P");
		}
		
		Pawn f7 = new Pawn(Color.BLACK,coords[1][5],"P");
		Pawn g7 = new Pawn(Color.BLACK,coords[1][6],"P");
		Pawn h7 = new Pawn(Color.BLACK,coords[1][7],"P");
		coords[1][0].setPiece(a7);
		coords[1][1].setPiece(b7);
		coords[1][2].setPiece(c7);
		coords[1][3].setPiece(d7);
		coords[1][4].setPiece(e7);
		coords[1][5].setPiece(f7);
		coords[1][6].setPiece(g7);
		coords[1][7].setPiece(h7);
		Black.addPiece(a7);
		Black.addPiece(b7);
		Black.addPiece(c7);
		Black.addPiece(d7);
		Black.addPiece(e7);
		Black.addPiece(f7);
		Black.addPiece(g7);
		Black.addPiece(h7);
		
		//Initialize White back row
		Rook a1 = new Rook(Color.WHITE,coords[7][0],"R");
		coords[7][0].setPiece(a1);
		Rook h1 = new Rook(Color.WHITE,coords[7][7],"R");
		coords[7][7].setPiece(h1);
		Knight b1 = new Knight(Color.WHITE,coords[7][1],"N");
		coords[7][1].setPiece(b1);
		Knight g1 = new Knight(Color.WHITE,coords[7][6],"N");
		coords[7][6].setPiece(g1);
		Bishop c1 = new Bishop(Color.WHITE,coords[7][2],"B");
		coords[7][2].setPiece(c1);
		Bishop f1 = new Bishop(Color.WHITE,coords[7][5],"B");
		coords[7][5].setPiece(f1);
		Queen d1 = new Queen(Color.WHITE,coords[7][3],"Q");
		coords[7][3].setPiece(d1);
		King whiteKing = new King(Color.WHITE,coords[7][4],"K");
		coords[7][4].setPiece(whiteKing);
		White.addPiece(a1);
		White.addPiece(h1);
		White.addPiece(b1);
		White.addPiece(g1);
		White.addPiece(c1);
		White.addPiece(f1);
		White.addPiece(d1);
		White.addPiece(whiteKing);
		
		
		//Initialize Black back row
		Rook a8 = new Rook(Color.BLACK,coords[0][0],"R");
		coords[0][0].setPiece(a8);
		Rook h8 = new Rook(Color.BLACK,coords[0][7],"R");
		coords[0][7].setPiece(h8);
		Knight b8 = new Knight(Color.BLACK,coords[0][1],"N");
		coords[0][1].setPiece(b8);
		Knight g8 = new Knight(Color.BLACK,coords[0][6],"N");
		coords[0][6].setPiece(g8);
		Bishop c8 = new Bishop(Color.BLACK,coords[0][2],"B");
		coords[0][2].setPiece(c8);
		Bishop f8 = new Bishop(Color.BLACK,coords[0][5],"B");
		coords[0][5].setPiece(f8);
		Queen d8 = new Queen(Color.BLACK,coords[0][3],"Q");
		coords[0][3].setPiece(d8);
		King blackKing = new King(Color.BLACK,coords[0][4],"K");
		coords[0][4].setPiece(blackKing);
		Black.addPiece(a8);
		Black.addPiece(h8);
		Black.addPiece(b8);
		Black.addPiece(g8);
		Black.addPiece(c8);
		Black.addPiece(f8);
		Black.addPiece(d8);
		Black.addPiece(blackKing);
	}

}
