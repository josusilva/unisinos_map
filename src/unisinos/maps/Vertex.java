package unisinos.maps;

import java.util.ArrayList;
import java.util.LinkedList;

public class Vertex<E> {
	
	private E element;
	private LinkedList<Vertex> adjacency;
	
	public Vertex(E element) {
		this.element = element;
		adjacency = new LinkedList<Vertex>();
	}
	
	public void addAdjacency(Vertex adj){
		adjacency.add(adj);
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public LinkedList<Vertex> getAdjacency() {
		return adjacency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		return result;
	}

	@Override
	
	
	public String toString(){
		return element.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		return true;
	}

	
	

	
	
	
	

}
