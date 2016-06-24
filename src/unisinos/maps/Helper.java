package unisinos.maps;

import java.util.LinkedList;

public class Helper {
	
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
				}
			}
		}		
		
		if (nextPoint.size() < 2){
			System.out.println("Não existem pontos próximos");
		 } else{
			 System.out.println("Pontos Próximos: ");
			 for (Vertex vertex : nextPoint){
				 System.out.println(vertex.getPoint().toString());
			 }
			WriteGeoJson.writePoint(nextPoint, "NextPoints");
		 }
		 
	}
}


