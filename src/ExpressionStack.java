
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Scanner;
import java.util.Stack;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UmairAkhtar
 */
public class ExpressionStack {
    public static void main(String[] args){
    Stack<Integer> vals = new Stack<Integer>();
    Stack<String> ops = new Stack<String>();
    String s;
    Scanner sc = new Scanner(System.in);
    s = sc.next();
    for(int i=0;i<s.length();i++){
        String p = String.valueOf(s.charAt(i));
        if (p.equals("(")) ;
        else if (p.equals("+")) ops.push(p);
        else if (p.equals("*")) ops.push(p);
        else if (p.equals(")"))
        {
            String op = ops.pop();
            if (op.equals("+")) vals.push(vals.pop() + vals.pop());
            else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
        }   
        else vals.push(Integer.parseInt(p));
    }
    System.out.println(vals.pop());
}
}
