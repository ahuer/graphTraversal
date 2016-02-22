package com.points.model;

import java.util.List;

public class Vertex implements Comparable {
	private String name;
	private List<Edge> edges;
	
	public Vertex(String name) {
		this.name = name;
	}
		
	public Vertex(String name, List<Edge> edges) {
		this.name = name;
		this.edges = edges;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	public void setEdges(List<Edge> edgeList) {
		edges = edgeList;
	}
	
	public int getEdgeWeight(Vertex v) {
		for (Edge e : edges ) {
			if (e.getVertex() == v ) {
				return e.getWeight();
			}
		}
		return -1;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

}
