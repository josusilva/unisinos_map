package unisinos.maps;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class Main {

	public static void main(String[] args) throws IOException {
		
		try(Reader reader = new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("GraphElements.json"))){
			ObjectMapper mapper = new ObjectMapper();			
			JsonNode node = mapper.readValue(reader, JsonNode.class);
			
			JsonNode features = node.get("features");
			List<Object> geometry = new ArrayList<Object>();
			Graph graph = new Graph();
			for (JsonNode jsonNode : features) {
				JsonNode geo = jsonNode.get("geometry");				
				if(geo.get("type").toString().contains("Point")){
					JsonNode array = geo.get("coordinates");	
					JsonNode prop = jsonNode.get("properties");
					
					Point point = new Point();
					point.setLat(array.get(0).asDouble());
					point.setLng(array.get(1).asDouble());
					point.setType(prop.get("tipo").asText());
					point.setId(prop.get("id").asInt());	
					graph.insertVertex(point);
				}
				else{
					JsonNode prop = jsonNode.get("properties");
					JsonNode array = geo.get("coordinates");
					Vertex v1 = graph.findVertex(array.get(0).get(0).asDouble(), array.get(0).get(1).asDouble());
					Vertex v2 = graph.findVertex(array.get(1).get(0).asDouble(), array.get(1).get(1).asDouble());
					Edge edge = new Edge(v1, v2, prop.get("distance").asInt(), prop.get("deslocamento").asText());
					graph.insertEdge(v1, v2, prop.get("deslocamento").asText());
				}
			}
			System.out.println("Camigol");
		} 		
	}	
}
