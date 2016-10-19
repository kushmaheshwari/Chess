package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;



import ChessClasses.*;

import org.junit.Test;


public class ChessSquareButtonTest {

	@Test
	public void testChessSquareButton() {
		ChessSquareButton button = new ChessSquareButton('a','1');
		assertEquals(button.getXCoord(),'a');
		assertEquals(button.getYCoord(),'1');
	}

}
