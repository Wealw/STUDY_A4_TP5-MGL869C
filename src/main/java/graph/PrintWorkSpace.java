package graph;

public class PrintWorkSpace extends WorkSpace{
    int vertexCounter;
    
    public PrintWorkSpace() {
        vertexCounter = 0;
    }
    
    @Override
    public void printMethod() {
        System.out.println("Pre-Order traversal");
    }
    
    @Override
    public void preVisitAction(Vertex v) {
        // This assigns the values on the way in
        if (!v.visited)
            v.VertexNumber = vertexCounter++;
        else {
            return;
        }
        System.out.println(v.name + " ");
    }
}
