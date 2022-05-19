

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Queue;

class node{
int val;
char c;

node left= null;
node right = null;
}
class compe implements Comparator<huffmannode> {
    public int compare(huffmannode a, huffmannode b)
    {
        return a.data - b.data;
    }
}
public class huffman2
{
    public static void main(String[] args){
        System.out.print("enter your roll number: ");
        Scanner sc= new Scanner(System.in);
        int r = sc.nextInt();
        System.out.println();
        int n = 6;
        char[] frec = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] frev = new int[n];
        for (int i = 0; i < 6; i++) {
            frev[i] = (r + (r + 1) * i) % (10);
            System.out.println("Frequency of character "+ frec[i]+" is= "+ frev[i]);
        }

        PriorityQueue<huffmannode> q = new PriorityQueue<huffmannode>(n, new compe());




    }
}
