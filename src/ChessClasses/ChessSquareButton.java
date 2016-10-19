package ChessClasses;

import javax.swing.JButton;

/**
 * This class extends the JButton and is used as a square for the GUI.
 * This adds the 'a-h' and '1-8' attributes for the GUI because my game logic uses it.
 */

public class ChessSquareButton extends JButton{
	char xCoordinate;//a-h
    char yCoordinate;//1-8


    public ChessSquareButton(char xCoord, char yCoord){
        super();
        this.xCoordinate = xCoord;
        this.yCoordinate = yCoord;
    }
    
    public char getXCoord(){
    	return xCoordinate;
    }
    
    public char getYCoord(){
    	return yCoordinate;
    }
    
    public void setXCoords(char x){
    	this.xCoordinate = x;
    }
    
    public void setYCoords(char y){
    	this.yCoordinate = y;
    }

}
