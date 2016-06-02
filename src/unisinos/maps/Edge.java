package unisinos.maps;

public class Edge<E> {
	private E element; 
	private Vertex origin;
	private Vertex destiny;
	
	public Edge(E element, Vertex origin, Vertex destiny) {
		this.element = element;
		this.origin = origin;
		this.destiny = destiny;
	}
	
	public Vertex getDestiny() {
		return destiny;
	}
	
	public void setDestiny(Vertex destiny) {
		this.destiny = destiny;
	}
	
	public Vertex getOrigin() {
		return origin;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	public E getElement() {
		return element;
	}

}
