package noughtsAndCrossesV2;

import ncErrors.outOfRangeError;


public class SimpleComputerPlayer implements NCPlayer 
{
	NCGrid.SquareStatus theSymbol = NCGrid.SquareStatus.EMPTY;   // initialise so we can detect error if it is not set O or X 

	public SimpleComputerPlayer()
	{
		// no initialisation
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

	// scan through grid until we find an empty square, choose it

	public GridCoordinate getNextMove(NCGrid currentGrid) 
	{
		int theRow;
		int theCol;
		GridCoordinate theSquare = null;

		for (theRow = 0; (theSquare == null) && (theRow < currentGrid.getGridDimension()); theRow++)
		{
			for (theCol = 0; (theSquare == null) && (theCol < currentGrid.getGridDimension()); theCol++)
			{
				try
				{
				if(currentGrid.getSquareStatus(theRow, theCol)==NCGrid.SquareStatus.EMPTY)
					theSquare = new GridCoordinate(theRow, theCol);
				}
				catch (outOfRangeError e)
				{
					// and ignore it - another exercise for the reader  
				}
			}
		}
		return theSquare;
	}


}
