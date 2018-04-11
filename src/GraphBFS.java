/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UmairAkhtar
 */
import java.io.*;
import java.util.*;

class GraphBFS
{
    private int V;
    private LinkedList<Integer> adj[];

    GraphBFS(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }

    void BFS(int s)
    {
        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0)
        {
            s = queue.poll();
            System.out.print(s+" ");
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String args[])
    {
        GraphBFS g = new GraphBFS(7);

        g.addEdge(4, 5);
        g.addEdge(3, 5);
        g.addEdge(2, 6);
        g.addEdge(5, 6);
        g.addEdge(4, 6);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(1, 2);
        g.addEdge(3, 6);

        System.out.println("Following is Breadth First Traversal "+
                           "(starting from vertex 1)");

        g.BFS(1);
    }
}