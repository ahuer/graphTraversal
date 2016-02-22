package com.points.shortestPath.breadthFirst

import static org.junit.Assert.*

import org.junit.Test

import com.points.model.Edge
import com.points.model.Graph
import com.points.model.Vertex

class BreadthFirstTreeTest {
	
	@Test
	public void testSmallTree() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def edgeToA = new Edge(vertA, 7)
		def edgeToB = new Edge(vertB, 7)
		vertA.setEdges([edgeToB])
		vertB.setEdges([edgeToA])
		
		def graph = new Graph([vertA, vertB])
		def tree = new BreadthTree(graph, vertA)
		def root = tree.getRoot()
		assertEquals(vertA, root.getData())
		assertEquals(vertB, root.getChild(0).getData())
		assertEquals(root, root.getChild(0).getParent())
	}
	
	@Test
	public void testLargeTree() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def vertC = new Vertex("C")
		def vertD = new Vertex("D")
		def vertE = new Vertex("E")
		def edgeAToB = new Edge(vertB, 4)
		def edgeBToA = new Edge(vertA, 4)
		def edgeAToE = new Edge(vertE, 3)
		def edgeEToA = new Edge(vertA, 3)
		def edgeBToE = new Edge(vertE, 7)
		def edgeEToB = new Edge(vertB, 7)
		def edgeBToC = new Edge(vertC, 1)
		def edgeCToB = new Edge(vertB, 1)
		def edgeBToD = new Edge(vertD, 6)
		def edgeDToB = new Edge(vertB, 6)
		def edgeEToC = new Edge(vertC, 5)
		def edgeCToE = new Edge(vertE, 5)
		
		vertA.setEdges([edgeAToB, edgeAToE])
		vertB.setEdges([edgeBToA, edgeBToE, edgeBToC, edgeBToD])
		vertC.setEdges([edgeCToB, edgeCToE])
		vertD.setEdges([edgeDToB])
		vertE.setEdges([edgeEToA, edgeEToB, edgeEToC])
		
		def graph = new Graph([vertA, vertB, vertC, vertD, vertE])
		def tree = new BreadthTree(graph, vertA)
		def root = tree.getRoot()
		assertEquals(vertA, root.getData())
		
		// verify tree structure
		def child0 = root.getChild(0)
		def child1 = root.getChild(1)
		assertEquals(vertB, child0.getData())
		assertEquals(vertE, child1.getData())
		
		def child00 = child0.getChild(0)
		def child01 = child0.getChild(1)
		def child02 = child0.getChild(2)
		def child10 = child1.getChild(0)
		def child11 = child1.getChild(1)
		assertEquals(vertE, child00.getData())
		assertEquals(vertC, child01.getData())
		assertEquals(vertD, child02.getData())
		assertEquals(vertB, child10.getData())
		assertEquals(vertC, child11.getData())
		
		def child000 = child00.getChild(0)
		def child010 = child01.getChild(0)
		def child020 = child02.getChild(0)
		def child100 = child10.getChild(0)
		def child101 = child10.getChild(1)
		def child110 = child11.getChild(0)
		def child1100 = child110.getChild(0)
		assertEquals(vertC, child000.getData())
		assertEquals(vertE, child010.getData())
		assertEquals(null, child020)
		assertEquals(vertC, child100.getData())
		assertEquals(vertD, child101.getData())
		assertEquals(vertB, child110.getData())
		assertEquals(vertD, child1100.getData())
		
		assertEquals(null, child000.getChild(0))
		assertEquals(null, child010.getChild(0))
		assertEquals(null, child100.getChild(0))
		assertEquals(null, child101.getChild(0))
		assertEquals(null, child1100.getChild(0))
	}

}
