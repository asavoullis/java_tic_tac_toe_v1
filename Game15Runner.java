package noughtsAndCrossesV2;

import java.util.Scanner;

import ncErrors.outOfRangeError;
import noughtsAndCrossesV2.NCGrid.GameStatus;
import noughtsAndCrossesV2.NCGrid.SquareStatus;

public class Game15Runner extends GameRunnerV2
{
	 
	 static final String INITIAL_INSTRUCTIONS = "Welcome to 15-game. First player is crosses, second player is noughts.\n";
	 static final String EACHROUND_INSTRUCTIONS= "Enter a number between 1 and 9: \n";
	 static int magic15game[][]={{6,1,8},{7,5,3},{2,9,4}};
	 
	 public static void main(String[] args) 
		{
			NCGrid theGrid = new NCGrid(3);
			Game15Runner theGame = new Game15Runner();
			Scanner sc = new Scanner(System.in);
			//HumanPlayer p1 = new HumanPlayer(sc, theGame);
			SimpleComputerPlayer p1 = new SimpleComputerPlayer();
			Human15Player p2 = new Human15Player(sc, theGame);
	
			//SimpleComputerPlayer p2 = new SimpleComputerPlayer();
			
			p1.setMySymbol(SquareStatus.CROSS);
			p2.setMySymbol(SquareStatus.NOUGHT);

			System.out.println();
			
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
	 
		@Override	
		public void displayGrid(NCGrid theGrid)
		{
			System.out.print("Crosses has chosen : ( ");
			printValues(theGrid, SquareStatus.CROSS);
			System.out.println(")");
			
			System.out.print("Noughts has chosen : ( ");
			printValues(theGrid, SquareStatus.NOUGHT);
			System.out.println(")");
			
			System.out.print("Available Choices : ( ");
			printValues(theGrid, SquareStatus.EMPTY);
			System.out.println(")");
			System.out.println("");
			System.out.println(EACHROUND_INSTRUCTIONS);
		}
		
		public void printValues (NCGrid theGrid, SquareStatus theValue) 
		{	
			for(int theRow = 0 ; theRow < 3 ; theRow++ )
			{
				for(int theCol = 0; theCol < 3; theCol++ )
				{
					try {
						if(theGrid.getSquareStatus(theRow,theCol) == theValue)
						{
							System.out.print(magic15game[theRow][theCol]);
							System.out.print(" ");
						}
					} catch (outOfRangeError e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
	
	public  void displayInstructions ()
	{
		
	}
	

}
