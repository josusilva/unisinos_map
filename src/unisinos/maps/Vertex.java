package unisinos.maps;

import java.util.ArrayList;

public class Vertex<E> {
	
	private E element;
	
	private ArrayList<Edge> adjacency;
	
	public Vertex(E element) {
		this.element = element;
		this.adjacency = new ArrayList<Edge>();
	}
		
	public void addEdge(Edge edge){
		adjacency.add(edge);
	}
	
	public E getElement() {
		return element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}

}
