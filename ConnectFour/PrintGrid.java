public class PrintGrid
{
    static int[] global_arr;
    static int global_loc = 0;
    public static void main(String[] args)
    {
        int columns = 4;     // cells
        int rows = 4;        // dashlines


        global_arr = new int[columns*rows];
        
        int arr_temp = 0;
        for(int i = 0; i < global_arr.length; i++)
        {
            int rand = (int)(Math.random() * 2) + 1; 
            global_arr[arr_temp] = rand;
            arr_temp++;
        }

        int t = 1;
        for(int i = 0; i < global_arr.length; i++)
        {
            System.out.print(global_arr[t-1] + " ");
            if(t % 4 == 0)
            {
                System.out.println();
            }
            t++;
        }

        System.out.println();


        
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
        String s = foo(global_loc);
        global_loc++;
        //System.out.print("|  _  |");
        System.out.print("|  " + s + "  |");
    }

    public static void printupdashLast(int r)
    {
        int tempR = 0;
        while(tempR < r-1)
        {
            String s = foo(global_loc);
            global_loc++;
           //System.out.print("  _  |");
           System.out.print("  " + s + "  |");
           tempR++;
        }
    }

    public static String foo(int i)
    {
        String s = "";
        s += global_arr[i];
        return s;
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
}
