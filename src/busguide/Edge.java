/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busguide;

/**
 *
 * @author elvis
 */
public class Edge {

	private final Vertex source;
	private final Vertex destination;
	private final Double weight;

	public Edge(Vertex sourceBs, Vertex destinationBs, Double distance) {
		source = sourceBs;
		destination = destinationBs;
		weight = distance;
	}

	public Vertex getSource() {
		return source;
	}

	public Vertex getDestination() {
		return destination;
	}

	public Double getWeight() {
		return weight;
	}
}
