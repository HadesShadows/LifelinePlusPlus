import java.util.*;
//Creating a class that will help me to find the Subset from the given Set
class SubsetHelper {
    int inputSet[]; // Array to store the input set

    int targetedSum; // To store the Targeted Sum
    Stack<Integer> requiredSubset;
    boolean hasSolution;
    // Created Constructor to pass parameters using class name
    SubsetHelper(int inputSet[], int targetedSum) {
        this.inputSet = inputSet; // Storing the input set values in array

        this.targetedSum = targetedSum; // Storing the Targeted Sum

        this.requiredSubset = new Stack<>(); // Initializing the requiredSubset by creating a stack
                hasSolution = false;
    }

    public void findSubset(int tempSum, int index) {
// If the value is greater then sum then we are returning directly

        if (tempSum > targetedSum)
            return;
// if the Sum is equal to targeted sum then we are returning giving signal true
        if (tempSum == targetedSum) {
            hasSolution = true;
            displayRequiredSubsets(); // Here i am displaying the contents of the stack
            return;
        }

        for (int i = index; i < inputSet.length; i++) {
            requiredSubset.push(inputSet[i]); // Pushing elements on to the stack

            findSubset(tempSum + inputSet[i], i + 1); // recusively starting from next number after adding to the stack
            requiredSubset.pop(); // Popping the element out of stack i.e we are backtracking

        }
    }

    // The output Subset has been printed through this function`
    private void displayRequiredSubsets() {
        for (Integer item : requiredSubset) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}

public class SubsetSum {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("-------|| Subset Sum Problem ||-------\n");

                    System.out.println("Enter the number of elements of the Set: ");

            int n = sc.nextInt(); // Taking input for the numberof elements of subset
            sc.nextLine();
            System.out.println("Enter the elements: ");
            int set[] = new int[n];
            for (int i = 0; i < n; i++) {
                set[i] = sc.nextInt(); // Taking input for theelements of the subset

            sc.nextLine();
            }
            System.out.println("\nEnter the Sum to be calculated: ");

            int sum = sc.nextInt(); // Taking input fo the sum to be calculated

            sc.nextLine();

            SubsetHelper helper = new SubsetHelper(set, sum);

            System.out.println("\nThe possible subsets are: ");
            helper.findSubset(0, 0); // Calling findSubset for finding the Subset

            if (helper.hasSolution == false) {
                System.out.print("Can't find the solution!"); //If hasSoltion returns false printing this.

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}