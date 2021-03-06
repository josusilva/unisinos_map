// Nós (Josué Silva, Giordano Trombetta, Fabio Junqueira), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho é resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.


package unisinos.maps;

import java.util.LinkedList;

public class Graph<E>{
	
	private LinkedList<Edge> edge;
	private LinkedList<Vertex> vertex;
	
	
	Graph(){
		edge = new LinkedList<Edge>();
		vertex = new LinkedList<Vertex>();
	}
	
	public Vertex[] endVertices(Edge e){
		Edge tempEdge = edge.get(edge.indexOf(e));
		Vertex[] tempVertex = new Vertex[2];
		tempVertex[0] = tempEdge.getVertex1();
		tempVertex[1] = tempEdge.getVertex2();
		return tempVertex;
	}
	
	public Vertex opposite(Vertex v,Edge e){
		Edge temp = edge.get(edge.indexOf(e));
		if (temp.getVertex1().equals(v)){
			return temp.getVertex2();
		}else{
			return temp.getVertex1();
		}
	}
	
	public boolean areAdjacent(Vertex v, Vertex w){
		Vertex temp = vertex.get(vertex.indexOf(v));
		for(int i = 0; i < temp.getAdjacency().size(); i++){
			if (w.equals(temp.getAdjacency().get(i))){
				return true;
			}
		}
		return false;
	}
	
	public <E> void replaceEdge(Edge e, String dislocate){
		edge.get(edge.indexOf(e)).setDislocate(dislocate);
	}
	
	public <E> void replaceVertex(Vertex v, Point point){
		int index = vertex.indexOf(v);
		Vertex oldVertex = vertex.get(index);
		vertex.get(vertex.indexOf(v)).setPoint(point);
		Vertex newVertex = vertex.get(index); 
		for(int i = 0; i < vertex.get(index).getAdjacency().size(); i++){
				Vertex tempVertex = (Vertex) vertex.get(index).getAdjacency().get(i);
				int temp = vertex.get(vertex.indexOf(tempVertex)).getAdjacency().indexOf(oldVertex);
				vertex.get(vertex.indexOf(tempVertex)).getAdjacency().remove(oldVertex);
				vertex.get(vertex.indexOf(tempVertex)).getAdjacency().add(newVertex);
		}
	}
	
	public Vertex insertVertex(Point point){
		Vertex newVertex = new Vertex(point);
		vertex.add(newVertex);
		return vertex.get(vertex.indexOf(newVertex));
	}
	
	public Edge insertEdge(Vertex vertex1, Vertex vertex2, String dislocate){
		Edge newEdge = new Edge(vertex1, vertex2, 0,dislocate);
		edge.add(newEdge);
		vertex.get(vertex.indexOf(vertex1)).getAdjacency().add(vertex2);
		vertex.get(vertex.indexOf(vertex2)).getAdjacency().add(vertex1);
		return edge.get(edge.indexOf(newEdge));
	}
	
	public Edge insertEdge(Vertex vertex1, Vertex vertex2, String dislocate, int weigth){
		Edge newEdge = new Edge(vertex1, vertex2, weigth,dislocate);
		edge.add(newEdge);
		vertex.get(vertex.indexOf(vertex1)).getAdjacency().add(vertex2);
		vertex.get(vertex.indexOf(vertex2)).getAdjacency().add(vertex1);
		return edge.get(edge.indexOf(newEdge));
	}
	
	public E removeEdge(Edge e){
		Vertex[] endVertices = endVertices(e);
		E element = (E) edge.get(edge.indexOf(e)).getDislocate();
		edge.remove(e);
		vertex.get(vertex.indexOf(endVertices[0])).getAdjacency().remove(endVertices[1]);
		vertex.get(vertex.indexOf(endVertices[1])).getAdjacency().remove(endVertices[0]);
		return element;
	}
	
	public Point removeVertex(Vertex v){
		Vertex removed = vertex.get(vertex.indexOf(v));
		LinkedList<Edge> edges = new LinkedList();
		edges = findEdges(v);
		for (int i=0; i < edges.size(); i++){
			removeEdge(edges.get(i));
		}
		vertex.remove(v);
		return removed.getPoint();
	}

	public LinkedList<Edge> getEdge() {
		return edge;
	}

	public LinkedList<Vertex> getVertex() {
		return vertex;
	}
	
	public void setVertex(LinkedList<Vertex> vertex){
		this.vertex = vertex;
	}
	
	public LinkedList<Edge> findEdges(Vertex v){
		LinkedList<Edge> edges = new LinkedList();
		for (int i=0; i<edge.size(); i++){
			if (edge.get(i).getVertex1().equals(v)){
				edges.add(edge.get(i));
			}else if (edge.get(i).getVertex2().equals(v)){
				edges.add(edge.get(i));
			}
		}
		return edges;
	}
	
	public Edge findEdges(Vertex v1, Vertex v2){
		for (int i=0; i<edge.size(); i++){
			if (edge.get(i).getVertex1().equals(v1) && edge.get(i).getVertex2().equals(v2)){
				return edge.get(i);
			}else if (edge.get(i).getVertex1().equals(v2) && edge.get(i).getVertex2().equals(v1)){
				return edge.get(i);
			}
		}
		return null;
	}
	
	public String edgeValue(Edge e){
		return edge.get(edge.indexOf(e)).getDislocate();
	}
	
	public Point vertexValue(Edge e){
		return vertex.get(vertex.indexOf(e)).getPoint();
	}
	
	public Vertex findVertex(double lat, double lng){
		Vertex v = null;
		
		for (Vertex v1 : vertex) {
			if(v1.getPoint().getLat() == lat && v1.getPoint().getLng() == lng){
				v = v1;
			}
		}
		
		return v;
	}
	
	public Vertex getVertexById(int id){
		Vertex returnVertex = null;
		for (Vertex v : vertex){
			if(v.getPoint().getId() == id){
				returnVertex = v;
			}
		}
		return returnVertex;
	}
}
