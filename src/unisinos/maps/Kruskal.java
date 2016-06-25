// Nós (Josué Silva, Giordano Trombetta, Fabio Junqueira), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho é resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.

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
