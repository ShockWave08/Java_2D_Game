/**
 * 
 * @author Daniel
 * @version 2.0
 *
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameBoard
{
	private int numRows; // Number of rows on the game board
	private int numCols; // Number of column on the game board
	
	private GamePiece [] [] gameboard; // 2-D array holding game piece objects
	
	private Slider mySlider; // Object of Slider 
	private Skipper mySkipper; // Object of Skipper
	private Spook mySpook; // Object of SPook 
	private int numSliders; // contains the number of Sliders currently on game board
	private int numSkippers; // contains the number of Skippers currently on game board
	private int numSpooks; // contains the number of Skippers currently on the game board
	private int numAnimals; // contains the total number of animals currently on the game board
	
	private Hunter hunter1, hunter2; // Hunter objects
	private int numHunters; // contains the number of hunters currently on the board
	
	ArrayList <AnimalStatus> animalTracker; // ArrayList of containing status of Animals
	private AnimalStatus animalStat; // Object of AnimalStatus
	
	private static boolean isBoardActive; // This indicates if if the game board has active hunters or animals
	
	private static int cycle; // contains the number of cycles per game
	
	private static int rounds; // contains the number of rounds per cycle
	
	private static String phase; // contains the phases during the cycles
	
	private Scanner input;
	
	private Random rand;
	
	
	// initializing static variable
	static 
	{
		rounds = 0;
		phase = "";
	}
	
	
	/*
	 * constructor initializing declared class variables to default value
	 */
	public GameBoard() 
	{
		numRows = numCols = 10;
		numSliders = numSkippers = numSpooks = 6;
		numHunters = 2;
		
		gameboard = new GamePiece[numRows][numCols];
		
		numAnimals = 18;
		
		animalTracker = new ArrayList<AnimalStatus>();
		
		isBoardActive = true;
		
		input = new Scanner(System.in);
		
		rand = new Random();
		
	} // end constructor GameBoard
	
	
	/*
	 * Set up the pieces on the board 
	 */
	public void setupBoard() 
	{
		gameboard [1][9]= new Wall();
		gameboard [0][8]= new Wall();
		gameboard [8][0]= new Wall();
		gameboard [9][1]= new Wall();
		
		String tempName = "";
		
		do 
		{
			System.out.print("Please provide Hunter1's Name: ");
			tempName = input.next();
		}
		while(tempName.isEmpty());
		
		hunter1 = new Hunter(tempName);
		
		tempName = "";
		
		do 
		{
			System.out.print("Please provide Hunter2's Name: ");
			tempName = input.next();
		}
		while(tempName.isEmpty());
		
		hunter2 = new Hunter(tempName);
		
		// assign hunters position on board
		gameboard [0][9] = hunter1;
		hunter1.setPos(0, 9);
		
		gameboard [9][0] = hunter2;
		hunter2.setPos(9, 0);

		
		for(int i = 0; i < numAnimals; i++) 
		{
			if(i < 6) 
			{
				mySlider = new Slider(i);
				addAnimal(mySlider, i);
			}
			
			else if(i < 12) 
			{
				mySkipper = new Skipper(i);
				addAnimal(mySkipper, i);
			}
			
			else if(i < 18) 
			{
				mySpook = new Spook(i);
				addAnimal(mySpook, i);
			}
		}
		
	} // end setupBoard 
		
	
	/**
	 * Add animal to board
	 * 
	 * @param animal used to pass animal object to method
	 * @param animalID used to track animals on board
	 * 
	 * @return true if operations were successful
	 */
	public boolean addAnimal(Animal animal, int animalID) 
	{	
		int randNum1, randNum2;
	
		do 
		{
			randNum1 = rand.nextInt(10);
			randNum2 = rand.nextInt(10);
		}
		while(gameboard [randNum1][randNum2] != null);
		
		animalStat = new AnimalStatus(true, randNum1, randNum2);
		gameboard [randNum1][randNum2] = animal;
		animal.setPos(randNum1, randNum2);
		animalTracker.add(animalStat);
		
		return true;
	} // end addAnimal 
	
	
	/**
	 * 
	 * @param animalID use to track animal ID for removal
	 * @return true if operations were successful
	 */
	public boolean removeAnimal(int animalID) 
	{
		if(animalID > 0 && animalID < numAnimals) 
		{
			for(int i = 0; i < 18; i++) 
			{
				if(gameboard [animalTracker.get(animalID).getX()][animalTracker.get(animalID).getY()] instanceof Animal) 
					{
						gameboard [animalTracker.get(animalID).getX()][animalTracker.get(animalID).getY()] = null;
						animalTracker.get(animalID).setActive(false);
						numAnimals--;
					}
			}
			return true;
		}
		
		else 
			return false;

	} // end removeAnimal
	
	
	/**
	 * Display board with all its game pieces
	 */
	public void displayBoard() 
	{
		for(int row = 0; row < numRows; row++)
		{
			for(int b = 0; b < 71; b++)
			{
				System.out.print("-");
			} // end inner loop 1
			
			System.out.println("");
			
			for(int col = 0; col < numCols; col++)
			{
				if(gameboard [row][col] != null)
					System.out.print("|   " + gameboard[row][col].getSymbol() + "  ");
				else	
					System.out.print("|   " + "   ");
			} // end inner loop 2
			
			System.out.println("|");
			
		} // end outer loop
		
		for(int b = 0; b < 71; b++)
		{
            System.out.print("-");
        } // end for loop
		
		System.out.printf("%n%n");
		
		if(getRounds() > 0) 
		{
			if(getRounds() < 3)
				setPhase("Light");
			else
				setPhase("Dark");
			
			System.out.printf("Cycle  [%d];  Phase  [%s];  Round  [%d]%n", cycle, phase, rounds);
			System.out.printf("Hunters: Fred  [%d]  &  Barney  [%d]%n", hunter1.getEnergyLevel(), hunter2.getEnergyLevel());
			System.out.printf("Animals: Skippers  [x%d];  Spooks  [x%d];   Sliders  [x%d] %n%n", numSkippers, numSpooks, numSliders);
		}
		
	} // end displayBoard
	
	/**
	 * 
	 * @return the number of Hunters still active
	 */
	public int getNumHunters() 
	{
		return numHunters;
	}  // end getNumHunters
	
	/**
	 * 
	 * @return the number of animals still active
	 */
	public int getNumAnimals() 
	{
		return numAnimals;
	} // end getNumAnimals
	
	/**
	 * 
	 * @param cyc indicate the number of cycles
	 */
	public static void setCycle(int cyc) 
	{
		cycle = cyc;
	} // end setCycle
	
	/**
	 * 
	 * @return the number of cycles
	 */
	public static int getCycle() 
	{
		return cycle;
	} // end getCycle
	
	/**
	 * 
	 * @param turns indicates number of rounds in the cycle
	 */
	public static void setRound(int turns) 
	{
		rounds = turns;
	} // end setround
	
	
	
	/**
	 * 
	 * @return the number or rounds at any given time in the cycle
	 */
	public static int getRounds() 
	{
		return rounds;
	} // end getRounds
	
	
	/**
	 * 
	 * @param time indicates the Light or Dark
	 */
	public static void setPhase(String time) 
	{
		phase = time;
	} // end setPhase
	
	/**
	 * 
	 * @return whether it is Light or Dark
	 */
	public static String getPhase() 
	{
		return phase;
	} // end getPhase
	
	/**
	 * 
	 * @param destination indicate hunter coordinates on the game board
	 * @return whether the destination is a valid position or not
	 */
	public boolean validateHunterMove(int[] destination) 
	{
		// boolean result = true;
		
		if(destination[1] < 0 || destination[1] > 9 || destination[0] < 0 || destination[0] > 9) 
		{
			System.out.printf("%n%nYou're tring to move beyond the Gameboard%n%n");
			return false;
		}
		
		else if(gameboard[destination[0]][destination[1]] instanceof Wall) 
		{
			System.out.printf("%n%nCannot Move into Wall%n%n");
			return false;
		}
				
		else if(gameboard[destination[0]][destination[1]] instanceof Hunter) 
		{
			System.out.printf("%n%nCant't Move into another hunter's position%n%n");
			return false;
		}
		
		else if(gameboard[destination[0]][destination[1]] instanceof Slider && Slider.isHiding() == true) 
		{
			System.out.printf("%n%nA slider is hidden here, choose a next direction%n%n");
			return false;
		}

		else if(gameboard[destination[0]][destination[1]] == null || gameboard[destination[0]][destination[1]] instanceof Animal) 
		{
			return true;
		}
		
		return false;
			
	} // end validateHunterMove
	
	
	/**
	 * Relocate hunters on the board
	 */
	public void moveHunters() 
	{
		input = new Scanner(System.in);
		
		int direction = 0;
		
		// player 1
		if(hunter1.getEnergyLevel() > 0)
		{
			int[] newPos = new int[2];
			
			do 
			{
			
				// Enter Direction for hunter 2
				System.out.println("Which direction would you like hunter 1 to move?");
				System.out.printf("%n%n1 = North %n2 = South %n3 = East %n4 = West %n5 = NorthEast %n6 = NorthWest %n7 = SouthEast %n8 = SouthWest %n%n");
				System.out.print("Choose Your destiny: ");
				
				direction = input.nextInt();
				
				newPos = hunter1.move(direction);		
			}
			while(validateHunterMove(newPos) == false);
			
			// if hunter is in animal position, move animal
			if(gameboard[newPos[0]][newPos[1]] instanceof Slider)
			{
				hunter1.setEnergyLevel(hunter1.getEnergyLevel() + mySlider.getPoints());
				removeAnimal(((Animal)gameboard[newPos[0]][newPos[1]]).getTrackingID());
				numSliders--;
			}
			
			else if(gameboard[newPos[0]][newPos[1]] instanceof Skipper) 
			{
				hunter1.setEnergyLevel(hunter1.getEnergyLevel() + mySkipper.getPoints());
				removeAnimal(((Animal)gameboard[newPos[0]][newPos[1]]).getTrackingID());
				numSkippers--;
			}
			
			else if(gameboard[newPos[0]][newPos[1]] instanceof Spook) 
			{
				hunter1.setEnergyLevel(hunter1.getEnergyLevel() + mySpook.getPoints());
				removeAnimal(((Animal)gameboard[newPos[0]][newPos[1]]).getTrackingID());
				numSpooks--;
			}
			
			
			hunter1.setEnergyLevel(hunter1.getEnergyLevel() - 1); // decrement hunter energy level after each successful move
			
			gameboard [newPos[0]][newPos[1]] = hunter1;
			gameboard [hunter1.getRowPos()][hunter1.getColPos()] = null; 
			hunter1.setPos(newPos[0],newPos[1]);
			
		}  
		
		
		direction = 0; // reset direction
		
		// player 2
		if(hunter2.getEnergyLevel() > 0)
		{	
			int[] newPos = new int[2];
			
			do 
			{
				// Enter Direction for hunter 2
				System.out.println("Which direction would you like hunter 2 to move?");
				System.out.printf("%n%n1 = North %n2 = South %n3 = East %n4 = West %n5 = NorthEast %n6 = NorthWest %n7 = SouthEast%n8 = SouthWest %n%n");
				System.out.print("Choose Your destiny: ");
				
				direction = input.nextInt();
				
				newPos = hunter2.move(direction);
			}
			while(validateHunterMove(newPos) == false);
		
			// if hunter is in animal position, move animal
			if(gameboard[newPos[0]][newPos[1]] instanceof Slider)  
			{
				hunter2.setEnergyLevel(hunter2.getEnergyLevel() + mySlider.getPoints());
				removeAnimal(((Animal)gameboard[newPos[0]][newPos[1]]).getTrackingID());
				numSliders--;
				numAnimals--;
			}
			
			else if(gameboard[newPos[0]][newPos[1]] instanceof Skipper) 
			{
				hunter2.setEnergyLevel(hunter2.getEnergyLevel() + mySkipper.getPoints());
				removeAnimal(((Animal)gameboard[newPos[0]][newPos[1]]).getTrackingID());
				numSkippers--;
				numAnimals--;
			}
			
			else if(gameboard[newPos[0]][newPos[1]] instanceof Spook) 
			{
				hunter2.setEnergyLevel(hunter2.getEnergyLevel() + mySpook.getPoints());
				removeAnimal(((Animal)gameboard[newPos[0]][newPos[1]]).getTrackingID());
				numSpooks--;
				numAnimals--;
			}
			
			hunter2.setEnergyLevel(hunter2.getEnergyLevel() - 1); 
			
			gameboard [newPos[0]][newPos[1]] = hunter2;
			gameboard [hunter2.getRowPos()][hunter2.getColPos()] = null; 
			hunter2.setPos(newPos[0],newPos[1]);
			
		}
		
		if(numHunters == 0 || numAnimals == 0)
			setIsBoardActive(false);
		
	}// end moveHunters
	
	/**
	 * 
	 * @param status sets a boolean value which determines if the board has active hunters or not
	 */
	public static void setIsBoardActive(boolean status) 
	{
		isBoardActive = status;
	} // end setIsBoardACtive
	
	/**
	 * 
	 * @return if the board still has active hunters or animals
	 */
	public static boolean getIsBoardActive() 
	{
		return isBoardActive;
	} // end getIsBordactive

	
	/**
	 * Validate the destination before repositioning 
	 * 
	 * @param animal
	 * @param destination
	 * @return whether the animals intended position is valid or not
	 */
	public boolean validateAnimalMove(Animal animal, int[] destination) 
	{	
		
		if(destination[1] < 0 || destination[1] > 9 || destination[0] < 0 || destination[0] > 9) 
		{
			return false;
		}
		
		else if((destination[0] == 0 && destination[1] == 9) || (destination[0] == 9 && destination[1] == 0)) 
		{
			return false;
		}
		
		else if(getPhase() == "Dark" && gameboard[destination[0]][destination[1]] instanceof Hunter) 
		{
			if(animal instanceof Spook)
				return true;
			
			else if(animal instanceof Skipper)
				return true;
			
			return true;
		}
		
		else if(gameboard[destination[0]][destination[1]] == null) 
		{	
			return true;
		}
		
		return false;
	} // end validateAnimalMove
	
	
	/**
	 * 
	 * Reloocate the animal on the gameboard					System.out.println("skipped");
	 * 
	 * @param currAnimal indicating current animal to be moved
	 */
	public void autoMoveAnimal(Animal currAnimal) 
	{	
		
		int[] newPos = currAnimal.move();
		
		//int hidden = 0;
				
		if(validateAnimalMove(currAnimal, newPos) == true) 
		{
			if(gameboard[newPos[0]][newPos[1]] instanceof Hunter && getPhase() == "Dark") 
			{
				if(gameboard[newPos[0]][newPos[1]].getSymbol() == hunter1.getSymbol()) 
					{
						gameboard [newPos[0]][newPos[1]] = currAnimal;
						gameboard [hunter1.getRowPos()][hunter1.getColPos()] = null; 
						currAnimal.setPos(newPos[0],newPos[1]);
						numHunters--;
						hunter1.setEnergyLevel(0);
						System.out.printf("%n%nHunter 1 was eaten by %s%n%n", currAnimal.getType());
					}
				else if(gameboard[newPos[0]][newPos[1]].getSymbol() == hunter2.getSymbol()) 
				{
					gameboard [newPos[0]][newPos[1]] = currAnimal;
					gameboard [hunter2.getRowPos()][hunter2.getColPos()] = null; 
					currAnimal.setPos(newPos[0],newPos[1]);
					numHunters--;
					hunter2.setEnergyLevel(0);
					System.out.printf("%n%nHunter 2 was eaten by %s%n%n", currAnimal.getType());
				}
			}
			else
			{
				gameboard[newPos[0]][newPos[1]] = currAnimal;
				gameboard[currAnimal.getRowPos()][currAnimal.getColPos()] = null;
				currAnimal.setPos(newPos[0], newPos[1]);
				animalTracker.get(currAnimal.getTrackingID()).setPosition(newPos[0], newPos[1]);
			}
			
			
			if(currAnimal instanceof Slider && Slider.isHiding() == true) 
			{
				mySlider.unHide();
			}
			
			// skipper skips every other round
			if(currAnimal instanceof Skipper && getRounds() % 2 == 0) 
			{
				mySkipper.updateHasSkipped();
			}
			
			int randNum = rand.nextInt(5) + 1;
			int skipped = 0;
			
			
			if(currAnimal instanceof Spook) 
			{
				if(getRounds() % randNum == 0 || getRounds() % randNum == 0)
				{
					mySpook.updateHasSkipped();
					skipped++;
				}
			}
			else if(skipped == 2) 
			{
				skipped = 0;
			}
		}
		
	} // end automoveAnimal
	
	
	/**
	 * Move each animal that is active
	 */
	public void moveAnimals() 
	{
		for(int a = 0; a < 18; a++) 
		{
			if(animalTracker.get(a).isActive() == true)
			{	
				System.out.println((Animal)gameboard[animalTracker.get(a).getX()][animalTracker.get(a).getY()]);
				autoMoveAnimal((Animal)gameboard[animalTracker.get(a).getX()][animalTracker.get(a).getY()]);
			}
		}
	} // moveAnimals
	
	
	/**
	 * Check Game Status to determine Winner and Loser
	 */
	public void checkGameStatus() 
	{
		if(GameBoard.getIsBoardActive() == false && numAnimals == 0) 
		{
			if(hunter1.getEnergyLevel() == 0 && hunter2.getEnergyLevel() == 0) 
			{
				System.out.printf("YOU BOTH LOST!!");
				System.exit(0);
			}
			
			else if(hunter1.getEnergyLevel() > 0 && hunter2.getEnergyLevel() == 0) 
			{
				System.out.printf("Hunter 1 WON!!");
				System.exit(0);
			}
			
			else if(hunter1.getEnergyLevel() > hunter2.getEnergyLevel()) 
			{
				System.out.printf("Hunter 1 WON!!");
				System.exit(0);
			}
			
			else if(hunter2.getEnergyLevel() > hunter1.getEnergyLevel()) 
			{
				System.out.printf("Hunter 2 WON!!");
				System.exit(0);
			}
			
			else if(hunter2.getEnergyLevel() > 0 && hunter1.getEnergyLevel() == 0) 
			{
				System.out.printf("Hunter 2 WON!!");
				System.exit(0);
			}
		}
		
		else if(GameBoard.getIsBoardActive() == true && numHunters == 0) 
		{
			System.out.printf("YOU BOTH LOST!!");
			System.exit(0);
		}
		
		else if(GameBoard.getIsBoardActive() == true && getCycle() == 3 && getRounds() == 20) 
		{
			System.out.printf("YOU BOTH LOST!!");
			System.exit(0);
		}
		
	} // end checkGameStatus
	
} // end class GameBoard




