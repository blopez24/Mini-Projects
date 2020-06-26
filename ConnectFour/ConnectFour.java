import java.util.Scanner;

public class ConnectFour
{

	static int[] global_arr;
	static int global_tracker = 0;

	static int rows;
	static int columns;
	static int[][] board;
	static int[] counter;
	
	public static void main(String[] args)
	{

		printWelcome();

		Scanner s = new Scanner(System.in);

		System.out.print("\n             MUST BE AT LEAST 4\nPlease enter the number of rows for your grid: ");
		int m = s.nextInt();

		System.out.print("\n             MUST BE AT LEAST 4\nPlease enter the number of columns for your grid: ");
		int n = s.nextInt();

		System.out.println();

		rows = m;
		columns = n;
		board = new int[rows][columns];
		counter = new int[columns];
		//
		global_arr = new int[columns*rows];
		for(int i = 0; i < global_arr.length; i++)
			global_arr[i] = -1;
		//
		createEmptyTable(board);
		
		boolean noWinner = true;
		int turnTracker = 0;
		int choosenColumn;

		// Printer
		print();

		System.out.print("\nYou have created a " + rows + " x " + columns + " grid for your Connect Four Games.");
		System.out.println("\nPlease enter a number between: " + 0 + " " + (columns-1));

		System.out.println();
		
		while(noWinner)
		{
			if(turnTracker % 2 == 0)
				System.out.print("FIRST PLAYER ( X ): \nSelect Column: ");
			else
				System.out.print("SECOND PLAYER( O ): \nSelect Column: ");

			

			choosenColumn = s.nextInt();

			System.out.println();	

			userInput(board, choosenColumn, counter[choosenColumn], turnTracker);
			if(checkPlayer(turnTracker))
			{
				noWinner = false;
				s.close();
				break;
			}
			turnTracker++;

			updateGlobalArray();
			
			// for(int i = 0; i< global_arr.length; i++)
			// {
			// 	System.out.print(global_arr[i] + " ");
			// }

			//Printer
			print();
			
		}

		updateGlobalArray();
		//Printer
		print();

		if(turnTracker % 2 == 0)
			System.out.println("\nFirst Player(X) Wins!");
		else
			System.out.println("\nSecond Player(O) Wins!");

		s.close();
	}
	
	private static void userInput(int[][] b, int index, int row, int tt)
	{
		int r = rows - 1 - row;				
		counter[index]++;
		
		if(player(tt) == 0)
			b[r][index] = 0;
		else
			b[r][index] = 1;
	}
	
	private static int player(int tt)
	{
		if(tt % 2 == 0)
			return 0;
		else
			return 1;
	}
	
	private static boolean checkPlayer(int tt)
	{
		int i = player(tt);
		boolean gameOver = false;
		
		if(checkVertical(i)	||	checkHorizontal(i)	||	checkFowardSlash(i)	||	checkBackwardSlash(i) || itIsATie(board))
			gameOver = true;
		
		return gameOver;
	}
	
	private static boolean checkFowardSlash(int i) 
	{
		boolean winner = false;
		int checkAgainst;
		int counter = 0;
		
		if(player(i) == 0)
			checkAgainst  = 0;
		else
			checkAgainst = 1;
		
		while(!winner && enoughToCheckDiagonals())
		{
			for(int r = 3; r < rows; r++)
			{
				for(int c = 0; c < columns -3; c++)
				{
					for(int j = 0; j < 4; j++)
					{
						if(board[r-j][c+j] == checkAgainst)
							counter++;
						else
							counter = 0;
					}
					if(counter >= 4 && checkAgainst == 0)
					{
						//System.out.println("First Player Wins!");
						winner = true;
					}
					if(counter >= 4 && checkAgainst == 1)
					{
						//System.out.println("Second Player Wins!");
						winner = true;
					}
				}
				counter = 0;
			}
			break;
		}
		return winner;
	}

	private static boolean checkBackwardSlash(int i) 
	{
		boolean winner = false;
		int checkAgainst;
		int counter = 0;
		
		if(player(i) == 0)
			checkAgainst  = 0;
		else
			checkAgainst = 1;
		
		while(!winner && enoughToCheckDiagonals())
		{
			for(int r = 3; r < rows; r++)
			{
				for(int c = 3; c < columns; c++)
				{
					for(int j = 0; j < 4; j++)
					{
						if(board[r-j][c-j] == checkAgainst)
							counter++;
						else
							counter = 0;
					}
					if(counter >= 4 && checkAgainst == 0)
					{
						//System.out.println("First Player Wins!");
						winner = true;
					}
					if(counter >= 4 && checkAgainst == 1)
					{
						//System.out.println("Second Player Wins!");
						winner = true;
					}
				}
				counter = 0;
			}
			break;
		}
		return winner;
	}

	private static boolean checkHorizontal(int i) 
	{
		boolean winner = false;
		int checkAgainst;
		int counter = 0;
		
		if(player(i) == 0)
			checkAgainst  = 0;
		else
			checkAgainst = 1;
		
		while(!winner)
		{
			for(int r = 0; r < rows; r++)
			{
				for(int c = 0; c < columns; c++)
				{
					if(board[r][c] == checkAgainst)
						counter++;
					else
						counter = 0;
					
					if(counter >= 4 && checkAgainst == 0)
					{
						//System.out.println("First Player Wins!");
						winner = true;
					}
					if(counter >= 4 && checkAgainst == 1)
					{
						//System.out.println("Second Player Wins!");
						winner = true;
					}
				}
				counter = 0;
			}
			break;
		}
		return winner;
	}

	private static boolean checkVertical(int i) 
	{	
		boolean winner = false;
		int checkAgainst;
		int counter = 0;
		
		if(player(i) == 0)
			checkAgainst  = 0;
		else
			checkAgainst = 1;
		
		while(!winner)
		{
			for(int c = 0; c < columns; c++)
			{
				for(int r = 0; r < rows; r++)
				{
					if(board[r][c] == checkAgainst)
						counter++;
					else
						counter = 0;
					
					if(counter >= 4 && checkAgainst == 0)
					{
						//System.out.println("First Player Wins!");
						winner = true;
					}
					if(counter >= 4 && checkAgainst == 1)
					{
						//System.out.println("Second Player Wins!");
						winner = true;
					}
				}
				counter = 0;
			}
			break;
		}
		return winner;
	}
	
	private static boolean enoughToCheckDiagonals()
	{
		if( rows >= 4 && columns >= 4)
			return true;
		else
			return false;
	}
	
	private static boolean itIsATie(int[][] b)
	{	
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < columns; c++)
			{
				if(board[r][c] == -1)
					return false;
			}
		}
		
		System.out.println("It's a tie.");
		return true;
	}
	
	private static void createEmptyTable(int[][] b) 
	{
		for(int r = 0; r < b.length; r++)
		{
			for(int c = 0; c < b[0].length; c++)
			{
				b[r][c] = -1;
			}
		}
	}

	public static void printWelcome()
	{
		System.out.println("+--------------------------------------+");
		System.out.println("+       Welcome to Connect Four!       +");
		System.out.println("+    Please follow the instructions    +");
		System.out.println("+                 below.               +");
		System.out.println("+--------------------------------------+");
	}

	public static void printdash(int c)
    {
        int tempC = 0;
        while(tempC < c)
        {
            for(int i = 0; i < 6; i++ )
            {
                System.out.print("-");
            }
            tempC++;
        }
        System.out.print("-");
    }

    public static void printupdash()
    {
		String s = boxInfo(global_tracker);	// temp 
        global_tracker++;
		// System.out.print("|  -  |");
		System.out.print("|  " + s + "  |");
        
    }

    public static void printupdashLast(int r)
    {
		int tempR = 0;
        while(tempR < r-1)
        {
			String s = boxInfo(global_tracker);	// temp 
			global_tracker++;
		//    System.out.print("  -  |");
		   System.out.print("  " + s + "  |");
           tempR++;
        }
    }

    public static void printNum(int c)
    {
        for(int i = 0; i < c; i ++)
        {
            if(i == 0)
                System.out.print("   " + i);
            else
                System.out.print("     " + i);
        }
	}
	
	public static void updateGlobalArray()
	{
		int local_index = 0;
		for(int i = 0; i < board.length; i ++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				global_arr[local_index] = board[i][j];
				local_index++;
			}
		}
	}

	public static String boxInfo(int i)
	{
		if(global_arr[i] == -1)
			return "-";
		else if(global_arr[i] == 0)
			return "X";
		else
			return "O";
	}

	public static void print()
	{
		global_tracker = 0;
		for(int i = 0; i < rows; i++)
        {
            printdash(columns);
            System.out.println();
            for(int j = 0; j < columns; j++)
            {
                if(j == 0)
                    printupdash();
                else
                {
                    printupdashLast(columns);
                    break;
                }
            }
            System.out.println();
        }
        printdash(columns);
        System.out.println();
		printNum(columns);
		System.out.println();
	}
}