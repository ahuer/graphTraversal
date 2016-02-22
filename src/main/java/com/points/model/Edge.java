package com.points.model;

public class Edge {
	private Vertex vertex;
	private int weight;
	
	public Edge(Vertex vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public Vertex getVertex() {
		return vertex;
	}
}
