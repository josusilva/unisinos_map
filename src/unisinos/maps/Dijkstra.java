package unisinos.maps;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
	private Graph graph;
	public Path optimalPath;
	
	public Dijkstra(Graph graph){
		this.graph = graph;
	}
	
    public void findShortestPath(int toId,int fromId, Path path){
    		Vertex from = graph.getVertexById(fromId);
    		Vertex to = graph.getVertexById(toId);
    		
    		List<Edge> edges = getVertexEdges(from); 
    		
            for(Edge edge : edges){
                    Path newPath = new Path();
                    if(path != null ){
                            if(path.edges.contains(edge)){
                                    continue;
                            }
                            
                            newPath.edges.addAll(path.edges);
                            newPath.pathWeight += path.pathWeight;
                            
                    }
                    newPath.edges.add(edge);
                    newPath.pathWeight += edge.getWeigth();
                    if(edge.getVertex1().getPoint().getId() == (toId) || edge.getVertex2().getPoint().getId() == (toId)){
                            if(optimalPath == null || optimalPath.pathWeight > newPath.pathWeight){
                                    optimalPath = newPath;
                            }
                            return;
                    }
                    Vertex newFrom = edge.getVertex1().getPoint().getId() == (fromId) ? edge.getVertex2() : edge.getVertex1();
                    findShortestPath(newFrom.getPoint().getId(), to.getPoint().getId(), newPath);                    
            }
    }
    
    private List<Edge> getVertexEdges(Vertex vertex){
    	return graph.findEdges(vertex);
    }
}