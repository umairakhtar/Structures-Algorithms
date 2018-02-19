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

public class BNode
{
    static int t;
    int count;
    int key[];
    BNode child[];
    boolean leaf;
    BNode parent;

    public BNode()
    {}

    public BNode(int t, BNode parent)
    {
        this.t = t;
        this.parent = parent;
        key = new int[2*t - 1];
        child = new BNode[2*t];
        leaf = true;
        count = 0;
    }

    public int getValue(int index)
    {
        return key[index];
    }

    public BNode getChild(int index)
    {
        return child[index];
    }    
}