package noughtsAndCrossesV2;

/* very simple class to handle co-ordinate pairs, (row, column)
 * Provides two methods, returning column or row as required.
 * No error handling - e.g. checking values are in range 
 */
public class GridCoordinate  
{
	int row;
	int column;

	public GridCoordinate(int theRow, int theCol)  
	{

		row = theRow;
		column = theCol;

	}

	int getRow()
	{
		return row;
	}
	int getCol()
	{
		return column;
	}
}
