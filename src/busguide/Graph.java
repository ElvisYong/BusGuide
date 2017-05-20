/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busguide;

import java.util.List;
import java.util.Set;

/**
 *
 * @author elvis
 */
public class Graph {
	private final Set<BusStops> vertices;
	private final Set<Edge> edges;

	public Graph(Set<BusStops> vertices, Set<Edge> edges){
		this.vertices = vertices;
		this.edges = edges;
	}

	public Set<BusStops> getVertexes() {
		return vertices;
	}

	public Set<Edge> getEdges() {
		return edges;
	}
	
}
