/**
 * 
 * @author Daniel
 * @version 2.0
 *
 */
public abstract class Animal extends GamePiece
{
	protected int trackingID; // Contains ID used to track this animal on board
	protected int points; // Contains the points associated with animal
	protected boolean isActive; // Contains true if animal is alive and false otherwise
	protected int [] newPos; // Contains the coordinate pair (row, col) for the animals's destination
	
	
	
	/**
	 * class constructor initializing inherited class variables to default value
	 */
	public Animal() 
	{
		type = "Animal";
		symbol = '?';
		newPos = new int[2];
	} // end class constructor
	
	
	/**
	 * Set the tracking ID
	 * 
	 * @param trackID used to set animalID
	 */
	public void setTrackingID(int trackID) 
	{
		trackingID = trackID;
	} // end setTrackingID
	
	
	/**
	 * 
	 * @return tracking ID
	 */
	public int getTrackingID() 
	{
		return trackingID;
	} // end getTrackingID
	
	
	/**
	 *Sets the energy points contained by animal
	 * 
	 * @param p used to set points associated with animal 
	 */
	public void setPoints(int p) 
	{
		points = p;
	} // end setPOints
	
	/**
	 * 
	 * @return points allocated to animal
	 */
	public int getPoints() 
	{
		return points;
	} // end getPoints
	
	/**
	 * 
	 * @param status indicates if the animal is alive or not
	 */
	public void setActive(boolean status) 
	{
		isActive = status;
	} // end setActive
	
	/**
	 * Returns whether animal is active or not (true or false)
	 * 
	 * @return whether animal is active or not
	 */
	public boolean isActive() 
	{
		return isActive;
	} // end getisActive
	
	
	/**
	 * 
	 * @return the current position the animal wants to move to
	 */
	public int[] getPos() 
	{	
		int[] pos = {newPos[0], newPos[1]};
		
		return pos;
	} // end getPos
	
	
	/**
	 * 
	 * @return
	 */
	public abstract int[] move();
	
} // end class Animal