// Nós (Josué Silva, Giordano Trombetta, Fabio Junqueira), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho é resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.


package unisinos.maps;

public class Edge<E> implements Comparable{
	 
	private Vertex vertex1;
	private Vertex vertex2;
	private double weigth;
	private String dislocate;	
	
	public Edge(Vertex vertex1, Vertex vertex2, int weigth, String d) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weigth = weigth;		
		this.dislocate = d;
	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public void setVertex1(Vertex vertex1) {
		this.vertex1 = vertex1;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

	public void setVertex2(Vertex vertex2) {
		this.vertex2 = vertex2;
	}

	public double getWeigth() {
		return weigth;
	}

	public void setWeigth(double d) {
		this.weigth = d;
	}
	
	public String getDislocate(){
		return dislocate;
	}
	
	public void setDislocate(String d){
		this.dislocate = d;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
		result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
		return result;
	}

	@Override
	
	
	public String toString(){ 
		String edge = vertex1.getPoint().getId()+","
					+" tipo="+vertex1.getPoint().getType()+", "
					+vertex2.getPoint().getId()+","
					+" tipo="+vertex2.getPoint().getType()+", "
					+" deslocamento= "+dislocate+","
					+" distance= "+weigth;
		
		
		return edge;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (vertex1 == null) {
			if (other.vertex1 != null)
				return false;
		} else if (!vertex1.equals(other.vertex1))
			return false;
		if (vertex2 == null) {
			if (other.vertex2 != null)
				return false;
		} else if (!vertex2.equals(other.vertex2))
			return false;
		return true;
	}
	
	public int compareTo(Object o) {
        if (this.weigth == ((Edge)o).getWeigth())
            return 0;
        else if (this.weigth > ((Edge)o).getWeigth())
            return 1;
        else
            return -1;
    }
}
