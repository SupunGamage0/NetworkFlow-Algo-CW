/**
 * Student Name: G.H.G.Supun Pamuditha
 * Student ID: w2053217 / 20222000
 */
package networkflow.parser;

import networkflow.model.Graph;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FileParser {
    public Graph parse(String filePath) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/" + filePath);
        if (inputStream == null) {
            throw new IOException("File not found: " + filePath);
        }

        Scanner sc = new Scanner(inputStream);
        int nodes = parseNodeCount(sc);
        Graph graph = new Graph(nodes);
        Set<String> existingEdges = new HashSet<>();
        int lineNumber = 1;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            if (parts.length != 3) {
                throw new IOException("Invalid edge format at line " + lineNumber);
            }

            processEdge(parts, graph, existingEdges, lineNumber);
            lineNumber++;
        }

        sc.close();
        return graph;
    }

    // Helper methods
    private int parseNodeCount(Scanner sc) throws IOException {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IOException("Invalid node count format");
        }
    }

    private void processEdge(String[] parts, Graph graph, Set<String> edges, int lineNum) throws IOException {
        int from = Integer.parseInt(parts[0]);
        int to = Integer.parseInt(parts[1]);
        int capacity = Integer.parseInt(parts[2]);

        validateNode(from, graph.nodes);
        validateNode(to, graph.nodes);
        validateCapacity(capacity);
        checkDuplicateEdge(edges, from, to, lineNum);

        graph.addEdge(from, to, capacity);
    }

    private void validateNode(int node, int max) throws IOException {
        if (node < 0 || node >= max) {
            throw new IOException("Node must be between 0 and " + (max - 1));
        }
    }

    private void validateCapacity(int capacity) throws IOException {
        if (capacity <= 0) throw new IOException("Capacity must be > 0");
    }

    private void checkDuplicateEdge(Set<String> edges, int from, int to, int lineNum) throws IOException {
        String key = from + "->" + to;
        if (edges.contains(key)) {
            throw new IOException("Duplicate edge at line " + lineNum);
        }
        edges.add(key);
    }
}