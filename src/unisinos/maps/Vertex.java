package unisinos.maps;

import java.util.ArrayList;
import java.util.LinkedList;

public class Vertex {
	
	private Point point;
	private LinkedList<Vertex> adjacency;
	private char color = 'w';		
	
	public Vertex(Point point) {
		this.point = point;
		adjacency = new LinkedList<Vertex>();
	}
	
	public void addAdjacency(Vertex adj){
		adjacency.add(adj);
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
	
	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	public void setAdjacency(LinkedList<Vertex> adjacency) {
		this.adjacency = adjacency;
	}

	public LinkedList<Vertex> getAdjacency() {
		return adjacency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		return result;
	}

	@Override
	
	
	public String toString(){
		return point.toString();
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
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}

	
	

	
	
	
	

}
