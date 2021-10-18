package noughtsAndCrossesV2;

import ncErrors.outOfRangeError;

/**
 * Class representing the board for a game of noughts and crosses (tic-tac-toe)
 *
 * The enumerations SquareStatus and GameStatus are used (respectively) to represent the content
 * of a square on the grid and the status of the game (i.e. whether it is underway, drawn or won).
 * A game is deemed to be a draw if all squares are occupied.
 * Note that the size of  the board is set in the constructor, but the method "checkForLineOfThree"
 * is hardcoded to look for lines of three identical symbols (O or X)
 */

public class NCGrid {
	
	public enum SquareStatus {NOUGHT,CROSS,EMPTY}
	public enum GameStatus {NOUGHTWIN,CROSSWIN,DRAW,STILLPLAYING}
	
	private int gridSize;
	private SquareStatus theGrid[][];
	private int numberOccupiedSquares;
	
	
	public  NCGrid (int size)
	{
		numberOccupiedSquares=0;
		gridSize = size;
		theGrid = new SquareStatus[size][size];
		for(int row=0; row <size; row++)
		{
			for(int col=0; col <size; col++)
				theGrid[row][col] = SquareStatus.EMPTY;

		}
		
	}
	public int getGridDimension()
	{
		return gridSize;
	}
	
	private boolean inRange(int value)
	{
		return (value >= 0) && (value < gridSize);
	}

	public SquareStatus getSquareStatus(int row, int col) throws outOfRangeError
	{
		if(inRange(row) && inRange(col))
			return theGrid[row][col];
		else
			throw new outOfRangeError(); // row,col);
	}
	
	private SquareStatus getSquareStatusNoChecking(int row, int col) // same as above without error checking
	{
				return theGrid[row][col];
	}
	
	public boolean  setSquareStatus(int row, int col, SquareStatus value) throws outOfRangeError
	{
		boolean ret=inRange(row) && inRange(col)
				;
		if(ret)
		{
			switch( theGrid[row][col])
			{
			case EMPTY:
				theGrid[row][col] = value;
				numberOccupiedSquares++;
				break;

			case NOUGHT:
			case CROSS:
				ret = false;		// This should this be an error; currently we silently forfeit the turn
				break;
			}
		}
		else
			throw new outOfRangeError(); // row,col);
		return ret;
	}
	
	public int getNumberOfOccupiedSquares()
	{
		return numberOccupiedSquares;
	}
	public GameStatus getGameStatus() 
	{
		GameStatus theStatus = GameStatus.STILLPLAYING;
		if(getNumberOfOccupiedSquares() == getGridDimension()*getGridDimension())
			theStatus = GameStatus.DRAW;
		
		/* otherwise check each row and column */
		for(int counter =0; (theStatus == GameStatus.STILLPLAYING ) && (counter < gridSize); counter++)
		{
			theStatus = checkForLineOfThree(counter, 0, 0, 1);		// check row
			if(theStatus == GameStatus.STILLPLAYING)
				theStatus = checkForLineOfThree(0, counter, 1, 0);		// check column

		}
		if(theStatus == GameStatus.STILLPLAYING)
			theStatus = checkForLineOfThree(0, 0, 1, 1);		// check diag
		if(theStatus == GameStatus.STILLPLAYING)
			theStatus = checkForLineOfThree(0, 2, 1, -1);		// check other diag
			
		return theStatus;
	}
	
	/*
	 * check to see whether there is a line of three identical symbols (NOUGHT or CROSS)
	 * starting at square (startRow, startCol) and moving in direction specified 
	 * by incrementRow, incrementCol. These are added to startRow/Col values to find the next square
	 * in the series. Hardcoded to look for lines of length three. 
	 * Returns GameStatus if 3 CROSS or NOUGHT symbols are found, false otherwise.
	 */
	private GameStatus checkForLineOfThree(int startRow, int startCol, int incrementRow, int incrementCol)
	{
		SquareStatus firstSquareContent = getSquareStatusNoChecking(startRow, startCol);
		GameStatus ret;
		boolean lineOfThree = (firstSquareContent != SquareStatus.EMPTY);
		for(int count=1; lineOfThree && (count <3);count++)
		{
			startRow += incrementRow;
			startCol += incrementCol;
			lineOfThree= ( getSquareStatusNoChecking(startRow, startCol) == firstSquareContent);
		}
		if(!lineOfThree)
			ret = GameStatus.STILLPLAYING;
		else if(firstSquareContent == SquareStatus.CROSS) 
			ret = GameStatus.CROSSWIN;
		else
			ret = GameStatus.NOUGHTWIN;

	
			
			return ret;
	}

}
