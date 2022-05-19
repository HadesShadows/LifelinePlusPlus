import java.util.*;

public class Dijkstra {
    private static int findMin(int[] dist, boolean[] visited) {
        int min = -1;// let min be -1
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && (min == -1 || dist[i] < dist[min])) {
                min = i;// find new minimum
            }
        }
        return min;// return minimum
    }
    private static void dijkstra(int[][] matrix, Integer[] vertices, int source) {
        int len = matrix.length;// size of matrix
        boolean[] visited = new boolean[len];// visited array

        int[] dist = new int[len];// distance array

        for (int i = 0; i < len; i++)
        {
            dist[i] = Integer.MAX_VALUE;// initializing the distance array to maximum value(infinity)
        }
        dist[source] = 0; // distance of source from source is 0
        for (int i = 0; i < len - 1; i++) {
// find vertex with minimum distance
            int min_vertex = findMin(dist, visited);
            visited[min_vertex] = true;// make the visited array of that vertex as true
// explore the neighbours
            for (int j = 0; j < len; j++) {
                if (matrix[min_vertex][j] != 0 && dist[min_vertex] != Integer.MAX_VALUE) {
                    int new_dist = dist[min_vertex] + matrix[min_vertex][j];
                    dist[j] = new_dist;
                }
            }
        }
// output the minimum distances of the vertices from the source
// distanc of the source will be printed as 0.
        System.out.println();
        System.out.println("Vertex\t\tDistance from Source");
        for (int i = 0; i < vertices.length; i++) {
            System.out.println(vertices[i] + " \t\t " + dist[vertices[i]]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int h = 0; h < t; h++) {
            int v = sc.nextInt();// input the number of vertices
            int matrix[][] = new int[100][100];// create a 100* 100 adjacency matrix

            int e = sc.nextInt();// input the number of edges

            Set<Integer> set = new HashSet<Integer>(); // create a set to store the vertices

            int source = sc.nextInt();// input the source of the graph

            int destination = sc.nextInt();// input destination

            int weight = sc.nextInt();// input weight/cost

            matrix[source][destination] = weight;// add weight to the adj matrix

            set.add(source);// add the vertices to the set

            set.add(destination);

            for (int i = 1; i < e; i++) {
// input the vertices, cost and add weight to the matrix
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();

                set.add(v1);
                set.add(v2);
                int cost = sc.nextInt();
                matrix[v1][v2] = cost;
            }
// converting set to array
            Integer[] array = new Integer[set.size()];
            int k = 0;
            for (Integer i : set) {
                array[k++] = i;
            }
// System.out.println(array.length);
            dijkstra(matrix, array,source);// method call
        }
    }
}