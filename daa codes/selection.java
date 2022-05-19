import java.util.Scanner;
import java.util.*;

public class selection {
    public static void selectionsort(int[] arr, int n ) {
        int min;
        for (int i=0; i<n-1; i++){
            min= i;
            for (int j= i+1; j<n; j++ ){
                if (arr[j]<arr[min]){
                   min=j;
                }
            }

            int temp= arr[i];
            arr[i]= arr[min];
            arr[min]= temp;
        }
        System.out.println("*******sorted array using selection sort*******");

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
        selectionsort(array1, n);
    }
}
