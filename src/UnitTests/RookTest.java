package UnitTests;

import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Test;
import ChessClasses.*;

public class RookTest {
	
	private static Coordinate[][] coords = null;

	
	/*
	 * This tests whether the Rook constructor is workin properly
	 */
	
	@Test
	public void testRookConstructor() {
		Coordinate coord = new Coordinate("h8",7,7);
		Rook p = new Rook(Color.WHITE,coord,"R");
		assertEquals(coord,p.getCoordinate());
		
		String name = "R";
		assertEquals(name,p.getName());
		
		Color c = Color.WHITE;
		assertEquals(c,p.getColor());
		
	}
	
	/*
	 * Tests whether the Rooks vertical movement works
	 */
	
	@Test
	public void testRookVericalMovement(){//test rook vertical movement
		makeBoard(8,8);
//		initializePieces();
		
		Rook a1 = new Rook(Color.WHITE,coords[7][0],"R");
		coords[7][0].setPiece(a1);
		
		
		Piece p = coords[7][0].getCurrentPiece();
		
		Coordinate destination = coords[0][0];
		boolean move = p.canMove(destination, coords);
		assertEquals(move,true);
		
	} 
	
	/*
	 * Tests whether the Rooks horizontal movement
	 */
	
	@Test
	public void testRookHorizontalMovement(){//test rook horizontal movement
		makeBoard(8,8);
//		initializePieces();
		
		Rook a1 = new Rook(Color.WHITE,coords[7][0],"R");
		coords[7][0].setPiece(a1);
		
		
		Piece p = coords[7][0].getCurrentPiece();
		
		Coordinate destination = coords[7][7];
		boolean move = p.canMove(destination, coords);
		assertEquals(move,true);
		
	} 
	
	/*
	 * Tests whether the vertical movement of the rook is blocked
	 */
	
	@Test
	public void testRookVerticalBlockedMovement(){//test rook being blocked by piece vertical
		makeBoard(8,8);
//		initializePieces();
		
		Rook a1 = new Rook(Color.WHITE,coords[0][1],"R");
		coords[0][1].setPiece(a1);
		
		Pawn h2 = new Pawn(Color.WHITE,coords[0][5],"P");
		coords[0][5].setPiece(h2);
		
		
		Piece p = coords[0][1].getCurrentPiece();
		
		Coordinate destination = coords[0][7];
		boolean move = p.canMove(destination, coords);
		assertEquals(move,false);
		
	}
	
	/*
	 * Tests whether the horizontal movement of the rook is blocked
	 */
	
	@Test
	public void testRookHorizontalBlockedMovement(){//test rook being blocked by piece horizontal
		makeBoard(8,8);
//		initializePieces();
		
		Rook a1 = new Rook(Color.WHITE,coords[6][2],"R");
		coords[6][2].setPiece(a1);
		
		Pawn h2 = new Pawn(Color.WHITE,coords[5][2],"P");
		coords[5][2].setPiece(h2);
		
		
		Piece p = coords[6][2].getCurrentPiece();
		
		Coordinate destination = coords[1][2];
		boolean move = p.canMove(destination, coords);
		assertEquals(move,false);
		
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
