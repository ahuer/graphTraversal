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

public class Salesman {
	private List<Vertex> vertices;
	private List<Vertex> shortestPathList;
	private Map<Vertex, Vertex> predecessors;
	
	public Salesman (Graph graph) throws IllegalArgumentException {
		if (graph == null || graph.getVertices() == null ) {
			throw new IllegalArgumentException("Invalid graph provided");
		}
		
		this.vertices = graph.getVertices();
	}
	
	public List<Vertex> getVertices() {
		return vertices;
	}
	
	public int shortestPath(Vertex start) {
		if (!vertices.contains(start) ) {
			return -1;
		}
		
		Set<Vertex> unvisitedNeighbors = new HashSet<>();
		for (Vertex v : vertices ) {
			unvisitedNeighbors.add(v);
		}		
		unvisitedNeighbors.remove(start);
		
		int shortestPathTotal = 0;
		predecessors = new HashMap<>();
		Vertex currentVertex = start;
		
		while (!unvisitedNeighbors.isEmpty() ) {
			Vertex nearestNeighbor = findNearestNeighbor(currentVertex, unvisitedNeighbors);
			
			if (nearestNeighbor == null ) {
				return -1;
			}
			
			shortestPathTotal += nearestNeighbor.getEdgeWeight(currentVertex);
			predecessors.put(nearestNeighbor, currentVertex);
			unvisitedNeighbors.remove(nearestNeighbor);
			currentVertex = nearestNeighbor;
		}
		
		int returnWeight = currentVertex.getEdgeWeight(start);
		
		// final vertex doesn't connect back to start
		if (returnWeight < 0 ) {
			return -1;
		}
		
		updateShortestPathList(start, currentVertex);
		return shortestPathTotal + returnWeight;
	}
	
	private Vertex findNearestNeighbor(Vertex currentVertex, Set<Vertex> unvisitedNeighbors) {
		int nearestNeighborValue = Integer.MAX_VALUE;
		Vertex nearestNeighbor = null;
		
		for (Edge edge : currentVertex.getEdges() ) {
			Vertex neighbor = edge.getVertex();
			
			if (!unvisitedNeighbors.contains(neighbor) ) {
				continue;
			}
			
			if (edge.getWeight() < nearestNeighborValue ) {
				nearestNeighborValue = edge.getWeight();
				nearestNeighbor = neighbor;					
			}				
		}
		
		return nearestNeighbor;
	}
	
	public void updateShortestPathList(Vertex start, Vertex end) {
		shortestPathList = new ArrayList<>();
		List<Vertex> reversePath = new ArrayList<>();
		reversePath.add(start);
		
		Vertex currentVertex = end;
		while (currentVertex != null ) {
			reversePath.add(currentVertex);
			currentVertex = predecessors.get(currentVertex);
		}
		
		for (int i = reversePath.size() - 1; i >=0; i-- ) {
			shortestPathList.add(reversePath.get(i));
		}
	}
	
	public List<Vertex> getShortestPathList() {
		return shortestPathList;
	}

}
