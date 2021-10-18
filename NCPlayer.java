package noughtsAndCrossesV2;

import noughtsAndCrossesV2.NCGrid.SquareStatus;

/*
 * an interface specifies the methods a class must implement.
 * Here we methods to set/get the symbol and find the next move
 */

public interface NCPlayer 
{	
	public SquareStatus getMySymbol();
	public void setMySymbol(SquareStatus theSymbol); 
	public GridCoordinate getNextMove(NCGrid currentGrid);
}
