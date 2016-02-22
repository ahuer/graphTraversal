package com.points.shortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.points.model.Edge;
import com.points.model.Graph;
import com.points.model.Vertex;

public class Dijkstra {
	private List<Vertex> vertices;
	private Map<Vertex, Vertex> predecessors = new HashMap<>();
	private List<Vertex> path;
	
	public Dijkstra(Graph graph) throws IllegalArgumentException {
		if (graph == null || graph.getVertices() == null ) {
			throw new IllegalArgumentException("Invalid graph provided");
		}
		
		this.vertices = graph.getVertices();
	}
	
	public List<Vertex> getShortestPathList() {
		return path;
	}
	
	private void calculatShortestPathList(Vertex start, Vertex end) {
		List<Vertex> reversePath = new ArrayList<>();
		Vertex currentVertex = end;
		reversePath.add(end);
		
		while (currentVertex != start ) {
			Vertex previous = predecessors.get(currentVertex); 
			reversePath.add(previous);
			currentVertex = previous;
		}
		
		path = new ArrayList<>();
		for (int i = reversePath.size() - 1; i >= 0; i-- ) {
			path.add(reversePath.get(i));
		}
	}
	
	public int shortestPath(Vertex start, Vertex end) {
		if (!vertices.contains(start) || !vertices.contains(end) ) {
			return 0;
		}
		
		Set<Vertex> verticesTraversed = new HashSet<>();		
		Map<Vertex, Integer> currentValues = new HashMap<>();
		for (Vertex v : vertices ) {
			currentValues.put(v, Integer.MAX_VALUE);
		}
		
		currentValues.put(start, 0);
		verticesTraversed.add(start);
		
		Vertex currentVertex = start;
		
		while(currentVertex != null) {
			int lowestWeight = Integer.MAX_VALUE;
			Vertex nextVertex = null;
			
			for (Edge edge : currentVertex.getEdges() ) {
				Vertex vertex = edge.getVertex();
				
				if (verticesTraversed.contains(vertex) ) {
					continue;
				}
				
				int weight = currentValues.get(currentVertex) + edge.getWeight();
				if (weight < currentValues.get(vertex) ) {
					currentValues.put(vertex, weight);
					predecessors.put(vertex, currentVertex);
				}
				
				if (currentValues.get(vertex) < lowestWeight ) {
					lowestWeight = currentValues.get(vertex);
					nextVertex = vertex;
				}
			}
			
			if (nextVertex != null ) {
				verticesTraversed.add(nextVertex);
			}
			currentVertex = nextVertex;			
		}		
		
		calculatShortestPathList(start, end);
		return currentValues.get(end);
	}
	
}
