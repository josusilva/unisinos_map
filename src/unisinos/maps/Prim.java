package unisinos.maps;

import java.util.LinkedList;
import java.util.LinkedList;

public class Prim<E> {
	
	private Graph<E> prim;
	private LinkedList<Vertex> vertex;
	private LinkedList<Edge> prim_edges;
	private LinkedList<Vertex> prim_vertex;
	
	public Prim(Graph graph){
		prim = graph;
		vertex = new LinkedList<Vertex>();
		prim_edges = new LinkedList<Edge>();
		prim_vertex = new LinkedList<Vertex>();
	}
	
	public LinkedList getPrimEdges(){
		return prim_edges;
	}
	
	public int weight_route(){
		int weight = 0;
		for (int i = 0; i < prim_edges.size(); i++){
			weight = weight + prim_edges.get(i).getWeigth();
		}
		return weight;
	}
	
	public void MSTPrim(){
		vertex = prim.getVertex();
		System.out.println("Tamanho lista vertex: "+vertex.size());
		Vertex current_vertex = vertex.removeFirst();
		prim_vertex.add(current_vertex);
		LinkedList<Edge> current_vertex_edges = prim.findEdges(current_vertex);
		Edge less = current_vertex_edges.get(0);
		Vertex new_prim_vertex = current_vertex;
		if (current_vertex.equals(less.getVertex1())){
			new_prim_vertex = less.getVertex2();
		} else{
			new_prim_vertex = less.getVertex1();
		}
		for (int j = 1; j < current_vertex_edges.size(); j++ ){
			if (less.getWeigth()>current_vertex_edges.get(j).getWeigth()){
				less = current_vertex_edges.get(j);
				System.out.println(current_vertex.toString());
				Vertex new_prim;
				if (current_vertex.equals(less.getVertex1())){
					new_prim_vertex = less.getVertex2();
				} else{
					new_prim_vertex = less.getVertex1();
					
				}
				
			}
		}
		prim_edges.add(less);
		prim_vertex.add(new_prim_vertex);
		vertex.remove(new_prim_vertex);
		int j = 0;
		while(!vertex.isEmpty()){
			System.out.println("Laço: "+j);
			less = null; 
			for(int i = 0; i < prim_vertex.size(); i++){
				current_vertex = prim_vertex.get(i);
				current_vertex_edges = prim.findEdges(current_vertex);
				for (int n = 0; n < current_vertex_edges.size(); n++){
					if (current_vertex_edges.get(n).getVertex1().equals(current_vertex)){
						if (prim_vertex.contains(current_vertex_edges.get(n).getVertex2())){
						} else{
							if (less == null){
								less = current_vertex_edges.get(n);
								new_prim_vertex = current_vertex_edges.get(n).getVertex2();
							} else{
								if (current_vertex_edges.get(n).getWeigth() < less.getWeigth()){
									less = current_vertex_edges.get(n);
									new_prim_vertex = current_vertex_edges.get(n).getVertex2();
								}
							}
						}
					} else {
						if (prim_vertex.contains(current_vertex_edges.get(n).getVertex1())){
	
						}else{
							if (less == null){
								less = current_vertex_edges.get(n);
								new_prim_vertex = current_vertex_edges.get(n).getVertex1();
							}else{
								if (current_vertex_edges.get(n).getWeigth() < less.getWeigth()){
									less = current_vertex_edges.get(n);
									new_prim_vertex = current_vertex_edges.get(n).getVertex1();									
								}
							}
						}
					}
				}
			}
			vertex.remove(new_prim_vertex);
			prim_edges.add(less);
			prim_vertex.add(new_prim_vertex);
			j++;
			System.out.println("Tamanho lista vertex: "+vertex.size());
		}
	}
}
		
	
	
	


