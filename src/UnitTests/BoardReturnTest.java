package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;


import java.awt.Color;

import ChessClasses.*;

import ChessClasses.*;

public class BoardReturnTest {

	@Test
	public void testBoardReturn() {
		BoardReturn value = new BoardReturn(true,"CheckMate");
		assertEquals(value.getError(),true);
		assertEquals(value.getMessge(),"CheckMate");
	}

}
