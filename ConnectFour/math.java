import java.lang.Math;

public class math
{
    public static void main(String[] args)
    {
        int a = 1;
        for(int i = 0; i < 100; i++)
        {
            int rand = (int)(Math.random() * 2) + 1; 
            System.out.print(rand + " ");
            if(a % 10 == 0)
            {
                System.out.println();
            }
            a++;
        }
    }
}