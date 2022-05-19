
import java.util.Scanner;

public class BranchAndBound {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the size of the grid:");
        int n = input.nextInt();
        int[][] arr = new int[n][n];
        boolean solvable = false;
        int[] inversionArray = new int[n * n];
        int k = 0;
        int blankSpace = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = input.nextInt();
                inversionArray[k] = arr[i][j];
                k++;
                if (arr[i][j] == 0)
                    blankSpace = n - i;
            }
        }
        int inversions = 0;
        for (int i = 0; i < n * n - 1; i++) {
            for (int j = i + 1; j < n * n; j++) {
                if (inversionArray[i] > inversionArray[j] && inversionArray[i] != 0
                        && inversionArray[j] != 0) {
                    inversions++;
                }
            }
        }
        System.out.println(inversions);
        System.out.println(blankSpace);
        if (n % 2 == 1) {
            if (inversions % 2 == 0) {
                System.out.println("Sum is solvable");
                solvable = true;
            } else {
                System.out.println("Sum is not solvable");
                solvable = false;
            }
        } else if (n % 2 == 0) {
            if (blankSpace % 2 == 0 && inversions % 2 == 1) {
                System.out.println("Sum is solvable");
                solvable = true;
            } else if (blankSpace % 2 == 1 && inversions % 2 == 0) {
                System.out.println("Sum is solvable");
                solvable = true;
                System.out.println();
            } else {
                System.out.println("Sum is not solvable");
                solvable = false;
            }
        }
        if (solvable) {
            solve(arr, n);
        }
    }

    public static void solve(int[][] arr, int n) {
        int x = 0, y = 0;
        int p;
        int minCost = Integer.MAX_VALUE;
        int costMove = 0;
        int iterations = 0;
        int[][] temp = new int[n][n];
        int[][] intermediate = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
// arr[i][j]=intermediate[i][j];
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        while (!checkFinal(arr, n)) {
            iterations++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == 0) {
                        x = i;
                        y = j;
                    }
                    temp[i][j] = arr[i][j];
                }
            }
//to move upwards
            if (x != 0) {
// p=temp[x][y];
                temp[x][y] = temp[x - 1][y];
                temp[x - 1][y] = 0;
            }
            costMove = cost(temp, n) + iterations;
            if (costMove <= minCost) {
                minCost = costMove;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        intermediate[i][j] = temp[i][j];
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    temp[i][j] = arr[i][j];
                }
            }
//to move downwards
            if (x != n - 1) {
                p = temp[x][y];
                temp[x][y] = temp[x + 1][y];
                temp[x + 1][y] = p;
            }
            costMove = cost(temp, n) + iterations;
            if (costMove <= minCost) {
                minCost = costMove;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        intermediate[i][j] = temp[i][j];
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    temp[i][j] = arr[i][j];
                }
            }
//to move left
            if (y != 0) {
                p = temp[x][y];

                temp[x][y] = temp[x][y - 1];
                temp[x][y - 1] = p;
            }
            costMove = cost(temp, n) + iterations;
            if (costMove <= minCost) {
                minCost = costMove;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        intermediate[i][j] = temp[i][j];
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    temp[i][j] = arr[i][j];
                }
            }
//to move right
            if (y != n - 1) {
                p = temp[x][y];
                temp[x][y] = temp[x][y + 1];
                temp[x][y + 1] = p;
            }
            costMove = cost(temp, n) + iterations;
            if (costMove <= minCost) {
                minCost = costMove;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        intermediate[i][j] = temp[i][j];
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = intermediate[i][j];
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Minimum Cost:" + minCost);
        }
    }

    public static boolean checkFinal(int[][] arr, int n) {
        int[][] target = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int cost(int[][] arr, int n) {
        int count = 0;
        int[][] target = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != target[i][j])
                    count++;
            }
        }
        return count;
    }
}