import java.util.Random;

/**
 * 
 * @author Daniel
 * @version 2.0
 *
 */

public class Skipper extends Animal implements StraightMovement
{
	private static boolean hasSkipped; // Indicates if the animal skipped on the last round or not
	
	/**
	 * class constructor which takes one parameter and initializing inherited class variables to default value
	 * 
	 * @param ID <required> used to track Skipper animal on board
	 */
	public Skipper(int ID) 
	{
		trackingID = ID;
		type = "Skipper";
		symbol = 'k';
		hasSkipped = false;
		points = 4;
	} // end constructor Skipper
	
	/**
	 * 
	 * @return if animal skipped last round or not
	 */
	public boolean hasSkipped() 
	{
		return hasSkipped;
	} // end hasSkipped
	
	/**
	 * Toggle and update the hasSKipped class value
	 */
	public void updateHasSkipped() 
	{
		if(hasSkipped == false)
			hasSkipped = true;
		else
			hasSkipped = false;
	} // end updateHasSkipped
	
	/**
	 * 
	 * @return the current position the animal wants to move to
	 */
	public int[] getPos() 
	{
		int[] pos = {getRowPos(), getColPos()};
		return pos;
	}
	
	/**
	 * 
	 * @param direction indicates direction for movement 
	 * @return new position for hunter to move
	 */
	public int[] move()  
	{	
		Random rand = new Random();
		
		updateHasSkipped(); // update has skipped
		
		int direction;
		
		if(hasSkipped == true)
			direction = rand.nextInt(4) + 1;
		else
			direction = rand.nextInt(8) + 1;
		
		switch(direction) 
		{
			case 1:
				moveNorth();
				break;
			
			case 2:
				moveSouth();
				break;
				
			case 3:
				moveEast();
				break;
				
			case 4:
				moveWest();
				break;
				
			case 5:
				moveNorth();
				moveNorth();
				break;
			
			case 6:
				moveSouth();
				moveNorth();
				break;
				
			case 7:
				moveEast();
				moveNorth();
				break;
				
			case 8:
				moveWest();
				moveNorth();
				break;
			
				
			default:
				
		}
	
		return newPos;
	}
	
	/**
	 * Move Northward on the board
	 */
	@Override
	public void moveNorth() 
	{
		newPos[0] = getRowPos() - 1;
		newPos[1] = getColPos() + 0;
	}
	
	
	/**
	 * Move Southward on the board
	 */
	@Override
	public void moveSouth() 
	{
		newPos[0] = getRowPos() + 1;
		newPos[1] = getColPos() + 0;
	}
	
	
	/**
	 * Move Eastward on the board
	 */
	@Override
	public void moveEast() 
	{
		newPos[0] = getRowPos() + 0;
		newPos[1] = getColPos() + 1;
	}
	
	
	/**
	 * Move Westward on the board
	 */
	@Override
	public void moveWest() 
	{
		newPos[0] = getRowPos();
		newPos[1] = getColPos() - 1; 
	}
	
} // end class Skipper
