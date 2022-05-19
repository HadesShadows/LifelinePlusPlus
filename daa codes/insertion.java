import java.util.Scanner;
import java.util.*;

public class insertion {
   public static void insertionsort(int[] arr, int n ) {
    int key, j;
            for (int step=0; step<n; step++){
                key= arr[step];
                j=step-1;

                while(j>=0 && key< arr[j]){
                    arr[j+1]= arr[j];
                    --j;
                }
            arr[j+1]= key;

        }
       System.out.println("*******sorted array using Insertion sort*******");

       for (int i=0; i < n; i++){
           System.out.print(""+arr[i]+"-> ");
       }

    }

    public static void main(String[] args){
        System.out.println("enter total number of elements: ");
        Scanner sc= new Scanner(System.in);
        int n= sc.nextInt();
      int temp= n;

        int[] array1= new int[n];

        for (int i=0; i < n; i++){
            System.out.print("enter element no. "+(i+1)+": ");
            array1[i]= sc.nextInt();

        }

        insertionsort(array1, n);
    }
}
