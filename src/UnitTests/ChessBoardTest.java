package UnitTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import ChessClasses.*;



public class ChessBoardTest {
	
	private static Coordinate[][] coords = null;
	
	private static Player White;					//Player White Object which contains pieces/colors
	private static Player Black;
	private static int width = 0;				// The width of the board (in number of tiles).
	private static int height = 0;
	
	int count = 0;
	public ChessBoardTest(){
	 	height = 8;
		width = 8;
		
		White = new Player(Color.WHITE);
		Black = new Player(Color.BLACK);
		
		coords = new Coordinate[height][width];
		if(count == 0){
			this.makeBoard();
			this.initializePieces();
		}else{
			this.makeBoard();
			this.initializeCustomPiece();
		}
		
		count++;
	}
	/*
	 * This tests finds the coordinate of My King(My refers to whatever color is passed in).
	 */
	
	@Test
	public void checkBoard(){
		ChessBoard cb = new ChessBoard(8,8); 
		
		assertEquals(cb.getHeight(),8);
		assertEquals(cb.getWidth(),8);
		
		Coordinate[][] coordinates = cb.getCoords();
		
		//checks every element of 2d array
		for(int i = 0;i<8;i++){
			for(int j=0;j<8;j++){
				assertEquals(coordinates[i][j].getX(),i);
				assertEquals(coordinates[i][j].getY(),j);
			}
		}
	}
	
	@Test
	public void findWhiteKingCoordinate(){
		ChessBoardTest cb = new ChessBoardTest();	
		Coordinate king = getCoordinateOfKing(White, true);
	
		assertEquals(king,coords[7][4]);		
		
	}
	
	//Check if the King is in Check
	
	@Test
	public void isKingInCheck(){
		ChessBoardTest cb = new ChessBoardTest();
		
//		Coordinate king = cb.getCoordinateOtherKing(Color.BLACK);
		assertEquals(isKingInCheck(Black,coords[7][4],false),false);
		
	}
	
	//Check if King is in CheckMate

	@Test
	public void isKingInCheckMate(){
		ChessBoardTest cb = new ChessBoardTest();
		
		
		assertEquals(theirKingInCheckMate(Black),false);
		
	}
	
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
			for(int j = 0;j < width;j++){
				for(int k= 0;k < height;k++){
					if(p.canMove(coords[j][k],coords)){//if this piece can move to this coordinate
						Piece currentEnd = moveThePiece(p, originalC, j, k);
						Coordinate otherKing = getCoordinateOfKing(player,false);
						if(isKingInCheck(player,otherKing,false)==false){//check if king is still in check
							System.out.println(p.getName()+ " " + coords[j][k].getCoordinateName()+" "+p.getColor());
							undoTheMove(p,originalC,currentEnd,coords[j][k]);
							return false;
						}else{//remove changes
							undoTheMove(p,originalC,currentEnd,coords[j][k]);
							
						}
					}
				}
			}
		}
		return true;
	}
	

	private static Piece moveThePiece(Piece p, Coordinate originalC, int j,
			int k) {
		Piece currentEnd = coords[j][k].getCurrentPiece();//get piece on square that piece is moving too
		p.makeMove(coords[j][k]);//move piece here
		coords[j][k].setPiece(p);//set coordinate to new piece
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
	
	public static void undoTheMove(Piece p1,Coordinate c1, Piece p2, Coordinate c2){
		p1.makeMove(c1);//set piece's coord to start
		c1.setPiece(p1);
		
		c2.setPiece(p2);
		if(p2 != null){
			p2.makeMove(c2);
			if(p2.getColor() == Color.WHITE){
				White.addPiece(p2);
			}else{
				Black.addPiece(p2);
			}
		}
	}
	
	public void makeBoard(){
		coords = new Coordinate[width][height];
		int num = height;//for naming vertical part of square
		char c = 'a';//for naming horizontal part of square
		
		for(int i = 0;i < width;i++){
			c = 'a';
			for(int j = 0;j < height;j++){
				String name = c + Integer.toString(num);
				coords[i][j] = new Coordinate(name,i,j);
				c++;
			}
			num--;
		}
	}
	
	private void initializeCustomPiece(){
		Pawn a2 = new Pawn(Color.WHITE,coords[6][0],"P");
		Pawn b2 = new Pawn(Color.WHITE,coords[6][1],"P");
		Pawn c2 = new Pawn(Color.WHITE,coords[6][2],"P");
		Pawn d2 = new Pawn(Color.WHITE,coords[6][3],"P");
		Pawn e2 = new Pawn(Color.WHITE,coords[6][4],"P");
		Pawn f2 = new Pawn(Color.WHITE,coords[5][5],"P");//here
		Pawn g2 = new Pawn(Color.WHITE,coords[4][6],"P");//here
		Pawn h2 = new Pawn(Color.WHITE,coords[6][7],"P");
		coords[6][0].setPiece(a2);
		coords[6][1].setPiece(b2);
		coords[6][2].setPiece(c2);
		coords[6][3].setPiece(d2);
		coords[6][4].setPiece(e2);
		coords[5][5].setPiece(f2);
		coords[4][6].setPiece(g2);
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
		Pawn d7 = new Pawn(Color.BLACK,coords[1][3],"P");
		Pawn e7 = new Pawn(Color.BLACK,coords[3][4],"P");//here
		Pawn f7 = new Pawn(Color.BLACK,coords[1][5],"P");
		Pawn g7 = new Pawn(Color.BLACK,coords[1][6],"P");
		Pawn h7 = new Pawn(Color.BLACK,coords[1][7],"P");
		coords[1][0].setPiece(a7);
		coords[1][1].setPiece(b7);
		coords[1][2].setPiece(c7);
		coords[1][3].setPiece(d7);
		coords[3][4].setPiece(e7);
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
		Queen d8 = new Queen(Color.BLACK,coords[3][7],"Q");//here
		coords[3][7].setPiece(d8);
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
	
	
	private void initializePieces(){
		//Initialize White Pawns
		Pawn a2 = new Pawn(Color.WHITE,coords[6][0],"P");
		Pawn b2 = new Pawn(Color.WHITE,coords[6][1],"P");
		Pawn c2 = new Pawn(Color.WHITE,coords[6][2],"P");
		Pawn d2 = new Pawn(Color.WHITE,coords[6][3],"P");
		Pawn e2 = new Pawn(Color.WHITE,coords[6][4],"P");
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
		Pawn d7 = new Pawn(Color.BLACK,coords[1][3],"P");
		Pawn e7 = new Pawn(Color.BLACK,coords[1][4],"P");
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
	
	 
	public static Coordinate getCoordinateOfKing(Player player, boolean myKing){
		if((player.getColor() == Color.WHITE && myKing) || (player.getColor() == Color.BLACK && !myKing)){
			for(int i = 0; i<width;i++){
				for(int j=0;j<height;j++){
					Piece p = coords[i][j].getCurrentPiece();
					if(p!= null && p.getName().equals("K") && p.getColor() == Color.WHITE){
						return coords[i][j];
						
					}
				}
			}
		}else{
			for(int i = 0; i<width;i++){
				for(int j=0;j<height;j++){
					Piece p = coords[i][j].getCurrentPiece();
					if(p!=null && p.getName() == "K" && p.getColor() == Color.BLACK){
						return coords[i][j];	
					}
				}
			}
			
		}
		return null;
	}

}
