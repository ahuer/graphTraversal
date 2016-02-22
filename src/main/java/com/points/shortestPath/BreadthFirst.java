package com.points.shortestPath;

import java.util.ArrayList;
import java.util.List;

import com.points.model.Graph;
import com.points.model.Vertex;
import com.points.shortestPath.breadthFirst.BreadthNode;
import com.points.shortestPath.breadthFirst.BreadthTree;

public class BreadthFirst {
	private Graph graph;
	private List<Vertex> vertices;
	private BreadthNode root;
	
	private BreadthNode shortestPathEndNode;
	private int shortestPathTotal;
	private Vertex endPoint;
	
	public BreadthFirst(Graph graph) throws IllegalArgumentException {
		if (graph == null || graph.getVertices() == null ) {
			throw new IllegalArgumentException("Invalid graph provided");
		}
		
		this.graph = graph;
		this.vertices = graph.getVertices();	
	}
	
	public int getShortestPathTotal() {
		return shortestPathTotal;
	}
	
	public BreadthNode getShortestPathEndNode() {
		return shortestPathEndNode;
	}
	
	public List<Vertex> getShortestPathList() {
		if (shortestPathEndNode == null ) {
			return null;
		}
		
		List<Vertex> reversePath = new ArrayList<>();
		BreadthNode currentNode = shortestPathEndNode;
		reversePath.add(currentNode.getData());
		
		while (currentNode.getParent() != null ) {
			BreadthNode parentNode = currentNode.getParent();
			reversePath.add(parentNode.getData());
			currentNode = parentNode;
		}	
		
		List<Vertex> path = new ArrayList<>(reversePath.size());
		for (int i = reversePath.size() - 1; i >= 0; i-- ) {
			path.add(reversePath.get(i));
		}
		
		return path;
	}
		
	public int shortestPath(Vertex start, Vertex end) {
		if (!vertices.contains(start) || !vertices.contains(end) ) {
			return 0;
		}
		
		BreadthTree breadthTree = new BreadthTree(graph, start);
		root = breadthTree.getRoot();
		
		if (root == null ) {
			return 0;
		}
		
		shortestPathEndNode = null;
		shortestPathTotal = Integer.MAX_VALUE;
		endPoint = end;
		
		bfs(root);
		
		if (shortestPathTotal == Integer.MAX_VALUE ) {
			return 0;
		}
		return shortestPathTotal;
	}
	
	private void bfs(BreadthNode currentNode) {
		Vertex currentVertex = currentNode.getData();
		
		if (currentVertex == endPoint ) {
			if (currentNode.getPathTotal() < shortestPathTotal ) {
				shortestPathTotal = currentNode.getPathTotal();
				shortestPathEndNode = currentNode;
			}
			return;
		}
		
		for (BreadthNode child : currentNode.getChildrenList() ) {
			bfs(child);
		}
	}

}
