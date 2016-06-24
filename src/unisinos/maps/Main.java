package unisinos.maps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class Main {

	public static void main(String[] args) throws IOException {
		DecimalFormat df2 = new DecimalFormat(".##");
		Graph graph = new Graph();
		try(Reader reader = new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("GraphElements.json"))){
			ObjectMapper mapper = new ObjectMapper();			
			JsonNode node = mapper.readValue(reader, JsonNode.class);
			
			JsonNode features = node.get("features");
			List<Object> geometry = new ArrayList<Object>();
			for (JsonNode jsonNode : features) {
				JsonNode geo = jsonNode.get("geometry");				
				if(geo.get("type").toString().contains("Point")){
					JsonNode array = geo.get("coordinates");	
					JsonNode prop = jsonNode.get("properties");
					
					Point point = new Point();
					point.setLat(array.get(1).asDouble());
					point.setLng(array.get(0).asDouble());
					point.setType(prop.get("tipo").asText());
					point.setId(prop.get("id").asInt());	
					graph.insertVertex(point);
				}
				else{
					JsonNode prop = jsonNode.get("properties");
					JsonNode array = geo.get("coordinates");					
					Vertex v1 = graph.findVertex(array.get(0).get(1).asDouble(), array.get(0).get(0).asDouble()); 
					Vertex v2 = graph.findVertex(array.get(1).get(1).asDouble(), array.get(1).get(0).asDouble());
					//Edge edge = new Edge(v1, v2, prop.get("distance").asInt(), prop.get("deslocamento").asText());
					graph.insertEdge(v1, v2, prop.get("deslocamento").asText());
				}
			}
			
		}
		
		for (int i = 0; i < graph.getEdge().size(); i++){
			double lat1 = ((Edge) graph.getEdge().get(i)).getVertex1().getPoint().getLat();
			double lon1 = ((Edge) graph.getEdge().get(i)).getVertex1().getPoint().getLng();
			double lat2 = ((Edge) graph.getEdge().get(i)).getVertex2().getPoint().getLat();
			double lon2 = ((Edge) graph.getEdge().get(i)).getVertex2().getPoint().getLng();
			double dist = DistanceCalculator.distance(lat1, lon1, lat2, lon2, "K");
			((Edge) graph.getEdge().get(i)).setWeigth(dist*1000);
		}
		
		int resp = -1;
		while (resp != 0){
			try{
				resp = InputDialog.readInt("Operação a ser realizada:\n"
										+ "[1]-Imprimir Grafo\n"
										+ "[2]-Listros de Tinta Para Percorrer o Campus\n"
										+ "[3]-Deslocamento Ponto a Ponto\n"
										+ "[4]-Buscar Locais Próximos\n"
										+ "[5]-Menor Distancia entre os Centros\n"
										+ "[0]-Sair");
				switch(resp){
					case 0:{
						break;
					}
					case 1:{
						for(int i = 0; i < graph.getEdge().size(); i++){
							System.out.println(graph.getEdge().get(i).toString());
						}
						WriteGeoJson.writeGraph(graph.getVertex(), graph.getEdge(), "Graph");
					break;
					}
					case 2:{
						Prim prim = new Prim(graph);
						prim.MSTPrim();
						System.out.println("Vai ser preciso um total de: "+(df2.format(prim.weight_route()/10))+" litros");
						WriteGeoJson.writeGraph(prim.getPrimVertex(), prim.getPrimEdges(), "AllPoints");
					break;
					}
					case 3:{
						int from = InputDialog.readInt("ID de origem:\n");
						int to = InputDialog.readInt("ID de destino:\n");
						Vertex v1 = graph.getVertexById(from);
						Vertex v2 = graph.getVertexById(to);
						Dijkstra dijkstra = new Dijkstra(graph);
						//Ponto inicial
						dijkstra.execute(v1);
						//Ponto onde deseja chegar
						LinkedList<Vertex> path = dijkstra.getPath(v2);
						
						if (path == null){
							System.out.println("Rota não gerada");
						}else{
							for (int i = 0; i < path.size(); i++ ){
								System.out.println(path.get(i).toString());
							}
							Path edges = new Path(path, graph);
							
							edges.printEdges();
							WriteGeoJson.writeGraph(path, edges.getEdges(), "PointToPoint");
						}
						
						break;
					}
					case 4:{
						int from = InputDialog.readInt("ID de origem:\n");
						int raio = InputDialog.readInt("Raio de Busca:\n");
						int resp4 = InputDialog.readInt("Tipo de Local Buscado:\n"
								+ "[1]-adm\n"
								+ "[2]-esporte\n"
								+ "[3]-auditorio\n"
								+ "[4]-comida\n"
								+ "[5]-banheiro\n"
								+ "[6]-onibus\n");
						Vertex v = graph.getVertexById(from);
						switch (resp4){
							case 1:{
								Helper.nextPoints(v, graph, raio, "adm");
								break;
							}
							case 2:{
								Helper.nextPoints(v, graph, raio, "esporte");
								break;
							}
							case 3:{
								Helper.nextPoints(v, graph, raio, "auditorio");
								break;
							}
							case 4:{
								Helper.nextPoints(v, graph, raio, "comida");
								break;
							}
							case 5:{
								Helper.nextPoints(v, graph, raio, "banheiro");
								break;
							}
							case 6:{
								Helper.nextPoints(v, graph, raio, "onibus");
								break;
							}
						} 	
					}
				}
			}catch (NullPointerException ex) {
            	JOptionPane.showMessageDialog(null, "Operação inválida", "Unisinos Maps", JOptionPane.INFORMATION_MESSAGE);
            }
		}
	}	
}
