package unisinos.maps;

import java.util.LinkedList;

public class Helper {
	
	public static void nextPoints(Vertex v, Graph g, int r, String type){
		LinkedList<Vertex> typeVertex = new LinkedList();
		LinkedList<Vertex> nextPoint = new LinkedList();
		for (int i = 0; i < g.getVertex().size(); i++){
			if (type.equals(((Vertex) g.getVertex().get(i)).getPoint().getType())){
				typeVertex.add((Vertex) g.getVertex().get(i));
			}
		}
		nextPoint.add(v);
		Dijkstra dijkstra = new Dijkstra(g);
		dijkstra.execute(v);
		
		for (int i = 0; i < typeVertex.size(); i++){
			LinkedList<Vertex> path = dijkstra.getPath(typeVertex.get(i));	
			if (path == null){
				System.out.println("Rota não gerada");
			}else{
				Path point = new Path(path, g);
				if (point.getPathWeight() < r){
					nextPoint.add(typeVertex.get(i));
				}
			}
		}
		if (nextPoint.size() < 2){
			System.out.println("Não existem pontos próximos");
		 } else{
			 System.out.println("Pontos Próximos: ");
			 for (int i = 1; i < nextPoint.size(); i++){
				 System.out.println(nextPoint.get(i).getPoint().toString());
			 }
			WriteGeoJson.writePoint(nextPoint, "NextPoints");
		 }
		 
	}
}


