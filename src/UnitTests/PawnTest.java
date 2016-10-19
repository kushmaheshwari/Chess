package UnitTests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import ChessClasses.*;

public class PawnTest {
	
	private static Coordinate[][] coords = null;

	@Test
	public void testPawnConstructor() {
		Coordinate coord = new Coordinate("h8",7,7);
		Pawn p = new Pawn(Color.WHITE,coord,"P");
		assertEquals(coord,p.getCoordinate());
		
		String name = "P";
		assertEquals(name,p.getName());
		
		Color c = Color.WHITE;
		assertEquals(c,p.getColor());
		
	}
	
	@Test
	public void testPawnMovement(){//test pawn going up 1
		makeBoard(8,8);
		initializePieces();
		Piece p = coords[6][0].getCurrentPiece();
		
		Coordinate destination = coords[5][0];
		boolean move = p.canMove(destination, coords);
		assertEquals(move,true);
		
	}
	
	
	@Test
	public void testPawnFirstMove(){//test pawn going up 2
		makeBoard(8,8);
		initializePieces();
		Piece p = coords[6][0].getCurrentPiece();
		
		Coordinate destination = coords[4][0];
		boolean move = p.canMove(destination, coords);
		assertEquals(move,true);
		
	}
	
	@Test
	public void testPawnFirstTwoMoves(){//test the intial moves of Pawn
		makeBoard(8,8);
		initializePieces();
		Piece p = coords[6][0].getCurrentPiece();
		
		Coordinate destination = coords[4][0];
		boolean move = p.canMove(destination, coords);
		p.makeMove(destination);
		destination.setPiece(p);
		
		Coordinate destination2 = coords[2][0];
		boolean canMove2 = p.canMove(destination2, coords);
		assertEquals(canMove2,false);
		
		Coordinate destination3 = coords[3][0];
		boolean canMove3 = p.canMove(destination3, coords);
		assertEquals(canMove3,true);
		
	}
	
	@Test
	public void testPawnNotMoving(){//pawn cant move if anything is in front of it
		makeBoard(8,8);
		initializePieces();
		
		Piece p = coords[6][0].getCurrentPiece();
		Coordinate destination = coords[4][0];
		p.makeMove(destination);
		destination.setPiece(p);
		
		Piece p2 = coords[1][0].getCurrentPiece();
		Coordinate destination2 = coords[3][0];
		p2.makeMove(destination2);
		destination2.setPiece(p2);
		
		Coordinate destination3 = coords[3][0];
		boolean canMove = p.canMove(destination3, coords);
		assertEquals(canMove,false);
		
		
	}
	
	@Test
	public void pawnTakingOpposingPiece(){//pawn takes opposing piece through diagonal
		makeBoard(8,8);
		initializePieces();
		
		Piece p = coords[6][0].getCurrentPiece();
		Coordinate destination = coords[4][0];
		p.makeMove(destination);
		destination.setPiece(p);
		
		Piece p2 = coords[1][1].getCurrentPiece();
		Coordinate destination2 = coords[3][1];
		p2.makeMove(destination2);
		destination2.setPiece(p2);
		
		Coordinate destination3 = coords[3][1];
		boolean canMove = p.canMove(destination3, coords);
		assertEquals(canMove,true);
		
		p.makeMove(destination3);
		destination3.setPiece(p);
		assertEquals(destination3.getCurrentPiece(),p);
		
	}
	
	
	public void makeBoard(int width, int height){
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
		
	}

	

}
