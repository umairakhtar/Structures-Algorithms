/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UmairAkhtar
 */
//Output: Hash code of This is a Test is 1413015013
public class hashcode {
    public static void main(String[] args) {
        
      String Obj = new String("This is a Test");

      int icode;
      icode = Obj.hashCode();

      System.out.println("Hash code of "+Obj+" is "+icode);
   }
}
