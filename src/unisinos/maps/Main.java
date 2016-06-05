package unisinos.maps;

import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Graph teste = new Graph();
		
		String vertex1E = "vertex1";
		String vertex2E = "vertex2";
		String vertex3E = "vertex3";
		String edge1E = "edge1";
		String edge2E = "edge2";
		String edge3E = "edge3";
		
		Vertex vertex1 = teste.insertVertex(vertex1E);
		Vertex vertex2 = teste.insertVertex(vertex2E);
		Vertex vertex3 = teste.insertVertex(vertex3E);
		Edge edge1 = teste.insertEdge(vertex1, vertex2, edge1E);
		Edge edge2 = teste.insertEdge(vertex1, vertex3, edge1E);
		Edge edge3 = teste.insertEdge(vertex2, vertex3, edge1E);
		
		Vertex[] endVertices = teste.endVertices(edge1);
		
		System.out.println(endVertices[0].getAdjacency().get(1).toString());
		System.out.println(endVertices[1].getAdjacency().get(0).toString());
		
		Vertex opposite = teste.opposite(vertex1, edge1);
		
		System.out.println(opposite.getElement().toString());
		
		if (teste.areAdjacent(vertex2, vertex3) == true){
			System.out.println("São Adjacentes");
		}else{
			System.out.println("Não São Adjacentes");
		}
		
		String vertex1EN = "vertex1N";
		
		teste.replaceVertex(vertex1, vertex1EN);
		
		Vertex opposite2 = teste.opposite(vertex2, edge1);
		
		System.out.println(opposite2.getElement().toString());
		
		Vertex[] endVertices2 = teste.endVertices(edge3);
		System.out.println("------------------------------------");
		System.out.println(endVertices2[0].getAdjacency().get(1).toString());
		System.out.println(endVertices2[1].getAdjacency().get(1).toString());
		
		String edge1EN = "edge1N";
		
		teste.replaceEdge(edge1, edge1EN);
		
		teste.removeVertex(vertex2);
		System.out.println("------------------------------------");
		LinkedList vertex = teste.getVertex();
		
		for(int i=0; i < vertex.size(); i++){
			Vertex temp = (Vertex) vertex.get(i);
			System.out.println(temp.getElement().toString());
			System.out.println("Adjacency: "+temp.getAdjacency().get(0));
		}
	}

}
