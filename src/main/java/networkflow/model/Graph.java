/**
 * Student Name: G.H.G.Supun Pamuditha
 * Student ID: w2053217 / 20222000
 */
package networkflow.model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public final int nodes;     // Total nodes
    public final List<Edge>[] adj; // Adjacency list

    @SuppressWarnings("unchecked")
    public Graph(int nodes) {
        this.nodes = nodes;
        adj = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    /**
     * Adds a directed edge and its reverse edge to the graph.
     */
    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(from, to, capacity);
        Edge backward = new Edge(to, from, 0);
        forward.reverse = backward;
        backward.reverse = forward;
        adj[from].add(forward);
        adj[to].add(backward);
    }
}