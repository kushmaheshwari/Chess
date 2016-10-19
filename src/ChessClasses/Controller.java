package ChessClasses;

import java.awt.Color;

import javax.swing.JOptionPane;

/**
 * This class represents the board and game Controller to connect the Gui to the model
 */

public class Controller {
	 private static ChessBoard game;
	 private static boolean isCustom;
	 
	 
	 
	 //All the variables for the board
	 private int whiteScore;
	 private int blackScore;
	 
	 private int numUndos;
	 private int whiteUndo;
	 private int blackUndo;
	 
	 private boolean isCheckMate;
	 
	 private String ans;
	 
	 public Controller(boolean custom,int numUndos, String answer){
		 isCustom = custom;
		if(custom){
			game = new ChessBoard(8,8,true);
		}else{
			game = new ChessBoard(8,8);
		}
		
		this.numUndos = numUndos;
		
		whiteScore = 0;
		blackScore = 0;
		ans = answer;
		isCheckMate = false;
		
	 }
	 
	 public static void reset(){
		 if(isCustom){
			game = new ChessBoard(8,8,true);
		 }else{
			game = new ChessBoard(8,8);
		 }
	 }
	 
	 //All the getters and setters for counts on the board
	 
	 public void incrementBlackScore(){
		 blackScore++;
	 }
	 
	 public int getBlackScore(){
		 return blackScore;
	 }
	 
	 public void resetWhiteScore(){
		 whiteScore = 0;
	 }
	 
	 public void resetBlackScore(){
		 blackScore = 0;
	 }
	 
	 public void incremementWhiteScore(){
		 whiteScore++;
	 }
	 
	 public int getWhiteScore(){
		 return whiteScore;
	 }
	 
	 public void incrementWhiteUndo(){
		 whiteUndo++;
	 }
	 
	 public void incrementBlackUndo(){
		 blackUndo++;
	 }
	 
	 public int getWhiteUndo(){
		 return whiteUndo;
	 }
	 
	 public int getBlackUndo(){
		 return blackUndo;
	 }
	 
	 public int getNumUndos(){
		 return numUndos;
	 }
	 
	 public boolean getCheckmate(){
		 return isCheckMate;
	 }
	 
	 public void resetCheckMate(){
		 isCheckMate = false;
	 }
	 
	 //Calls play on the Model for the game
	 
	 public boolean play(String start, String end, Color color){
		 BoardReturn returnVal = game.play(start,end,color);
		 
		 if(returnVal.getError() == true){//toggle the player whose turn it is if play method returns true
			 if(returnVal.getMessge() != null && returnVal.getMessge().equals("CheckMate")){
					if(color == Color.WHITE){
						whiteScore++;
					}else{
						blackScore++;
					}
					
					JOptionPane.showMessageDialog(null,
		                    returnVal.getMessge(),
		                    "Message", JOptionPane.INFORMATION_MESSAGE);
					
					isCheckMate = true;
					reset();
			        if(ans.equals("yes")){
			        	reset();
			        }else{
				        reset();
			        }
			        color = Color.WHITE;
			        return true;
			    }
				if(returnVal.getMessge() != null){
					JOptionPane.showMessageDialog(null,
		                    returnVal.getMessge(),
		                    "Message", JOptionPane.INFORMATION_MESSAGE);
					return true;
				}
				return true;
			}else{
				
				JOptionPane.showMessageDialog(null,
	                    returnVal.getMessge(),
	                    "Message", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
	 }
	 
	 
	 public void undo(){
		 game.undo();
	 }
	 
	 

	 
}
