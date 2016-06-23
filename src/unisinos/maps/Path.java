package unisinos.maps;
import java.util.ArrayList;
import java.util.List;

public class Path {
	List<Edge> edges = new ArrayList<Edge>() ;
	int pathWeight;
	
	public void printPath(){
		for(Edge e : edges){
			System.out.println(edges.toString());
		}
	}
	
}
