/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busguide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author elvis
 *
 * Algorithm from
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html#algorithm
 */
public class DijkstraAlgorithm {

	private final Set<BusStops> nodes;
	private final Set<Edge> edges;
	private Set<BusStops> settledNodes;
	private Set<BusStops> unSettledNodes;
	private Map<BusStops, BusStops> predecessors;
	private Map<BusStops, Double> distance;

	public DijkstraAlgorithm(Graph graph) {
		this.nodes = new HashSet<>(graph.getVertexes());
		this.edges = new HashSet<>(graph.getEdges());
	}

	public void execute(BusStops source) {
		settledNodes = new HashSet<>();
		unSettledNodes = new HashSet<>();
		distance = new HashMap<>();
		predecessors = new HashMap<>();
		distance.put(source, 0.0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			BusStops node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(BusStops node) {
		List<BusStops> adjacentNodes = getNeighbors(node);
		for (BusStops target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
				+ getDistance(node, target)) {
				distance.put(target, getShortestDistance(node)
					+ getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}
	}

	private double getDistance(BusStops node, BusStops target) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
				&& edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Error");
	}

	private List<BusStops> getNeighbors(BusStops node) {
		List<BusStops> neighbors = new ArrayList<>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
				&& !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	private BusStops getMinimum(Set<BusStops> vertexes) {
		BusStops minimum = null;
		for (BusStops vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
				minimum = vertex;
			}
		}
		return minimum;
	}

	private boolean isSettled(BusStops vertex) {
		return settledNodes.contains(vertex);
	}

	private Double getShortestDistance(BusStops destination) {
		Double d = distance.get(destination);
		if (d == null) {
			return Double.MAX_VALUE;
		} else {
			return d;
		}
	}

	public LinkedList<BusStops> getPath(BusStops target) {
		LinkedList<BusStops> path = new LinkedList<BusStops>();
		BusStops step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}

}
