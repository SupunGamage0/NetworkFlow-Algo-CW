/**
 * Student Name: G.H.G.Supun Pamuditha
 * Student ID: w2053217 / 20222000
 */
package networkflow.algorithm;

import networkflow.model.Edge;
import networkflow.model.Graph;
import java.util.*;

public class EdmondsKarp {
    public int maxFlow(Graph graph, int source, int sink) {
        if (source == sink) {
            throw new IllegalArgumentException("Source and sink cannot be the same.");
        }

        int maxFlow = 0;
        int step = 1;
        Edge[] parent = new Edge[graph.nodes];

        System.out.println("===== MAX FLOW CALCULATION =====");
        System.out.printf("Source: Node %d | Sink: Node %d%n%n", source, sink);

        while (true) {
            System.out.printf("Finding Augmenting Path: %d%n", step);
            boolean foundPath = bfs(graph, source, sink, parent);
            if (!foundPath) {
                System.out.println("No augmenting paths found.\n");
                break;
            }

            int bottleneck = findBottleneck(parent, sink, source);
            updateFlows(parent, sink, source, bottleneck);
            logStepDetails(step, parent, sink, source, bottleneck, maxFlow += bottleneck);

            step++;
            Arrays.fill(parent, null);
        }
        return maxFlow;
    }

    // BFS to find shortest augmenting path
    private boolean bfs(Graph graph, int source, int sink, Edge[] parent) {
        boolean[] visited = new boolean[graph.nodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge edge : graph.adj[u]) {
                if (!visited[edge.to] && edge.getResidual() > 0) {
                    parent[edge.to] = edge;
                    visited[edge.to] = true;
                    queue.add(edge.to);
                    if (edge.to == sink) return true;
                }
            }
        }
        return false;
    }

    // Log step details (path, bottleneck, edge updates)
    private void logStepDetails(int step, Edge[] parent, int sink, int source,
                                int bottleneck, int totalFlow) {
        List<String> path = new ArrayList<>();
        for (int v = sink; v != source; v = parent[v].from) {
            path.add(String.valueOf(v));
        }
        path.add(String.valueOf(source));
        Collections.reverse(path);

        System.out.println("Augmenting Path: " + String.join(" → ", path));
        System.out.println("Bottleneck Capacity: " + bottleneck);
        System.out.println("Edge Updates:");

        Set<Edge> changedEdges = new HashSet<>();
        for (int v = sink; v != source; v = parent[v].from) {
            changedEdges.add(parent[v]);
        }

        for (Edge e : changedEdges) {
            if (e.capacity == 0) continue;
            String status = e.getResidual() == 0 ? "FULL" : "Residual: " + e.getResidual();
            System.out.printf("- %d → %d: Flow=%d/%d (%s)%n",
                    e.from, e.to, e.flow, e.capacity, status);
        }
        System.out.printf("Total Flow: %d%n%n", totalFlow);
    }

    // Helper methods
    private int findBottleneck(Edge[] parent, int sink, int source) {
        int bottleneck = Integer.MAX_VALUE;
        for (int v = sink; v != source; v = parent[v].from) {
            bottleneck = Math.min(bottleneck, parent[v].getResidual());
        }
        return bottleneck;
    }

    private void updateFlows(Edge[] parent, int sink, int source, int bottleneck) {
        for (int v = sink; v != source; v = parent[v].from) {
            Edge e = parent[v];
            e.flow += bottleneck;
            e.reverse.flow -= bottleneck;
        }
    }
}