/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc= new Scanner(System.in);
        int t= sc.nextInt();
        int x[]= new int[t];
        int y[]= new int[t];
        int z[]= new int[t];
        for (int i=0; i<t; i++){
            x[i]= sc.nextInt();
            y[i]= sc.nextInt();
            z[i]= sc.nextInt();
        }
        for (int i=0; i<t; i++){

            int tp= x[i];
            int rem= z[i]*2;
            int sum= tp+rem;
            if(sum>=y[i]){
                System.out.println("yes");
            }
            else
                System.out.println("no");

        }
    }
}
