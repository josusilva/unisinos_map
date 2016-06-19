package unisinos.maps;

import java.util.Arrays;

public class Kruskal {
	
	Graph graph;
	Edge[] edges;
	
	public Kruskal(Graph graph){
		this.graph = graph;
		edges = new Edge[graph.getEdge().size()];
	}
	
	public void ordenaVertices(){
		Arrays.sort(edges);
	}
	
	public int MSTKruskal(){
		
		
		return 0;
	}
	
}
