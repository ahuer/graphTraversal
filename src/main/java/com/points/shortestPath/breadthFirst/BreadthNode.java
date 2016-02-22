package com.points.shortestPath.breadthFirst;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.points.model.Vertex;
import com.points.tree.Node;

public class BreadthNode extends Node<Vertex> {
	private BreadthNode parent;
	private int pathTotal;
	
	public BreadthNode(Vertex data, BreadthNode parent, int pathTotal) {
		super(data);
		this.parent = parent;
		this.pathTotal = pathTotal;
	}
	
	public BreadthNode getParent() {
		return parent;
	}
	
	public int getPathTotal() {
		return pathTotal;
	}
	
	public List<BreadthNode> getChildrenList() {
		Map<Integer, Node<Vertex>> childMap = this.getChildren();
		List<BreadthNode> children = new ArrayList<>();
		
		for (Node<Vertex> node : childMap.values() ) {
			children.add((BreadthNode) node);
		}
		return children;
	}

}
