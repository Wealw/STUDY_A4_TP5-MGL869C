/**
 * Main class
 *
 * @author Roberto E. Lopez-Herrejon
 * ETS MGL869
 */

package graph;

import java.io.IOException;

public class GraphMain {
    
    public static boolean UNDIRECTED = false;
    public static boolean DIRECTED   = false;
    public static boolean WEIGHTED   = true;
    public static boolean DFS        = false;
    public static boolean BFS        = false;
    
    /**
     * Definition of arguments
     * [0] 1 = BFS, 2 = DFS
     * [1] Name of starting vertex, ex.  v0
     * [2] 1 = Directed, 2= Undirected
     * [3] Name of Benchmark file
     */
    public static void main(String[] args) {
        // Step 0: Verify consistency of product configuration
        if (!validConfiguration(args)) {
            System.out.println("Invalid configuration");
            System.exit(0);
        }
        switch (Integer.parseInt(args[0])) {
            case 1 -> BFS = true;
            case 2 -> DFS = true;
        }
        switch (Integer.parseInt(args[2])) {
            case 1 -> DIRECTED = true;
            case 2 -> UNDIRECTED = true;
        }
        // @DEBUG
        if (DFS)
            System.out.println("DFS Selected");
        if (BFS)
            System.out.println("BFS Selected");
        if (UNDIRECTED)
            System.out.println("Undirected graph");
        if (DIRECTED)
            System.out.println("Directed graph");
        System.out.println("Starting vertex= " + args[1]);
        System.out.println("Graph file= " + args[3]);
//    	// @DEBUG
//    	// Finding the path
//    	File f = new File("./src/main/graphs");
//    	for (File file: f.listFiles()) {
//    		System.out.println("File in directory " + file.getName());
//    	}
        // Step 1: create graph object
        Graph g = new Graph();
        // Step 2: sets up the benchmark file to read
        g.openBenchmark(args[3]);
        // Step 3: reads number of vertices, number of edges and weights
        int num_vertices = 0;
        int num_edges    = 0;
        try {
            num_vertices = g.readNumber();
            num_edges = g.readNumber();
            // ignores the additional description files
            g.readNumber();   // undirected, directed
            g.readNumber();   // self loops
            g.readNumber();   // unique
        } catch (IOException e) {
            System.out.println("Error while reading benchmark file configuration values");
            System.exit(0);
        }
        // @DEBUG
        System.out.println("Benchmark file read");
        // Step 4: reserves space for vertices, edges and weights
        Vertex[] V             = new Vertex[num_vertices];
        int[]    startVertices = new int[num_edges];
        int[]    endVertices   = new int[num_edges];
        // @feature WEIGHTED selected
        int[] weights = new int[num_edges];
        // Step 5: creates the vertices objects
        int i;
        for (i = 0; i < num_vertices; i++) {
            V[i] = new Vertex().assignName("v" + i);
            g.addVertex(V[i]);
        }
        // Step 6: reads the edges
        try {
            for (i = 0; i < num_edges; i++) {
                startVertices[i] = g.readNumber();
                endVertices[i] = g.readNumber();
            }
        } catch (IOException e) {
            System.out.println("Error while reading the edges");
            System.exit(0);
        }
        // @feature WEIGHTED selected
        // Step 7: reads the weights
        if (WEIGHTED) {
            try {
                for (i = 0; i < num_edges; i++)
                    weights[i] = g.readNumber();
            } catch (IOException e) {
                System.out.println("Error while reading the weights");
                System.exit(0);
            }
        }
        // ----
        // Stops the benchmark reading
        g.stopBenchmark();
        // Step 8: Adds the edges
        for (i = 0; i < num_edges; i++) {
            //@feature WEIGHTED selected or not
            if (WEIGHTED) {
                g.addAnEdge(V[startVertices[i]], V[endVertices[i]], weights[i]);
            }
            else {
                g.addAnEdge(V[startVertices[i]], V[endVertices[i]]);
            }
        }
        g.display();
        // Call the methods here starting on the starting vertex
        if (BFS)
            g.BFS(args[1]);
        else
            g.DFS(args[1]);
    } // main
    
    /**
     * Definition of arguments
     * [0] 1 = BFS, 2 = DFS
     * [1] Name of starting vertex, ex.  v0
     * [2] 1 = Directed, 2= Undirected
     * [3] Name of Benchmark file
     * Note: The default implementation reads the weights as well.
     */
    private static boolean validConfiguration(String[] args) {
        // Does not have the 4 arguments required
        if (args.length != 4)
            return false;
        // Checks args[0] algorithm to execute
        int algorithm = Integer.parseInt(args[0]);
        if (algorithm != 1 && algorithm != 2)
            return false;
        // Note: No checks for args[1] here. Your DFS et BFS algorithms will verify that before executing
        // Checks args[2] for type of graph
        int typeGraph = Integer.parseInt(args[2]);
        return typeGraph == 1 || typeGraph == 2;
        // Note: The existence of the file name is not verified here
    } // of validConfiguration
} // of GraphMain
