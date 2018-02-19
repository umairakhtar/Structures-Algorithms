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
public class Record {
    private int key;
    private BNode leftNode;
    private BNode rightNode;

    public Record(int key, BNode leftNode, BNode rightNode) {
        this.key = key;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Record(int key){
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public BNode getLeftNode() {
        return leftNode;
    }

    public BNode getRightNode() {
        return rightNode;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setLeftNode(BNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(BNode rightNode) {
        this.rightNode = rightNode;
    }
}