package unisinos.maps;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
					Vertex v1 = graph.findVertex(array.get(1).get(1).asDouble(), array.get(1).get(0).asDouble());
					Vertex v2 = graph.findVertex(array.get(0).get(1).asDouble(), array.get(0).get(0).asDouble());
					Edge edge = new Edge(v1, v2, prop.get("distance").asInt(), prop.get("deslocamento").asText());
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
					break;
					}
					case 2:{
						Prim prim = new Prim(graph);
						prim.MSTPrim();
						System.out.println("Vai ser preciso um total de: "+(df2.format(prim.weight_route()/10))+" litros");
					break;
					}
					case 3:{
						 Dijkstra dijkstra = new Dijkstra(graph);
						 dijkstra.findShortestPath(1, 52, null);
						 dijkstra.optimalPath.printPath();						
					break;
					}
				}
			}catch (NullPointerException ex) {
            	JOptionPane.showMessageDialog(null, "Operação inválida", "Unisinos Maps", JOptionPane.INFORMATION_MESSAGE);
            }
		}
	}	
}
