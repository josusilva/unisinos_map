package unisinos.maps;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Path{ 
	private LinkedList<Edge> edges = new LinkedList<Edge>() ;
	private double pathWeight;
	
	public Path(LinkedList<Vertex> vertex, Graph graph){
		pathWeight = 0;
		for (int i = 0; i < vertex.size()-1; i++){
			edges.add(graph.findEdges(vertex.get(i), vertex.get(i+1)));
		}
		
		for (int i = 0; i < edges.size(); i++){
			pathWeight = pathWeight + edges.get(i).getWeigth();
		}
	}
	
	public double getPathWeight() {
		return pathWeight;
	}

	public void setPathWeight(double pathWeight) {
		this.pathWeight = pathWeight;
	}

	public LinkedList<Edge> getEdges(){
		return edges;
	}
	
	public void printEdges(){
		for(int i = 0; i < edges.size(); i++){
			System.out.println(edges.get(i).toString());
		}
	}
	
}
