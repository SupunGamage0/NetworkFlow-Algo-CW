/**
 * Student Name: G.H.G.Supun Pamuditha
 * Student ID: w2053217 / 20222000
 */
package networkflow.model;
public class Edge {
    public final int from;     // Source node
    public final int to;       // Destination node
    public final int capacity; // Maximum capacity
    public int flow;           // Current flow
    public Edge reverse;       // Reverse edge for residual calculations

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    /**
     * Returns the residual capacity of the edge.
     */
    public int getResidual() {
        return capacity - flow;
    }
}