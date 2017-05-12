/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busguide;

import static java.lang.reflect.Array.set;
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
	private final List<Vertex> nodes;
	private final List<Edge> edges;
	private Set<Vertex> settledNodes;
	private Set<Vertex> unSettledNodes;
        private Map<Vertex, Vertex> predecessors;
        private Map<Vertex, Double> distance;
	
	public DijkstraAlgorithm(Graph graph){
		this.nodes = new ArrayList<>(graph.getVertexes());
		this.edges = new ArrayList<>(graph.getEdges());
	}

	public void execute(Vertex source){
		settledNodes = new HashSet<>();
                unSettledNodes = new HashSet<>();
                distance = new HashMap<>();
                predecessors = new HashMap<>();
		distance.put(source, 0.0);
		unSettledNodes.add(source);
		while(unSettledNodes.size() > 0){
			Vertex node = getMinimum(unSettledNodes);
                        settledNodes.add(node);
                        unSettledNodes.remove(node);
                        findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Vertex node){
		List<Vertex> adjacentNodes = getNeighbors(node);
                for (Vertex target : adjacentNodes) {
                        if (getShortestDistance(target) > getShortestDistance(node)
                                        + getDistance(node, target)) {
                                distance.put(target, getShortestDistance(node)
                                                + getDistance(node, target));
                                predecessors.put(target, node);
                                unSettledNodes.add(target);
                        }
                }	
	}

	private double getDistance(Vertex node, Vertex target) {
                for (Edge edge : edges) {
                        if (edge.getSource().equals(node)
                                        && edge.getDestination().equals(target)) {
                                return edge.getWeight();
                        }
                }
                throw new RuntimeException("Error");
        }

	private List<Vertex> getNeighbors(Vertex node) {
                List<Vertex> neighbors = new ArrayList<>();
                for (Edge edge : edges) {
                        if (edge.getSource().equals(node)
                                        && !isSettled(edge.getDestination())) {
                                neighbors.add(edge.getDestination());
                        }
                }
                return neighbors;
        }

	private Vertex getMinimum(Set<Vertex> vertexes) {
                Vertex minimum = null;
                for (Vertex vertex : vertexes) {
                        if (minimum == null) {
                                minimum = vertex;
                        } else {
                                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                                        minimum = vertex;
                                }
                        }
                }
                return minimum;
        }

	private boolean isSettled(Vertex vertex) {
                return settledNodes.contains(vertex);
        }

        private Double getShortestDistance(Vertex destination) {
                Double d = distance.get(destination);
                if (d == null) {
                        return Double.MAX_VALUE;
                } else {
                        return d;
                }
        }

	public LinkedList<Vertex> getPath(Vertex target) {
                LinkedList<Vertex> path = new LinkedList<Vertex>();
                Vertex step = target;
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