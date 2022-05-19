// Java program for recursive implementation
// of Bubble sort
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class bubblesort
{
    static int swaps=0, count=1;
    static int num=0;
    static void printArray(int[] arr, int size)
    {
        for(int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");

        System.out.println();
    }
    // A function to implement bubble sort
    static void bubbleSort(int arr[], int n)
    {

        // Base case
        if (n == 1)
            return;

        // One pass of bubble sort. After
        // this pass, the largest element
        // is moved (or bubbled) to end.
        for (int i=0; i<n-1; i++) {
            if (arr[i] > arr[i+1])
            {
                // swap arr[i], arr[i+1]
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                swaps++;
            }
            //print the sorted array till the value of i in that iteration
        }
        System.out.print("Recursion number("+count+")= ");
        printArray(arr, num);
        count++;
        // Largest element is fixed,
        // recur for remaining array
        bubbleSort(arr, n-1);
    }
    static void array_creator(int num, int rollno, int[] best, int[] worst) {// formation of the array
        for (int i = 0; i < num; i++) {
            best[i] = rollno + (rollno + 1) * i;
            worst[num - i - 1] = rollno + (rollno + 1) * i;
        }
    }

    static void Randomize(int num, int[] arr) { // shuffling of the array elements
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            int index = rand.nextInt(arr.length);
            if (arr[i] == arr[index])
                continue;
            arr[i] = arr[i] + arr[index];
            arr[index] = arr[i] - arr[index];
            arr[i] = arr[i] - arr[index];
        }
    }
    // Driver Method
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bubble Sort");
        System.out.println("Enter Array size:");
        num = sc.nextInt();

        int[] best = new int[num];// best case array
        int[] worst = new int[num];// worst case array
        int[] random = best;// random case array

        int rollno = 42;

        array_creator(num, 42, best, worst);

        System.out.println("\nBest Case: ");
        printArray(best, num);
        bubbleSort(best, num);
        System.out.println("Total number of swaps: " + swaps);
        System.out.print("Final sorted array: ");
        printArray(best, num);
        System.out.println();

        System.out.println("Worst Case:");
        printArray(worst, num);
        swaps=0;
        count=0;
        bubbleSort(worst, num);
        System.out.println("Total number of swaps: " + swaps);
        System.out.println();
        System.out.print("Final sorted array: ");
        printArray(worst, num);
        System.out.println("Random Case:");
        Randomize(num, random);
        printArray(random, num);
        count=1;
        swaps=0;
        bubbleSort(random, num);
        System.out.println("Total number of swaps: " + swaps);
        System.out.println();
        System.out.print("Final sorted array: ");
        printArray(random, num);
    }

}
