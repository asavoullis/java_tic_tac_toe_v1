package noughtsAndCrossesV2;

import java.util.Scanner;


public class Human15Player extends HumanPlayer implements NCPlayer 
{
	Scanner theInput;
	Game15Runner theGame;
	NCGrid.SquareStatus theSymbol = NCGrid.SquareStatus.EMPTY;  
	static int coords[][] = {{0,1},{2,0},{1,2},{2,2},{1,1},{0,0},{1,0},{0,2},{2,1}};
			
	public Human15Player(Scanner inputScanner, Game15Runner gameRunner)
	{
		super(inputScanner, gameRunner);
		theInput = inputScanner; //initialise the scanner
		theGame = gameRunner;
		
	}	
	public int[] returnMoves()
	{
		return null;
		
	}
	@Override
	public GridCoordinate getNextMove(NCGrid currentGrid) 
	{
		int theRow = -1;
		int theCol = -1	;
		theGame.displayInstructions();
		theGame.displayGrid(currentGrid);
		int number = theInput.nextInt();
		theRow = coords[number-1][0];
		theCol  = coords[number-1][1];
		return new GridCoordinate(theRow, theCol);
		
	}
	
}