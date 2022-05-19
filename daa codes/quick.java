import java.util.Scanner;
import java.util.*;

public class quick {
    static int partition(int array[], int low, int high) {

        int pivot= array[high];

        int i= (low-1);

        for (int j= low; j<high; j++){
            if (array[j]<= pivot){
                i++;

                int temp= array[i];
                array[i]=array[j];
                array[j]=temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return (i+1);
    }

    public static void quicksort(int[] arr, int low, int high ) {
        if (low< high){

            int par= partition(arr, low, high);

            quicksort(arr, low, par-1);

            quicksort(arr, par+1, high);


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
        quicksort(array1,0,n-1);

        System.out.println("*******sorted array using quick sort*******");

        for (int i=0; i < n; i++){
            System.out.print(""+array1[i]+"-> ");
        }
    }
}
