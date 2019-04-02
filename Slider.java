import java.util.Random;

/**
 * 
 * @author Daniel
 * @version 2.0
 *
 */
public class Slider extends Animal implements StraightMovement
{
	private static boolean isHiding; // Indicates if the animal is hiding or not
	
	
	/**
	 * class constructor which takes one parameter and initializing inherited class variables to default value
	 * 
	 * @param ID <required> used to track slider animal on board
	 */
	public Slider(int ID) 
	{
		trackingID = ID;
		type = "Slider";
		symbol = 'l';
		isHiding = false;
		points = 4;
	} // end constructor Slider
	
	
	/**
	 * 
	 * @return if the animal is hiding or not
	 */
	public static boolean isHiding()
	{
		return isHiding;
	} // end isHiding
	
	
	/**
	 * Update class variable is hiding to indicate animal is hiding 
	 */
	public void hide() 
	{
		setSymbol('<');
		isHiding = true;
	} // end hide
	
	/**
	 * Update class variable is hiding to indicate animal is not hiding
	 */
	public void unHide() 
	{
		setSymbol('l');
		isHiding = false;
	}// end unHide
	
	
	/**
	 * @return the current position the animal wants to move to
	 */
	public int[] getPos() 
	{
		int[] pos = {getRowPos(), getColPos()};
		return pos;
	} // end getPos
	
	
	/**gameGrid.moveAnimals();
	 * 
	 * @param direction indicates direction for movement 
	 * @return new position for hunter to move
	 */
	public int[] move()  
	{	
		Random rand = new Random();
		
		int direction = rand.nextInt(5) + 1;
		
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
				hide();
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
	
} // end class Slider