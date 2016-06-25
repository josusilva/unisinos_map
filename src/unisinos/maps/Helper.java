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

public class Helper {
	
	public static void allAdm (Graph g){
		LinkedList<Vertex> admVertex = new LinkedList();
		LinkedList<Vertex> vertexOut = new LinkedList();
		LinkedList<Edge> edges = new LinkedList();
		LinkedList<Vertex> vertexs = new LinkedList();
		Vertex current = null;
		String type = "adm";
		for (Vertex vertex : (LinkedList<Vertex>) g.getVertex()) {				
			if(type.equals(vertex.getPoint().getType())){
				admVertex.add(vertex);
			}
		}
		LinkedList<Vertex> lessPath = new LinkedList();
		double lessWeight = 0;
		Vertex lessVertex = null;
		Dijkstra dijkstra = new Dijkstra(g);
		current = admVertex.getFirst();
		vertexOut.add(admVertex.removeFirst());
		while (!admVertex.isEmpty()){
			dijkstra.execute(current);
			for (int i = 0; i < admVertex.size(); i++){
				if (!current.equals(admVertex.get(i))){
					LinkedList<Vertex> path = dijkstra.getPath(admVertex.get(i));
					Path temp = new Path(path, g);
					if (i == 0){
						lessPath = path;
						lessWeight = temp.getPathWeight();
						lessVertex = admVertex.get(i);
					} else if(lessWeight > temp.getPathWeight());
						lessPath = path;
						lessWeight = temp.getPathWeight();
						lessVertex = admVertex.get(i);
					}
				}
			admVertex.remove(current);
			for (int i = 0; i < lessPath.size(); i++){
				vertexs.add(lessPath.get(i));
			}
			Path temp = new Path(lessPath, g);
			for (int i = 0; i < temp.getEdges().size(); i++){
				edges.add(temp.getEdges().get(i));
			}
			current = lessVertex;
		}
		for (int i = 0; i < edges.size(); i++){
			System.out.println(edges.get(i).toString());
		}
		WriteGeoJson.writeGraph(vertexs, edges, "allAdm");
		
	}
	
	public static void nextPoints(Vertex v, Graph g, int r, String type){
		LinkedList<Vertex> typeVertex = new LinkedList();
		LinkedList<Vertex> nextPoint = new LinkedList();		
		
		for (Vertex vertex : (LinkedList<Vertex>) g.getVertex()) {				
			if(type.equals(vertex.getPoint().getType())){
				typeVertex.add(vertex);
			}
		}
		nextPoint.add(v);
		Dijkstra dijkstra = new Dijkstra(g);
		dijkstra.execute(v);
		
		for(Vertex vertex : typeVertex){
			LinkedList<Vertex> path = dijkstra.getPath(vertex);	
			if (path == null){
				System.out.println("Rota não gerada");
			}else{
				Path point = new Path(path, g);
				if (point.getPathWeight() < r){
					nextPoint.add(vertex);
					System.out.println(vertex.getPoint().toString()+" Distância: "+point.getPathWeight());
				}
			}
		}		
		
		if (nextPoint.size() < 2){
			System.out.println("Não existem pontos próximos");
		 } else{
			WriteGeoJson.writePoint(nextPoint, "NextPoints");
			
		 }
		 
	}
}


