/**
 * 
 * @author Daniel
 * @version 2.0
 *
 */
public class Hunter extends GamePiece implements StraightMovement, DiagonalMovement
{
	private int energyLevel; // Indicates current energy level of Hunter
	final private int MAXENERGY = 20; // Constant used to indicate maximum energy of Hunter
	private String name; // Character chosen by player for hunter
	
	private int[] newPos = new int[2];
	
	
	/**
	 * 
	 * class constructor which takes one parameter and initializing inherited class variables to default value
	 * 
	 * @param n <required> hunter's name
	 */
	public Hunter(String n) 
	{
		type = "hunter";
		name = n;
		symbol = name.toUpperCase().charAt(0);
		energyLevel = 20;
	}
	
	/**
	 * Set the energy level 
	 * 
	 * Energy level must not exceed 20 
	 * 
	 * @param e  <required>  used to set Hunter's energy level
	 */
	public void setEnergyLevel(int e) 
	{
		if(energyLevel > MAXENERGY)
			energyLevel = MAXENERGY;
		else 
			energyLevel = e;
		
	} // end setEnergyLevel
	
	/**
	 * 
	 * @return the energy level
	 */
	public int getEnergyLevel() 
	{
		return energyLevel;
	} // end getEnergyLevel
	
	
	/**
	 * Set the hunters name
	 * 
	 * @param n character used to represent Hunter
	 */
	public void setName(String n) 
	{
		name = n;
	} // end setName
	
	/**
	 * 
	 * @return return the hunter's name
	 */
	public String getName() 
	{
		return name;
	}// end getName
	
	
	/**
	 * 
	 * @return the current position of the hunter
	 */
	public int[] getPos() 
	{
		int[] pos = {getRowPos(), getColPos()};
		return pos;
	} // end getPos
	
	/**
	 * 
	 * @param direction indicates direction for movement 
	 * @return new position for hunter to move
	 */
	public int[] move(int direction)  
	{	
		
		switch(direction) 
		{
			case 1:
			{	moveNorth();
				break;
			}
			case 2:
			{	moveSouth();
			break;
			}
			case 3:
			{	moveEast();
				break;
			}	
			case 4:
			{
				moveWest();
				break;
			}
			case 5:
			{	moveNorthEast();
				break;
			}
			case 6:
			{	moveNorthWest();
				break;
			}	
			case 7:
			{	moveSouthEast();
				break;
			}
			case 8:
			{	moveSouthWest();
				break;
			}
			default:
			{
				//move(direction);
			}
		}
		return newPos;
		
	}// end move()
	
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
	
} // end class Hunter