package com.points.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	private List<Vertex> vertices;
	
	public Graph(List<Vertex> vertices ) {
		this.vertices = vertices;
	}
	
	public List<Vertex> getVertices() {
		return vertices;
	}
	
}
