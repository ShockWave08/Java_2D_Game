/**
 * 
 * @author Daniel
 * @version 2.0
 *
 */
public class GamePiece
{
	protected String type; // contains the type of game piece
	protected char symbol; // contains the character that will be used to represent the game piece on the grid
	private int rowPos; // contains row in which the game piece will be positioned
	private int colPos; // contains column in which the game piece will be positioned
	
	/**
	 * class constructor initializing class variables with default values
	 */
	public GamePiece() 
	{
		type = "GP";
		symbol = '?';
	} // end constructor GamePiece
	
	
	/**
	 * 
	 * @return type passed to constructor
	 */
	public String getType() 
	{
		return type;
	} // end getType
	
	/**
	 * 
	 * @param sym
	 */
	public void setSymbol(char sym) 
	{
		symbol = sym;
	}
	
	/**
	 * 
	 * @return symbol passed to constructor
	 */
	public char getSymbol() 
	{
		return symbol;
	} // end getSymbol
	
	/**
	 * Set the row and column coordinates
	 * 
	 * @param r - <required> indicates row in which animal will be placed on the grid
	 * @param c - <required> indicates column in which animal will be placed on the grid
	 */
	public void setPos(int r, int c) 
	{
		rowPos = r;
		colPos = c;
	} // end setPos
	
	/**
	 * 
	 * @return the position of represented by the row on the two dimension board
	 */
	public int getRowPos() 
	{
		return rowPos;
	} // end getRosPos
	
	/**
	 * 
	 * @return the position represented by the column on the two dimension board
	 */
	public int getColPos() 
	{
		return colPos;
	}// end getColPos 
	
} // end class GamePiece 
