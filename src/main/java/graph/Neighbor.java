/**
 * Neighbor class
 *
 * @author Roberto E. Lopez-Herrejon
 * ETS MGL869
 */
package graph;

public class Neighbor {
    public Vertex end;
    public Edge   edge;
    
    public Neighbor() {
        end = null;
        edge = null;
    }
    
    public Neighbor(Vertex v, Edge e) {
        end = v;
        edge = e;
    }
    
    public String toString() {
        return end.name + " " + edge.toString();
    }
}
