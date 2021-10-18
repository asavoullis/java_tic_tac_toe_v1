package noughtsAndCrossesV2;

import java.util.Scanner;

import ncErrors.outOfRangeError;
import noughtsAndCrossesV2.NCGrid.GameStatus;
import noughtsAndCrossesV2.NCGrid.SquareStatus;

public class GameRunnerV2 
{
	 static final String INITIAL_INSTRUCTIONS = "Welcome to Noughts and Crosses. First player is crosses, second player is noughts.\n";
	 static final String EACHROUND_INSTRUCTIONS= "\n enter row from 0 - 2 and col from 0 - 2 separated by a space (0 0 = top left)\n";


	public static void main(String[] args) 
	{
		NCGrid theGrid = new NCGrid(3);
		Game15Runner theGame = new Game15Runner();
		Scanner sc = new Scanner(System.in);
//		HumanPlayer p1 = new HumanPlayer(sc, theGame);
		SimpleComputerPlayer p1 = new SimpleComputerPlayer();
		Human15Player p2 = new Human15Player(sc, theGame);
		
		//SimpleComputerPlayer p2 = new SimpleComputerPlayer();
		
		p1.setMySymbol(SquareStatus.CROSS);
		p2.setMySymbol(SquareStatus.NOUGHT);

		
		
		System.out.println(INITIAL_INSTRUCTIONS);


		NCPlayer nextToPlay = p1;		// arbitrary decision that p1 goes first

		while (theGrid.getGameStatus() == GameStatus.STILLPLAYING)
		{
			GridCoordinate nextMove = nextToPlay.getNextMove(theGrid) ;
			try
			{
				theGrid.setSquareStatus(nextMove.getRow(), nextMove.getCol(), nextToPlay.getMySymbol());
			} 
			catch (outOfRangeError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			// take turns 
			if(nextToPlay == p1)
				nextToPlay = p2;
			else
				nextToPlay = p1;


		}
		theGame.displayGrid(theGrid);

		switch(theGrid.getGameStatus() )
		{
		case  CROSSWIN:
			System.out.println("Cross wins");
			break;
		case  NOUGHTWIN:
			System.out.println("a win for Noughts");
			break;
		case  DRAW:
			System.out.println("honours even");
			break;
		}




	}
	public  void displayInstructions ()
	{
		System.out.println(EACHROUND_INSTRUCTIONS);
	}
	
	public  void displayGrid (NCGrid theGrid)
	{
		int limit = theGrid.getGridDimension();
		
		for(int row=0; row < limit; row++)
		{
			if(row > 0)
				{
				System.out.println("___|___|___");
				System.out.println("   |   |   ");
				}
			for (int col = 0; col < limit; col++)
			{
				if(col > 0 )
					System.out.print("|");
				try {
					switch(theGrid.getSquareStatus(row, col))
					{
					case CROSS:
						//add
						break;
					case NOUGHT:
						
						break;
					case EMPTY:
						
						break;
					}
				}
				catch (outOfRangeError e) 
				{
					System.err.println("Row or col out of range - should not happen " + e.toString());
					e.printStackTrace();
					System.exit(1);
				}	
				
				}
			}
		}
	

}
