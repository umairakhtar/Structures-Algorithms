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
 
class GraphDFS
{
    private int V;
 
    private LinkedList<Integer> adj[];
 
    GraphDFS(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 
    void addEdge(int v, int w)
    {
        adj[v].add(w);
    }
 
    void DFSUtil(int v,boolean visited[])
    {
        visited[v] = true;
        System.out.print(v+" ");
 
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
 
    void DFS(int v)
    {
        boolean visited[] = new boolean[V];
 
        DFSUtil(v, visited);
    }
 
    public static void main(String args[])
    {
        GraphDFS g = new GraphDFS(7);
 
        g.addEdge(4, 5);
        g.addEdge(3, 5);
        g.addEdge(2, 6);
        g.addEdge(5, 6);
        g.addEdge(4, 6);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(1, 2);
        g.addEdge(3, 6);
 
        System.out.println("Following is Depth First Traversal "+
                           "(starting from vertex 1)");
 
        g.DFS(1);
    }
}