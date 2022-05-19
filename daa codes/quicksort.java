//divide and conquer approach
import java.util.Scanner;
import java.util.Random;
public class quicksort {

    static void printArray(int[] arr, int size)
    {
        for(int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");

        System.out.println();
    }
    static int total, totalc = 0; // total swaps and total comparisons

    static int partition(int arr[], int l, int h) {// partition function
        int pivot = arr[l];// assigning the first element of this part as pivot
        int comp = 0;
        int i = l + 1;
        int j = h;
        do {
            while (pivot > arr[i] && i + 1 <= h) {// second condition restricts i to the last element of the array
                i++;
                comp++;
            }
            while (pivot < arr[j] && j >= l + 1) {
                j--;
                comp++;
            }
            if (i < j) { // swapping the l and the h elements and the swapping pivot with the h element
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                total++;
            }
        } while (i < j);// IMPORTANT
        if (l != j) { // IF j==l then we are already at the pivot element, hence no swapping required
            int temp = arr[j];
            arr[j] = arr[l];
            arr[l] = temp;
            total++;
        }
        totalc = totalc + comp;
        System.out.println();
        System.out.println("Pivot element= " + pivot );
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
        return j;// pivot moves to the position j
    }

    static void quicksort(int arr[], int start, int end) {
        if (start < end) {
            int piv_pos = partition(arr, start, end);// stores the position of pivot element
            quicksort(arr, start, piv_pos - 1); // sorts the left side of pivot.
            quicksort(arr, piv_pos + 1, end); // sorts the right side of pivot.
        }
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quick Sort");
        System.out.println("Enter Array size:");
        int num = sc.nextInt();

        int[] best = new int[num];// best case array
        int[] worst = new int[num];// worst case array
        int[] random = best;// random case array

        int rollno = 42;

        array_creator(num, 42, best, worst);

        System.out.println("\nBest Case: ");
        printArray(best, num);
        System.out.println("Total number of swaps: " + total + "\nTotal number of comparisions: " + totalc);
        System.out.println();

        System.out.println("Worst Case:");
        printArray(worst, num);
        quicksort(worst, 0, num - 1);
        System.out.println("Total number of swaps: " + total + "\nTotal number of comparisions: " + totalc);
        System.out.println();
        printArray(worst, num);
        System.out.println("Random Case:");
        Randomize(num, random);
        printArray(random, num);

        quicksort(random, 0, num - 1);
        System.out.println("Total number of swaps: " + total + "\nTotal number of comparisions: " + totalc);
        System.out.println();
        printArray(random, num);
    }
}
