import java.util.Scanner;

// for 0-1 Knapsack problem
class Knapsack {

    // A utility function that returns
    // maximum of two integers
    static int max(int a, int b)
    {
        return (a > b) ? a : b;
    }

    // Returns the maximum value that can
    // be put in a knapsack of capacity W
    static int knapSack(int W, int wt[],
                        int val[], int n)
    {
        int i, w;
        int K[][] = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++)
        {
            for (w = 0; w <= W; w++)
            {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w]
                            = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        return K[n][W];
    }

    // Driver code
    public static void main(String args[])
    {
        int  t;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total number of Test cases: ");
        t= sc.nextInt();
        for (int i =0; i< t; i++)
        {
            System.out.println("Enter number of objects: ");
            int n= sc.nextInt();
            System.out.println("Enter total weight of the objects: ");
            int w= sc.nextInt();
            int val[] = new int[n];
            int wt[] = new int[n];
            System.out.println("enter values of the items: ");
            for(int j=0;j<n;j++)
            {
//Profit of all elements in the array
               val[j]= sc.nextInt();
            }
            System.out.println("Enter Weights of the items: ");
            for(int j=0;j<n;j++)
            {
//Weight of all elements in the array
                val[j]= sc.nextInt();
            }
            System.out.println("Max profit: "+ knapSack(w, wt, val, n));
        }

    }
}

