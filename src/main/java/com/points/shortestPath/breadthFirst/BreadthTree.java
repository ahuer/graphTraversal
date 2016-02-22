package com.points.shortestPath.breadthFirst;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.points.model.Edge;
import com.points.model.Graph;
import com.points.model.Vertex;

public class BreadthTree {
	private BreadthNode root;
	
	public BreadthTree(Graph graph, Vertex start) {
		root = populateRootNode(graph, start);
	}
	
	private BreadthNode populateRootNode(Graph graph, Vertex start) {
		if (graph == null || graph.getVertices() == null ) {
			return null;
		}
		
		List<Vertex> vertices = graph.getVertices();
		if (start == null || !vertices.contains(start) ) {
			return null;
		}
		
		BreadthNode rootNode = new BreadthNode(start, null, 0);
		Queue<BreadthNode> nodeQueue = new ConcurrentLinkedQueue<>();
		nodeQueue.add(rootNode);
		
		while (!nodeQueue.isEmpty() ) {
			BreadthNode currentNode = nodeQueue.poll();
			Vertex currentVertex = currentNode.getData();
			Set<Vertex> ancestors = getAncestors(currentNode);
			
			int childCount = 0;
			for (Edge e : currentVertex.getEdges() ) {
				Vertex child = e.getVertex();
				if (ancestors.contains(child) ) {
					continue;
				}
				
				BreadthNode childNode = new BreadthNode(child, currentNode, currentNode.getPathTotal() + e.getWeight());
				nodeQueue.add(childNode);
				currentNode.setChild(childNode, childCount);
				childCount ++;								
			}
		}
		
		return rootNode;
	}
	
	private Set<Vertex> getAncestors(BreadthNode node) {
		Set<Vertex> ancestors = new HashSet<>();
		BreadthNode currentNode = node;
		
		while (currentNode.getParent() != null ) {
			BreadthNode parentNode = currentNode.getParent();
			ancestors.add(parentNode.getData());
			currentNode = parentNode;
		}		
		
		return ancestors;
	}
	
	public BreadthNode getRoot() {
		return root;
	}

}
