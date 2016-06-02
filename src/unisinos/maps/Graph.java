package unisinos.maps;

import java.util.ArrayList;

public class Graph<E> {
	//
	private ArrayList<Vertex> vertex = new ArrayList<Vertex>();
	
	public void insertVertex(E element){
		Vertex temp = new Vertex(element);
		vertex.add((Vertex) element);
	}
	
	
	
	
}
