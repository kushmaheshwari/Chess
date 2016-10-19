 package ChessClasses;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * This class represents the GUI for the game
 */

public class ChessGUI{
	//Controller for the gui
	private Controller gameController;
	 
	 //GUI parts
	 private JFrame Window;
	 
	 private JPanel boardPanel;	 
	 private JPanel buttonPanel;
	   
	 private JButton forfeitButton;
	 private JButton restartButton;
	 private JButton undoButton;
	 private JLabel scoreLabel;
	 
	 private static ChessSquareButton[][] chessBoardSquares;

	 
	 //The 2 coordinates that are clicked
	 String startCoord = null;
	 String endCoord = null;
	 
	
	 //Number of clicks
	 private int count;
	//starting color
	 private Color color = Color.WHITE;
	 
	 //A map for unicode strings
	 private static Map<String,String> pieces = new HashMap<String,String>();
	 
	 //For undoing a move
	 private static String pieceFirst;
	 private static String pieceSecond;
	 private static int startX;
	 private static int startY;
	 private static int endX;
	 private static int endY;
	 
	 //Answer to initial question
	 private String ans;
	 
	 
	 /**
	  * Initialize the whole frame
	  */
	
	public ChessGUI(){
		Window = new JFrame();
		Window.setLayout(new BorderLayout());
		
		Window.setSize(700, 700);
		Window.setVisible(true);
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //board panel which is the chess board
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8,8));
        boardPanel.setVisible(true);
        boardPanel.setPreferredSize(new Dimension(700, 600));
        Window.add(boardPanel,BorderLayout.NORTH);
        
        //button panel which has all the buttons and scores
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        boardPanel.setVisible(true);
        Window.add(buttonPanel,BorderLayout.SOUTH);
        
        //all 3 buttons that are part of the button panel
        forfeitButton = new JButton("Forfeit");
        buttonPanel.add(forfeitButton, BorderLayout.WEST);
        
        restartButton = new JButton("Restart");
        buttonPanel.add(restartButton, BorderLayout.EAST);
        
        undoButton = new JButton("Undo Move");
        buttonPanel.add(undoButton, BorderLayout.SOUTH);

        
        //initializing Label
        scoreLabel = new JLabel("White: 0 | Black: 0");
        scoreLabel.setVisible(true);
        buttonPanel.add(scoreLabel, BorderLayout.CENTER);

        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        
      //Ask for custom pieces
        ans = JOptionPane.showInputDialog("Do you want custom pieces?");
        
        String undos = JOptionPane.showInputDialog("How many undos per person?");
        int numUndos = Integer.parseInt(undos);
        
        if(ans.equals("yes")){
        	gameController = new Controller(true,numUndos,ans);
        }else{
        	gameController = new Controller(false,numUndos,ans);
        }
        
        chessBoardSquares = new ChessSquareButton[8][8];
        initializeSquares();
        
        if(ans.equals("yes")){
        	initializePieces(true);
        }else{
        	initializePieces(false);
        }
        
        //initialize buttons and count
        initializeForfeitButton(forfeitButton);
        initializeRestartButton(restartButton);
        initUndoMoveButton(undoButton);
        count = 0;

    }
	
	static{
		pieces.put("WK","\u2654");
		pieces.put("WQ","\u2655");
		pieces.put("WR","\u2656");
		pieces.put("WN","\u2658");
		pieces.put("WB","\u2657");
		pieces.put("WP","\u2659");
		pieces.put("BK","\u265A");
		pieces.put("BQ","\u265B");
		pieces.put("BR","\u265C");
		pieces.put("BN","\u265E");
		pieces.put("BB","\u265D");
		pieces.put("BP","\u265F");
		pieces.put("WA","\u25A1");
		pieces.put("WC","\u25CB");
		pieces.put("BA","\u25A0");
		pieces.put("BC","\u25CF");
	}
	/**
	 * Initializes Forfeit button and logic
	 */
	
	private void initializeForfeitButton(JButton forfeitButton) {
		forfeitButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(color == Color.WHITE){
		        	gameController.incrementBlackScore();
		        }else{
		        	gameController.incremementWhiteScore();
		        }
		        
		        scoreLabel.setText("White: " + gameController.getWhiteScore() + " |" + "Black: " + gameController.getBlackScore());
		        gameController.reset();
		        if(ans.equals("yes")){
			        initializePieces(true);
		        }else{
			        initializePieces(false);
		        }
		        color = Color.WHITE;
		        
		    }
		});
    }
	
	/**
	 * Initialize Undo Move button logic 
	 */
	
	private void initUndoMoveButton(JButton undoButton){
		undoButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(color == Color.WHITE && gameController.getWhiteUndo() < gameController.getNumUndos()){
		    		gameController.undo();
			    	undoMove();
			    	gameController.incrementWhiteUndo();
		    	}else if (color == Color.BLACK && gameController.getBlackUndo() < gameController.getNumUndos()){
		    		gameController.undo();
			    	undoMove();
			    	gameController.incrementBlackUndo();
		    	}else{
		    		JOptionPane.showMessageDialog(null,
		                    "You have used your Undo move already",
		                    "Message", JOptionPane.INFORMATION_MESSAGE);
		    	}
		    	
		    }
		});
	}
	
	/**
	 * Initialize Restart Button logic  
	 */
	
		private void initializeRestartButton(JButton restartButton) {
			restartButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	gameController.resetBlackScore();
			    	gameController.resetWhiteScore();
			        scoreLabel.setText("White: " + gameController.getWhiteScore() + " |" + "Black: " + gameController.getBlackScore());
			    	gameController.reset();
			        if(ans.equals("yes")){
			        	initializePieces(true);
			        }else{
				        initializePieces(false);
			        }
			        color = Color.WHITE;
			    }
			});
	    }
		
		/**
		 * Initialize all squares and click logic
		 */
		
		private void initializeChessButton(JButton ChessSquareButton) {
			ChessSquareButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        Object b = e.getSource();
			        ChessSquareButton button = (ChessSquareButton)b;
			        
			        char x = button.getXCoord();
			        char y = button.getYCoord();
			        
			        if(count%2 == 0){
			            startCoord = new StringBuilder().append(x).append(y).toString();
			        }else{
			        	endCoord = new StringBuilder().append(x).append(y).toString();
			        }
			        count++;
			        if(startCoord != null && endCoord!=null){
			        	boolean val = gameController.play(startCoord,endCoord,color);
			        	if(val){
			        		play(startCoord,endCoord);
			        	}
			        	startCoord = null;
			        	endCoord = null;
			        }
			    }
			});
	    }
		
		
		/**
		 * The controller calling the move made on the GUI 
		 */
	 
		private void play(String start, String end){
			if(color == Color.WHITE){
				color = Color.BLACK;
			}else{
				color = Color.WHITE;
			}
			makeMove(start,end);
			scoreLabel.setText("White: " + gameController.getWhiteScore() + " |" + "Black: " + gameController.getBlackScore());
			if(gameController.getCheckmate()){
				if(ans.equals("yes")){
		        	initializePieces(true);
		        }else{
			        initializePieces(false);
		        }
		        color = Color.WHITE;
		        gameController.resetCheckMate();
			}
			
			
		}
	
		/**
		 * Tells the GUI to make the same move as the controller
		 */
	
		private static void makeMove(String start, String end){
			
			String piece = null;
			
			for(int i = 0;i < chessBoardSquares.length;i++){
				for(int j = 0;j < chessBoardSquares[0].length;j++){
					ChessSquareButton b = chessBoardSquares[i][j];
					if(b.getXCoord() == start.charAt(0) && b.getYCoord() == start.charAt(1)){
						piece = chessBoardSquares[i][j].getText();
						chessBoardSquares[i][j].setText("");
						startX = i;
						startY = j;
					}
					
				}
			}
		
		
			pieceFirst = piece;
			
			for(int i = 0;i < chessBoardSquares.length;i++){
				for(int j = 0;j < chessBoardSquares[0].length;j++){
					ChessSquareButton b = chessBoardSquares[i][j];
					if(b.getXCoord() == end.charAt(0) && b.getYCoord() == end.charAt(1)){
						pieceSecond = chessBoardSquares[i][j].getText();
						chessBoardSquares[i][j].setText(piece);
						chessBoardSquares[i][j].setFont(new Font("Verdana", Font.BOLD, 30));
						endX = i;
						endY = j;
					}
					
				}
			}
			
		}
	
	/**
	 * Tells the GUI to undo the move 
	 */
	
	
		private void undoMove(){
			chessBoardSquares[startX][startY].setText(pieceFirst);
			chessBoardSquares[endX][endY].setText(pieceSecond);
			if(color == Color.WHITE){
				color = Color.BLACK;
			}else{
				color = Color.WHITE;
			}
			
		}

		
		/**
		 * Set the chess squares to their background and initialize them with the correct code. Give the squares the extra variables(a-h,1-8)
		 */
		private void initializeSquares(){
			char num = '8';//for naming vertical part of square
			char c = 'a';//for naming horizontal part of square
			
			
			for(int i = 0;i< chessBoardSquares.length;i++){
				c = 'a';
				for(int j= 0;j < chessBoardSquares[0].length;j++){
					ChessSquareButton p = chessBoardSquares[i][j];
					p = new ChessSquareButton(c,num);
					if((i+j)%2 == 0){
						p.setBackground(Color.GRAY);
					}else{
						p.setBackground(Color.WHITE);
					}
					p.setOpaque(true);
					p.setBorderPainted(false);
					
					initializeChessButton(p);
					
					chessBoardSquares[i][j] = p;
					boardPanel.add(p);
					c++;
				}
				num--;
			}
		}
		
		
		
		/**
		 * Puts the proper pieces on the squares
		 */
		
		private void initializePieces(boolean custom){
			
		
			for(int i = 2;i < 6;i++){
				for(int j = 0;j < chessBoardSquares[2].length;j++){
					chessBoardSquares[i][j].setText("");
				}
			}
			chessBoardSquares[0][0].setText(pieces.get("BR"));
			chessBoardSquares[0][0].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[0][1].setText(pieces.get("BN"));
			chessBoardSquares[0][1].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[0][2].setText(pieces.get("BB"));
			chessBoardSquares[0][2].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[0][3].setText(pieces.get("BQ"));
			chessBoardSquares[0][3].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[0][4].setText(pieces.get("BK"));
			chessBoardSquares[0][4].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[0][5].setText(pieces.get("BB"));
			chessBoardSquares[0][5].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[0][6].setText(pieces.get("BN"));
			chessBoardSquares[0][6].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[0][7].setText(pieces.get("BR"));
			chessBoardSquares[0][7].setFont(new Font("Verdana", Font.BOLD, 30));
			///////////////////////////////
			chessBoardSquares[7][0].setText(pieces.get("WR"));
			chessBoardSquares[7][0].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[7][1].setText(pieces.get("WN"));
			chessBoardSquares[7][1].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[7][2].setText(pieces.get("WB"));
			chessBoardSquares[7][2].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[7][3].setText(pieces.get("WQ"));
			chessBoardSquares[7][3].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[7][4].setText(pieces.get("WK"));
			chessBoardSquares[7][4].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[7][5].setText(pieces.get("WB"));
			chessBoardSquares[7][5].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[7][6].setText(pieces.get("WN"));
			chessBoardSquares[7][6].setFont(new Font("Verdana", Font.BOLD, 30));
			chessBoardSquares[7][7].setText(pieces.get("WR"));
			chessBoardSquares[7][7].setFont(new Font("Verdana", Font.BOLD, 30));
			
			///////////////////////
			for(int i = 0;i < 8;i++){
				if(i == 3 && custom){
					chessBoardSquares[1][i].setText(pieces.get("BC"));
					chessBoardSquares[1][i].setFont(new Font("Verdana", Font.BOLD, 30));
				}else if(i == 4 && custom){
					chessBoardSquares[1][i].setText(pieces.get("BA"));
					chessBoardSquares[1][i].setFont(new Font("Verdana", Font.BOLD, 30)); 
				}else{
					chessBoardSquares[1][i].setText(pieces.get("BP"));
					chessBoardSquares[1][i].setFont(new Font("Verdana", Font.BOLD, 30));
				}
			}
	
			////////////////////////////////////
			for(int i = 0;i < 8;i++){
				if(i == 3 && custom){
					chessBoardSquares[6][i].setText(pieces.get("WC"));
					chessBoardSquares[6][i].setFont(new Font("Verdana", Font.BOLD, 30));
				}else if(i == 4 && custom){
					chessBoardSquares[6][i].setText(pieces.get("WA"));
					chessBoardSquares[6][i].setFont(new Font("Verdana", Font.BOLD, 30));
				}else{
					chessBoardSquares[6][i].setText(pieces.get("WP"));
					chessBoardSquares[6][i].setFont(new Font("Verdana", Font.BOLD, 30));
				}
			}
			
		}
	}
