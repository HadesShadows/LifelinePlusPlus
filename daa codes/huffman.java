
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;


class  huffmannode{
    int data ;
    char c;
    String ans="";
    huffmannode left= null;
    huffmannode right= null;
}

class comp implements Comparator<huffmannode> {
    public int compare(huffmannode a, huffmannode b)
    {
        return a.data - b.data;
    }
}

public class huffman
{
    static void printString(huffmannode n)
    {
        if(n==null) return;
        if(isLeaf(n))
        {
            for (int i = 0; i < n.data; i++) {
                System.out.printf(n.c+"");
            }

            return; // Return if leaf node is reached
        }

        printString(n.left);
        printString(n.right);
    }

    static void printEncode(huffmannode n)
    {
        if(n==null) return;
        if(isLeaf(n))
        {
            for (int i = 0; i < n.data; i++) {
                System.out.print(n.ans+"");
            }
            return; // Return if leaf node is reached
        }
        printString(n.left);
        printString(n.right);
    }

    static boolean isLeaf(huffmannode n)
    {
        if(n.left == null && n.right == null)
            return true;

        return false;
    }

    static void printHuffmanTree(huffmannode n)
    {
        if(n==null) return;
        printHuffmanTree(n.left);
        if(isLeaf(n))
        {
            System.out.println(n.c+":"+n.data);
            return;
        }
        else
        {
            System.out.println(n.data);
        }
        printHuffmanTree(n.right);
    }

    public static void printCode(huffmannode root, String s)
    {



        if (root.left == null && root.right == null && Character.isLetter(root.c)) {

            System.out.println(root.c + ":" + s);
            root.ans=root.ans+s;
           // System.out.println(root.ans);
            return;
        }

        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("enter your roll no= ");
    int r = sc.nextInt();
    System.out.println();
    int n = 6;
    char[] frec = {'a', 'b', 'c', 'd', 'e', 'f'};
    int[] frev = new int[n];
    for (int i = 0; i < 6; i++) {
        frev[i] = (r + (r + 1) * i) % (10);
        System.out.println("Frequency of character "+ frec[i]+" is= "+ frev[i]);
    }
    PriorityQueue<huffmannode> q = new PriorityQueue<huffmannode>(n, new comp());

    for (int i = 0; i < n; i++) {

        huffmannode h = new huffmannode();

        h.c = frec[i];
        h.data = frev[i];


        q.add(h);

    }
huffmannode root= null;
int x=0;
    while (q.size()>1){

        huffmannode a= q.poll();

        huffmannode b= q.poll();

        huffmannode f= new huffmannode();


        assert b != null;
        f.data= a.data + b.data;
        f.c= frec[x];

        f.left= a;
        f.right=b;
        root= f;
        q.add(f);
x++;
    }
    System.out.print("Inorder Traversal of huffman tree: ");
    System.out.println();
    printHuffmanTree(root);
    System.out.println();

    printCode(root, "");
    System.out.print("The original string is: " );
    printString(root);
    System.out.println();
  // printEncode(root, frev[]);


}

}
