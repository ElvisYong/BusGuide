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

	private final BusStops source;
	private final BusStops destination;
	private final Double weight;

	public Edge(BusStops sourceBs, BusStops destinationBs, Double distance) {
		source = sourceBs;
		destination = destinationBs;
		weight = distance;
	}

	public BusStops getSource() {
		return source;
	}

	public BusStops getDestination() {
		return destination;
	}

	public Double getWeight() {
		return weight;
	}
}
