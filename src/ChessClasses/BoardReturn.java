package ChessClasses;

/**
 * This class constructs objects that return errors and messages back to the controller
 */

public class BoardReturn {
	private boolean error;
	private String message;
	
	public BoardReturn(boolean error, String message){
		this.error = error;
		this.message = message;	
	}
	
	public boolean getError(){
		return this.error;
	}
	
	public String getMessge(){
		return this.message;
	}

}
