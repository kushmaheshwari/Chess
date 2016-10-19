package UnitTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import ChessClasses.*;



public class PlayerTest {
	
	@Test
	public void testPlayerConstructor() {
		Player p = new Player(Color.WHITE);
		assertEquals(p.getColor(),Color.WHITE);
		
	}
	
	@Test
	public void testPlayerAddition() {
		Player p = new Player(Color.WHITE);
		Coordinate coord = new Coordinate("h8",7,7);
		King k = new King(Color.WHITE,coord,"K");
		
		p.addPiece(k);
		
		ArrayList<Piece> pieces;
		pieces = p.getPieces();
		
		Piece piece = pieces.get(0);
		assertEquals(piece,k);
		
		p.removePiece(piece);
		pieces = p.getPieces();
		assertEquals(pieces.size(),0);
	}
	
	

}
