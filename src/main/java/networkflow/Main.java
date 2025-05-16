/**
 * Student Name: G.H.G.Supun Pamuditha
 * Student ID: w2053217 / 20222000
 */
package networkflow;

import networkflow.model.Edge;
import networkflow.model.Graph;
import networkflow.parser.FileParser;
import networkflow.algorithm.EdmondsKarp;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = null;
        String inputFile;

        // Keep retrying until valid file is provided
        while (true) {
            System.out.print("Enter benchmark file name: ");
            inputFile = scanner.nextLine().trim();

            try {
                FileParser parser = new FileParser();
                graph = parser.parse(inputFile);
                break; // Exit loop if file is valid
            } catch (IOException e) {
                System.out.println("\nError: File '" + inputFile + "' not found. Try again.\n");
            }
        }

        // Run algorithm and measure time
        try {
            long startTime = System.nanoTime();
            EdmondsKarp solver = new EdmondsKarp();
            int maxFlow = solver.maxFlow(graph, 0, graph.nodes - 1);
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000_000.0;

            printFinalResult(graph, maxFlow, duration);
        } catch (Exception e) {
            System.out.println("Error during calculation: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void printFinalResult(Graph graph, int maxFlow, double duration) {
        System.out.println("\n===== FINAL RESULT =====");
        System.out.println("Flow Assignment on Each Edge:");

        // Print all edges with flow values
        for (int u = 0; u < graph.nodes; u++) {
            for (Edge e : graph.adj[u]) {
                if (e.capacity > 0) { // Skip reverse edges
                    System.out.printf("- Edge %d → %d: Flow=%d/%d%n",
                            e.from, e.to, e.flow, e.capacity);
                }
            }
        }

        // Display results at bottom
        System.out.printf("\nExecution Time: %.4f seconds%n", duration);
        System.out.println("Maximum Flow Value: " + maxFlow);
    }
}