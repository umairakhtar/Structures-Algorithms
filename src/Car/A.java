/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Car;

/**
 *
 * @author UmairAkhtar
 */
public class A {
    private int i, j;
    protected int area;

    public A(int a, int b) 
    { 
        i = a;
        j = b;
        area = calcArea();
        System.out.print(area);
    }
    private int calcArea() 
    { 
        return i * j; 
    }

    public void show() {
        area = calcArea();
        System.out.println("SHOW OF A"); 
    }
    
    public static int area(int x, int y) { 
        x -= 1;
        y += 1;
        System.out.print("z");
        return (x * y); 
    }
}

class B extends A { 
    public B(int a, int b) {
        super(a,b); 
    }
    public void show() { 
        System.out.println("HY");
    }
    public static void main(String[] args) {
        B b = new B(2,3);
        b.show();
    }
}