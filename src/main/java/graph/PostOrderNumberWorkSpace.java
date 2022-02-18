package graph;

public class PostOrderNumberWorkSpace extends WorkSpace {
    int vertexCounter;
    
    public PostOrderNumberWorkSpace() {
        vertexCounter = 0;
    }
    
    @Override
    public void printMethod() {
        System.out.println("Post-Order traversal");
    }
    
    public void postVisitAction(Vertex v) {
        if (v.visited)
            v.VertexNumber = vertexCounter++;
        System.out.println(v.name + " " + "visited :" + v.VertexNumber);
    }
}
