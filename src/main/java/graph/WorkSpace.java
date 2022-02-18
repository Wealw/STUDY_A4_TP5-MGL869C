/**
 * Graph Search Patterns
 * Example implementing searches
 *
 * @author Roberto E. Lopez-Herrejon
 * ETS-LOGTI
 */
package graph;

//supply template actions
public abstract class WorkSpace {
    public void init_vertex(Vertex v) {}
    
    public void preVisitAction(Vertex v) {}
    
    public void postVisitAction(Vertex v) {}
    
    public void nextRegionAction(Vertex v) {}
    
    @SuppressWarnings ("SpellCheckingInspection")
    public void checkNeighborAction(Vertex vsource, Vertex vtarget) {}
    
    public void printMethod() {}
}
