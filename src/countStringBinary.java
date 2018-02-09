
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UmairAkhtar
 */
public class countStringBinary {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of digits:");
        int num = sc.nextInt();
        System.out.println(countStringBinary(num)+" is the number of binary strings of length "+num+" that do not have two consecutive 0's.");
    }
    public static int countStringBinary(int n){
        if(n==1)
            return 2;
        else if(n==2)
            return 3;
        else
            return (countStringBinary(n-1)+countStringBinary(n-2));
    }
}
