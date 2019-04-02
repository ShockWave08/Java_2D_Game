/**
 * 
 * @author Daniel
 * @version 2.0
 *
 */
public class AnimalStatus
{
	private int xPos; // Used to indicate the x-coordinate in which the animal is located on the game board
	private int yPos; // Used to indicate the x-coordinate in which the animal is located on the game board
	private boolean isActive; // Indicates if the animal is alive or not
	
	/**
	 * constructor which takes three parameters and initializing inherited and class variables to default value
	 * 
	 * @param status - used to indicate if the animal is active or not
	 * @param x - used to indicate the x coordinate of animal located on the game board
	 * @param y - used to indicate the y coordinate of animal located on the game board
	 */
	public AnimalStatus(boolean status, int x, int y) 
	{
		isActive = status;
		xPos = x;
		yPos = y;
	} // end constructor AnimalStatus
	
	/**
	 * Updates the value of isActive
	 * 
	 * @param status - indicates the status of animal
	 */
	public void setActive(boolean status) 
	{
		isActive = status;
	} // end setActive
	
	/**
	 * 
	 * @return whether the animal is alive or not
	 */
	public boolean isActive() 
	{
		return isActive;
	} // end isActive
	
	/**
	 * 
	 * @param x set the x coordinate of animal
	 * @param y set the y coordinate of animal
	 */
	public void setPosition(int x, int y) 
	{
		xPos = x;
		yPos = y;
	} // end setPosition
	
	
	/**
	 * 
	 * @return the x coordinate
	 */
	public int getX() 
	{
		return xPos;
	} // end getX
	
	/**
	 * 
	 * @return the y coordinate
	 */
	public int getY() 
	{
		return yPos;
	} // end getY

} // end class AnimalStatus
