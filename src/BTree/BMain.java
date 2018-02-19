/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTree;

/**
 *
 * @author UmairAkhtar
 */
import java.util.Scanner;
public class BMain 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner( System.in );
        int n,n2,temp;
        System.out.print("Enter the t of the Tree?  ");
        n=input.nextInt();		
        while ( n<2)
        {	
            System.out.print("Please enter a integer greater than 1 : ");
            n=input.nextInt();
        }
	BTree tree = new BTree(n);
	System.out.print("\n How many values do you want to enter?:  ");	
        n2 = input.nextInt();

        for ( int i=0;i< n2;i++)
	{
            System.out.print("\nEnter Value:");
            System.out.println(i+1);
            temp=input.nextInt();
            tree.insert(tree,temp);
	}
        System.out.println("Value are:");
        tree.print(tree.root);
	System.out.println();					
    }
}