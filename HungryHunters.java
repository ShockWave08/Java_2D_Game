/**
 * 
 * @author Daniel
 * @version 2.0
 *
 */
public class HungryHunters
{
	public static void main(String[] args) 
	{
		System.out.printf("%n%n\t\t\t\t\t\t Welcome to Hungry Hunters%n%n%n");
		
		GameBoard gameGrid = new GameBoard();  // Create GameBoard object
		
		// Set up game pieces on the grid
		gameGrid.setupBoard(); 
		
		// Display grid
		gameGrid.displayBoard(); 
		
		GameBoard.setCycle(1);
		
		while(GameBoard.getIsBoardActive() != false) 
		{
			while(GameBoard.getCycle() != 3) 
			{	
				while(GameBoard.getRounds() != 4)
				{
					if(GameBoard.getRounds() < 3) 
					{
						GameBoard.setRound(GameBoard.getRounds() + 1);
						gameGrid.moveHunters();
						gameGrid.moveAnimals();
						gameGrid.displayBoard();
						gameGrid.checkGameStatus();
					}
					
					if(GameBoard.getRounds() > 2) 
					{
						GameBoard.setRound(GameBoard.getRounds() + 1);
						gameGrid.moveHunters();
						gameGrid.moveAnimals();
						gameGrid.displayBoard();
						gameGrid.checkGameStatus();
					}
				}
				
				GameBoard.setCycle(GameBoard.getCycle() + 1); 
				GameBoard.setRound(0); // resetting rounds after each cycle
			}	
		}
		
		
		
	}
} // end class HungryHunters