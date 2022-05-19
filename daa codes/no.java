import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
class SetCoveringPblm {
    // calculate minimum cost of set cover
    public static void minCost(Set Univ, Set S[], int cost[], int m) {
// Set I is used to get minimum cost
        Set<Integer> I = new HashSet<Integer>();
        int minCost = 0;
        System.out.print("Best possible Solution sets -> { ");
// Loop continues till the time I contains all the elements of U
        while (!I.equals(Univ)) {

// calculate the min cost
            double min = Double.MAX_VALUE;
// loop to find the minimum cost
            int index = -1;
            double effCost[] = new double[m];
            for (int i = 0; i < m; i++) {
// diff is difference between S[i] and I
                Set<Integer> diff = new HashSet<Integer>(S[i]);
                diff.removeAll(I);
// if size of diff != 0
                if (diff.size() != 0) {
                    effCost[i] = cost[i] / diff.size();
                }
// if size of diff = 0
                else {
                    effCost[i] = Double.MAX_VALUE;
                }
// change min if effCost[i] < min
                if (min > effCost[i]) {
                    min = effCost[i];
                    index = i;
                }
            }
// Printing the S[i]
            System.out.print("S[" + (index + 1) + "] ");
// Used to get union of I and S[index]
            Set<Integer> union = new HashSet<Integer>(I);
            union.addAll(S[index]);
// I = union of I and S[index]
            I = union;
// Min cost
            minCost = minCost + cost[index];
        }
        System.out.print("}");
        System.out.println(" Minimum cost: " + minCost);
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
// no of elements in U

        System.out.println("------------|| Subset Covering Problem ||------------\n");
                System.out.print("Enter the total elements in Universal Set : ");
        int n = sc.nextInt();
// for storing the values in Universal set
        System.out.print("Enter the elements -> ");
        Set<Integer> Univ = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            Univ.add(temp);
        }
// no of sets in S
        System.out.print("Enter the number of sets : ");
        int m = sc.nextInt();
        Set<Integer> S[] = new HashSet[m];
        int cost[] = new int[m];
        for (int i = 0; i < m; i++) {
            S[i] = new HashSet<Integer>();
            System.out.println("\nInput for S[" + (i + 1) + "]: ");
// Cost of S[i]
            System.out.print("Enter the cost(S[" + (i + 1) + "]) : ");
            cost[i] = sc.nextInt();
// no of elements in S[i]
            System.out.print("Number of Elements : ");
            int size = sc.nextInt();
            System.out.println("Enter the elements : ");
            for (int j = 0; j < size; j++) {
                int temp = sc.nextInt();
                S[i].add(temp);
            }
        }
// method to get min cost
        minCost(Univ, S, cost, m);
    }
}