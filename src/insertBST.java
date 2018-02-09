/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UmairAkhtar
 */
public class insertBST {
    class Node {
        int key;
        Node left, right;
 
        public Node(int item) {
            key = item;
            left = right = null;
        }
    }
 
    Node root;
 
    insertBST() { 
        root = null; 
    }
 
    void insert(int key) {
       root = insertRec(root, key);
    }
     
    Node insertRec(Node root, int key) {
 
        if (root == null) {
            root = new Node(key);
            return root;
        }
 
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
 
        return root;
    }
 
    void inorder()  {
       inorderRec(root);
    }
 
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }
 
    public static void main(String[] args) {
        insertBST tree = new insertBST();
 
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        tree.insert(30);
        tree.insert(40);
        tree.insert(23);
        tree.insert(58);
        tree.insert(48);
        tree.insert(26);
        tree.insert(11);
        tree.insert(13);
        System.out.println("Inorder traversal:");
        tree.inorder();
    }
}
