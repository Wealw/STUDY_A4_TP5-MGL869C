/**
 * Graph Search Patterns
 * Example implementing searches
 *
 * @author Roberto E. Lopez-Herrejon
 * ETS-LOGTI
 */
package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Integrate these methods into your solution
 */
public class Vertex {
    
    // @feature BFS
    static LinkedList<Vertex> Queue = new LinkedList<>();
    public List<Neighbor>     neighbors;
    // Hint: You may add an attribute to indicate if the vertex has been visited or not
    public String             name;
    // Code to implement the DFS and BFS for vertex numbering
    // -----------------------------
    // @feature BFS  or feature DFS selected
    public boolean            visited;
    // @feature NUMBER
    public int                VertexNumber;
    
    public Vertex() {
        name = null;
        neighbors = new LinkedList<>();
    }
    
    public Vertex assignName(String name) {
        this.name = name;
        return this;
    }
    
    public void addNeighbor(Neighbor n) {
        neighbors.add(n);
    }
    
    public boolean equals(Object o) {
        boolean result = false;
        if (!(o instanceof Vertex v))
            return false;
        if (Objects.equals(v.name, this.name))
            result = true;
        return result;
    }
    // ---
    
    public void display() {
        System.out.print(" Node " + name + " connected to: ");
        for (Neighbor theNeighbor : neighbors) {
            if (GraphMain.UNDIRECTED)
                System.out.print(theNeighbor.end.name + ", ");
            Vertex v = theNeighbor.end;
            System.out.print(v.name + ", ");
        } // for all the vertices
        System.out.println();
    } // of display
    
    public void init_vertex(WorkSpace w) {
        visited = false;
        w.init_vertex(this);
    }
    // ---
    
    public void bftNodeSearch(WorkSpace w) {
        Vertex v;
        Vertex header;
        // Step 1: if preVisitAction is true or if we've already
        //         visited this node
        w.preVisitAction(this);
        if (visited)
            return;
        System.out.print(this.name);
        // Step 2: Mark as visited, put the unvisited neighbors in the queue
        //     and make the recursive call on the first element of the queue
        //     if there is such if not you are done
        visited = true;
        // Step 3: do postVisitAction now, you are no longer going through the
        // node again
        w.postVisitAction(this);
        // enqueues the vertices not visited
        for (Neighbor n : neighbors) {
            v = n.end;
            // if the neighbor has not been visited then enqueue
            if (!v.visited)
                Queue.add(v);
        }
        // while there is something in the queue
        while (Queue.size() != 0) {
            header = Queue.get(0);
            Queue.remove(0);
            header.bftNodeSearch(w);
        } // while there is a vertex pending to visit
    } // of bfsNodeSearch
    // ---
    
    // @feature DFS
    public void dftNodeSearch(WorkSpace w) {
        Vertex v;
        // Step 1: Do preVisitAction.
        // If we've already visited this node return
        w.preVisitAction(this);
        if (visited)
            return;
        System.out.print(this.name);
        // Step 2: else remember that we've visited and
        //         visit all neighbors
        visited = true;
        for (Neighbor n : neighbors) {
            v = n.end;
            w.checkNeighborAction(this, v);
            v.dftNodeSearch(w);
        }
        // Step 3: do postVisitAction now
        w.postVisitAction(this);
    } // of dftNodeSearch
    // ----
} // of Vertex
