package UnitTests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import ChessClasses.*;


public class CoordinateTest {
	
	/*
	 * Test whether the coordinate constructor works
	 */


	@Test
	public void testCoordinateConstructor() {
		Coordinate coord = new Coordinate("h8",7,7);
		assertEquals(coord.getCoordinateName(),"h8");
		assertEquals(coord.getX(),7);
		assertEquals(coord.getY(),7);
		

		
	}
	
	/*
	 * Test whether the coordinate isOccupied() method works
	 */
	
	@Test
	public void testCoordinateOcupied() {
		Coordinate coord = new Coordinate("h8",7,7);
		King k = new King(Color.WHITE,coord,"K");
		coord.setPiece(k);
		assertEquals(true,coord.isOccupied());

		
	}

}
