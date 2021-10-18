package noughtsAndCrossesV2;

import java.util.Scanner;

public class HumanPlayer implements NCPlayer 
{
	Scanner theInput ;
	GameRunnerV2 theGame;
	NCGrid.SquareStatus theSymbol = NCGrid.SquareStatus.EMPTY;   // initialise so we can detect error if it is not set O or X 
	
	public HumanPlayer(Scanner inputScanner, GameRunnerV2 gameRunner)
	{
		theInput = inputScanner;
		theGame = gameRunner;
	}

	public void setMySymbol(NCGrid.SquareStatus sym)
	{
		theSymbol = sym;
	}

	public NCGrid.SquareStatus getMySymbol()
	{
		if (theSymbol == NCGrid.SquareStatus.EMPTY)
			System.err.println("HumanPlayer : symbol has not been set ");   //exercise for the reader :  throw an error here 

		return theSymbol;
	}

	public GridCoordinate getNextMove(NCGrid currentGrid) 
	{
		theGame.displayInstructions();
		theGame.displayGrid(currentGrid);
		int theRow = theInput.nextInt();
		int theCol  = theInput.nextInt(); 
		return new GridCoordinate(theRow, theCol);
	}

}
