package com.points.shortestPath
import static org.junit.Assert.*

import org.junit.Test

import com.points.model.Edge
import com.points.model.Graph
import com.points.model.Vertex

public class SalesmanTest {

	@Test
	public void testSmallGraph() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def edgeToA = new Edge(vertA, 7)
		def edgeToB = new Edge(vertB, 7)
		vertA.setEdges([edgeToB])
		vertB.setEdges([edgeToA])
		
		def graph = new Graph([vertA, vertB])
		def salesman = new Salesman(graph)
		def result = salesman.shortestPath(vertA)
		assertEquals(14, result)
		assertEquals([vertA, vertB, vertA], salesman.getShortestPathList())
	}
	
	@Test
	public void testLargeGraph() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def vertC = new Vertex("C")
		def vertD = new Vertex("D")
		def edgeAToB = new Edge(vertB, 8)
		def edgeAToC = new Edge(vertC, 9)
		def edgeAToD = new Edge(vertD, 7)
		def edgeBToA = new Edge(vertA, 8)
		def edgeBToC = new Edge(vertC, 5)
		def edgeBToD = new Edge(vertD, 4)
		def edgeCToA = new Edge(vertA, 9)
		def edgeCToB = new Edge(vertB, 5)
		def edgeCToD = new Edge(vertD, 6)
		def edgeDToA = new Edge(vertA, 7)
		def edgeDToB = new Edge(vertB, 4)
		def edgeDToC = new Edge(vertC, 6)
		
		vertA.setEdges([edgeAToB, edgeAToC, edgeAToD])
		vertB.setEdges([edgeBToA, edgeBToC, edgeBToD])
		vertC.setEdges([edgeCToA, edgeCToB, edgeCToD])
		vertD.setEdges([edgeDToA, edgeDToB, edgeDToC])
		
		def graph = new Graph([vertA, vertB, vertC, vertD])
		def salesman = new Salesman(graph)
		def result = salesman.shortestPath(vertA)
		assertEquals(25, result)
		assertEquals([vertA, vertD, vertB, vertC, vertA], salesman.getShortestPathList())
	}
}