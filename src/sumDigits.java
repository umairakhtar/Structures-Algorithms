
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
public class sumDigits {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        try{
            int num = sc.nextInt();        
        if(num>=0){
            System.out.println("The sum of the digits is:");
            System.out.print(sumDigits(num));
        }
        else{
            throw new IllegalArgumentException("value must be non-negative");
        }
        }
        catch(IllegalArgumentException i){
            System.out.println("value must be non-negative");
        }
        
        
    }
    public static int sumDigits(int n){
        int i;
        if(n==0)
            return 0;
        else
            i = n % 10;
            n = n / 10;
            return (i + sumDigits(n));
    }
}