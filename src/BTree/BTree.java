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
public class BTree
{
    static int order;
    BNode root;
    public BTree(int order)
    {
        this.order = order;
        root = new BNode(order, null);
    }
    public void split(BNode x, int i, BNode y)
    {
        BNode z = new BNode(order,null);
        z.leaf = y.leaf;
        z.count = order - 1;
        for(int j = 0; j < order - 1; j++)
        {
                z.key[j] = y.key[j+order];
        }
        if(!y.leaf)
        {
                for(int k = 0; k < order; k++)
                {
                        z.child[k] = y.child[k+order];
                }
        }

        y.count = order - 1;

        for(int j = x.count ; j> i ; j--)
        {
            x.child[j+1] = x.child[j];
        }
        x.child[i+1] = z;
        for(int j = x.count; j> i; j--)
        {
                x.key[j + 1] = x.key[j];
        }
        x.key[i] = y.key[order-1];
        y.key[order-1 ] = 0;
        for(int j = 0; j < order - 1; j++)
        {
                y.key[j + order] = 0;
        }
        x.count ++;
    }
    public void nonfullInsert(BNode x, int key)
    {
        int i = x.count;
        if(x.leaf)
        {
            while(i >= 1 && key < x.key[i-1])
            {
                x.key[i] = x.key[i-1];
                i--;
            }
            x.key[i] = key;
            x.count ++;
        }
        else
        {
            int j = 0;
            while(j < x.count  && key > x.key[j])
            {		
                j++;
            }
            if(x.child[j].count == order*2 - 1)
            {
                split(x,j,x.child[j]);
                if(key > x.key[j])
                {
                    j++;
                }
            }
            nonfullInsert(x.child[j],key);
        }
    }
    public void insert(BTree t, int key)
    {
        BNode r = t.root;
        if(r.count == 2*order - 1)
        {
            BNode s = new BNode(order,null);
            t.root = s;	
            s.leaf = false;
            s.count = 0;
            s.child[0] = r;
            split(s,0,r);
            nonfullInsert(s, key);
        }
        else
            nonfullInsert(r,key);
    }
    public void print(BNode n)
    {
        for(int i = 0; i < n.count; i++)
        {
            System.out.print(n.getValue(i)+" " );
        }
        if(!n.leaf)
        {
            for(int j = 0; j <= n.count  ; j++)
            {
                if(n.getChild(j) != null)
                {
                System.out.println();
                print(n.getChild(j));
                }
            }
        }
    }
}