package unisinos.maps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class WriteGeoJson {

	private static String filePath = "";
	
	public static void writeGraph(LinkedList<Vertex> vertex, LinkedList<Edge> edge, String fileName){
		try {
			File file = new File(filePath + fileName + ".json");
			// 
			if (!file.exists()) {
				file.createNewFile();
			}
			String text ="";
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("{ \"type\": \"FeatureCollection\","
					+ "\n\"features\":"
					+ "\n[\n");
			
			for (int i=0; i < vertex.size(); i++){
				writePoint(bw, vertex.get(i).getPoint());
			}
			
			for (int i=0; i < edge.size()-1; i++){
				writeLineString(bw, edge.get(i));
			}
			
			int size = edge.size()-1;
			writeLastLineString(bw, edge.get(size));
			bw.write("]\n}");
			bw.close();
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void writePoint(LinkedList<Vertex> vertex, String fileName){
		try {
			File file = new File(filePath + fileName + ".json");
			// 
			if (!file.exists()) {
				file.createNewFile();
			}
			String text ="";
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("{ \"type\": \"FeatureCollection\","
					+ "\n\"features\":"
					+ "\n[\n");
			
			for (int i=0; i < vertex.size()-1; i++){
				writePoint(bw, vertex.get(i).getPoint());
			}
			int size = vertex.size()-1;
			writeLastPoint(bw, vertex.get(size).getPoint());
			bw.write("]\n}");
			bw.close();
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private static void writeLineString(BufferedWriter bw, Edge e){
		try{
			bw.write("{ \n");
			bw.write("\"type\": \"Feature\",\n");
			bw.write("\"geometry\": { \"type\": \"LineString\", \"coordinates\": [["
					+ e.getVertex1().getPoint().getLng()
					+","+e.getVertex1().getPoint().getLat()
					+"], ["
					+ e.getVertex2().getPoint().getLng()
					+","+e.getVertex2().getPoint().getLat() 
					+ "]]},\n");
			bw.write("\"properties\": { \"deslocamento\": "+"\""+e.getDislocate()+"\""
					+", \"distance\": "+"\""+e.getWeigth()+"\""+"}\n");
			bw.write("},\n");

		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	
	public static void writeLineString(LinkedList<Edge> edge, String fileName){
		try {
			File file = new File(filePath + fileName + ".json");
			// 
			if (!file.exists()) {
				file.createNewFile();
			}
			String text ="";
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("{ \"type\": \"FeatureCollection\","
					+ "\n\"features\":"
					+ "\n[\n");
			
			for (int i=0; i < edge.size()-1; i++){
				writeLineString(bw, edge.get(i));
			}
			int size = edge.size()-1;
			writeLastLineString(bw, edge.get(size));
			bw.write("]\n}");
			bw.close();
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private static void writeLastLineString(BufferedWriter bw, Edge e){
		try{
			bw.write("{ \n");
			bw.write("\"type\": \"Feature\",\n");
			bw.write("\"geometry\": { \"type\": \"LineString\", \"coordinates\": [["
					+ e.getVertex1().getPoint().getLng()
					+","+e.getVertex1().getPoint().getLat()
					+"], ["
					+ e.getVertex2().getPoint().getLng()
					+","+e.getVertex2().getPoint().getLat() 
					+ "]]},\n");
			bw.write("\"properties\": { \"deslocamento\": "+"\""+e.getDislocate()+"\""
					+", \"distance\": "+"\""+e.getWeigth()+"\""+"}\n");
			bw.write("}\n");

		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	
	private static void writePoint(BufferedWriter bw, Point p){
		try{
			bw.write("{ \n");
			bw.write("\"type\": \"Feature\",\n");
			bw.write("\"geometry\": { \"type\": \"Point\", \"coordinates\": ["+ p.getLng()
					+","+p.getLat()+"]},\n");
			bw.write("\"properties\": { \"tipo\": "+"\""+p.getType()+"\""
					+", \"id\": "+"\""+p.getId()+"\""+"}\n");
			bw.write("},\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeLastPoint(BufferedWriter bw, Point p){
		try{
			bw.write("{ \n");
			bw.write("\"type\": \"Feature\",\n");
			bw.write("\"geometry\": { \"type\": \"Point\", \"coordinates\": ["+ p.getLng()
					+","+p.getLat()+"]},\n");
			bw.write("\"properties\": { \"tipo\": "+"\""+p.getType()+"\""
					+", \"id\": "+"\""+p.getId()+"\""+"}\n");
			bw.write("}\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
