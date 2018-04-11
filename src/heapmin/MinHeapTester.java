/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heapmin;


/**
 *
 * @author UmairAkhtar
 */
public class MinHeapTester {
public void runTests(){
   MinHeap<String> heap = new MinHeap<String>();
   heap.addElement("John");
   heap.addElement("Paul");
   heap.addElement("George");
   heap.addElement("Ringo");
   heap.addElement("Elmer");
   heap.addElement("Zorro");
   System.out.println(heap);
   heap.removeMin();
   System.out.println(heap);
}
public static void main(String[] args) {
  MinHeapTester mht = new MinHeapTester();
  mht.runTests();
}
}
