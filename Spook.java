import java.util.Random;

/**
 * 
 * @author Daniel
 * @version 2.0
 *
 */
public class Spook extends Animal implements StraightMovement, DiagonalMovement
{
	private static boolean hasSkipped; // Indicates if the animal skipped on the last round or not

	/**
	 * constructor which takes one parameter and initializing inherited class variables to default value
	 * 
	 * @param ID <required> used to track Spook animal on board
	 */
	public Spook(int ID) 
	{
		trackingID = ID;
		type = "spook";
		symbol = 'p';
		hasSkipped = false;
		points = 6;
	} // end constructor Spook
	
	/**
	 * 
	 * @return if animal has skipped the last round or not
	 */
	public boolean hasSkipped() 
	{
		return hasSkipped;
	} // end hasSkipped
	
	/**
	 * Toggles and updates the hasSkipped value
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
		
		int direction;
		
		if(hasSkipped == true)
			direction = rand.nextInt(8) + 1;
		else
			direction = rand.nextInt(16) + 1;
		
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
				moveNorthEast();
				break;
				
			case 6:
				moveNorthWest();
				break;
				
			case 7:
				moveSouthEast();
				break;
				
			case 8:
				moveSouthWest();
				break;
				
			case 9:
				moveNorthNorth();
				break;
				
			case 10:
				moveEastEast();
				break;
				
			case 11:
				moveWestWest();
				break;
				
			case 12:
				moveSouthSouth();
				break;
			
			case 13:
				moveNorthEast();
				moveNorthEast();
				break;
				
			case 14:
				moveNorthWest();
				moveNorthWest();
				break;
				
			case 15:
				moveSouthEast();
				moveSouthEast();
				break;
				
			case 16:
				moveSouthWest();
				moveSouthWest();
				break;
				
			default:
				
		}
			return newPos;
			
	} // end move()
	
	
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
	
	/**
	 * Move NorthEastWard on the board
	 */
	@Override
	public void moveNorthEast() 
	{
		newPos[0] = getRowPos() - 1;
		newPos[1] = getColPos() + 1;
	}
	
	/**
	 * Move SouthEastWard on the board
	 */
	public void moveSouthEast() 
	{
		newPos[0] = getRowPos() + 1;
		newPos[1] = getColPos() + 1;
	}
	
	/**
	 * Move SouthWestWard on the board
	 */
	@Override
	public void moveSouthWest() 
	{
		newPos[0] = getRowPos() + 1;
		newPos[1] = getColPos() - 1;
	}
	
	/**
	 * Move NorthWestWard on the board
	 */
	@Override
	public void moveNorthWest() 
	{
		newPos[0] = getRowPos() - 1;
		newPos[1] = getColPos() - 1;
	}
	
	
	/**
	 * 
	 */
	public void moveNorthNorth() 
	{
		moveNorth();
		moveNorth();
	}
	
	/**
	 * 
	 */
	public void moveSouthSouth() 
	{
		moveSouth();
		moveSouth();
	}
	
	/**
	 * 
	 */
	public void moveEastEast() 
	{
		moveEast();
		moveEast();
	}
	
	/**
	 * 
	 */
	public void moveWestWest() 
	{
		moveWest();
		moveWest();
	}
	

} // end class Spook 
