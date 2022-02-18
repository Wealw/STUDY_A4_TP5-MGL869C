/**
 * Graph Search Patterns
 * Example implementing searches
 *
 * @author Roberto E. Lopez-Herrejon
 * ETS-LOGTI
 */
package graph;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * Integrate these methods into your solution
 */
public class Graph {
    // **************************************************
    // Management of graph file for benchmarking
    // @feature BENCH
    public Reader             inFile; // File handler for reading
    // Lists of vertices and edges
    public LinkedList<Vertex> vertices;
    public LinkedList<Edge>   edges;
    
    /**
     * Constructor of the Graph class that initializes the vertices and edges.
     */
    public Graph() {
        vertices = new LinkedList<>();
        edges = new LinkedList<>();
    }
    
    /**
     * Opens the benchmark file
     */
    public void openBenchmark(String FileName) {
        try {
            inFile = new FileReader(FileName);
        } catch (IOException e) {
            System.out.println("Your file " + FileName + " cannot be read");
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    /**
     * Attempts to close the benchmark file.
     */
    public void stopBenchmark() {
        try {
            inFile.close();
        } catch (IOException e) {
            System.out.println("Error while closing the benchmark file");
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public int readNumber() throws IOException {
        int    index = 0;
        char[] word  = new char[80];
        int    ch;
        ch = inFile.read();
        while (ch == 32)
            ch = inFile.read(); // skips extra whitespaces
        while (ch != -1 && ch != 32 && ch != 10) // while it is not EOF, WS, NL
        {
            word[index++] = (char) ch;
            ch = inFile.read();
        }
        word[index] = 0;
        String theString = new String(word);
        theString = theString.substring(0, index)
                             .trim();
        return Integer.parseInt(theString, 10);
    }
    
    /**
     * Adds an edge without weights
     *
     * @param start vertex of the edge
     * @param end   vertex of the edge
     */
    public void addAnEdge(Vertex start, Vertex end) {
        Edge theEdge = new Edge(start, end);
        addEdge(theEdge);
    }
    
    /**
     * Adds an edge with weights
     *
     * @param start vertex of the edge
     * @param end   vertex of the edge
     */
    public void addAnEdge(Vertex start, Vertex end, int weight) {
        Edge theEdge = new Edge(start, end, weight);
        addEdge(theEdge);
    }
    
    /**
     * Adds a vertex to the list of vertices
     *
     * @param v Vertex to add
     */
    public void addVertex(Vertex v) {
        vertices.add(v);
    }
    
    /**
     * Adds an edge based from
     *
     * @param the_edge An edge instance
     */
    public void addEdge(Edge the_edge) {
        Vertex start = the_edge.start;
        Vertex end   = the_edge.end;
        edges.add(the_edge);
        start.addNeighbor(new Neighbor(end, the_edge));
        // if the graph is undirected
        if (GraphMain.UNDIRECTED)
            end.addNeighbor(new Neighbor(start, the_edge));
        // ---
    } //of addEdge
    
    
    /**
     * Finds a vertex given its name in the vertices list
     *
     * @param theName The name of the vertex you're looking for
     * @return The instance of the vertex
     */
    public Vertex findsVertex(String theName) {
        // if we are dealing with the root
        if (theName == null)
            return null;
        for (Vertex theVertex : vertices)
            if (theName.equals(theVertex.name))
                return theVertex;
        return null;
    } // of findsVertex
    
    
    /**
     * Displays the list of vertices and edges
     */
    public void display() {
        int i;
        System.out.println("******************************************");
        System.out.println("Vertices ");
        for (i = 0; i < vertices.size(); i++)
            vertices.get(i)
                    .display();
        System.out.println("******************************************");
        System.out.println("Edges ");
        for (i = 0; i < edges.size(); i++)
            edges.get(i)
                 .display();
        System.out.println("******************************************");
    } // of display
    // **********************************************
    // **********************************************
    // Implement your search methods here
    
    /**
     * Your implementation of DFS
     * Note that this method displays each node in the traversal order
     *
     * @param vertexName The starting vertex of the DFS exploration
     */
    public void DFS(String vertexName) {
        System.out.println("DFS :");
        Vertex startVertex = findsVertex(vertexName);
        // If start vertex does not exist
        if (startVertex==null) {
            System.out.println("Start vertex does not exist");
            return;
        }
        // if there are no vertices in the graph, then finish
        if ( vertices.size() == 0 ) return;
        // Step 1. Initialization of vertices
        for (Vertex v : vertices)
            v.visited = false;
        // Step 2: traverse neighbors of each node
        for (Vertex v : vertices) {
            if (!v.visited ) {
                v.dftNodeSearch(new NumberWorkSpace());
            }
        }
        System.out.println();
        System.out.println("******************************************");
    } // of DFS
    
    private void exploreChildren(Vertex vertex, List<Vertex> visited_vertices) {
        for (Edge edge : edges) {
            if (edge.start == vertex && !visited_vertices.contains(edge.end)) {
                visited_vertices.add(edge.end);
                exploreChildren(edge.end, visited_vertices);
            }
        }
    }
    
    
    /**
     * Your implementation of BFS
     * Note that this method displays each node in the traversal order
     *
     * @param vertexName The starting vertex of the BFS exploration
     */
    public void BFS(String vertexName) {
        System.out.println("BFS :");
        Vertex startVertex = findsVertex(vertexName);
        // If start vertex does not exist
        if (startVertex == null) {
            System.out.println("Start vertex does not exist");
            return;
        }
        // if there are no vertices in the graph, then finish
        if (vertices.size() == 0)
            return;
        // Step 1. Initialization of vertices
        for (Vertex v : vertices)
            v.visited = false;
        // Step 2: traverse neighbors of each node
        for (Vertex v : vertices) {
            if (!v.visited) {
                v.bftNodeSearch(new NumberWorkSpace());
            }
        }
        System.out.println();
        System.out.println("******************************************");
    } // of BFS
    
    
    // numbers vertices in pre-order form
    public void NumberVertices() {
        GraphSearch(new NumberWorkSpace());
    }
    
    /**
     * Performs a search on the graph, either in BFS or DFS mode.
     *
     * @param w The context of the method call
     */
    public void GraphSearch(WorkSpace w) {
        // Step 1: initialize visited member of all nodes
        // if there are no vertices in the graph, then finish
        if (vertices.size() == 0)
            return;
        // Initializes the vertices calling the method init_vertex
        for (Vertex v : vertices)
            v.init_vertex(w);
        // Step 2: traverse neighbors of each node
        for (Vertex v : vertices) {
            if (!v.visited) {
                w.nextRegionAction(v);
                // Note: Because we manually check that they are mutually exclusive, actually at most one of the
                // two searches will be called
                // @feature BFS
                if (GraphMain.BFS) {
                    // System.out.println("BFS " + v.name);
                    v.bftNodeSearch(w);
                }
                // ---
                // @feature DFS
                if (GraphMain.DFS) {
                    // System.out.println("DFS " + v.name);
                    v.dftNodeSearch(w);
                }
                // --
            } // if not visited
        } // for
    }   // GraphSearch
} // of Graph
