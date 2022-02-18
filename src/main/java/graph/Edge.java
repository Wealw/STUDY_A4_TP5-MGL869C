/**
 * Class Edge
 *
 * @author Roberto E. Lopez-Herrejon
 * ETS MGL869
 */
package graph;

public class Edge extends Neighbor {
    public Vertex start;
    
    public Edge(Vertex the_start, Vertex the_end) {
        start = the_start;
        end = the_end;
    }
    
    public void adjustAdorns(Edge the_edge) {
        weight = the_edge.weight;
    }
    
    public void display() {
        System.out.print(" start=" + start.name + " end=" + end.name + " weight=" + weight);
        System.out.println();
    }
    
    public int weight;
    
    public Edge(Vertex the_start, Vertex the_end, int the_weight) {
        this(the_start, the_end);
        weight = the_weight;
    }
    
    
    public String toString() {
        return " start=" + start.name + " end=" + end.name + " weight=" + weight;
    }
} // of Edge
