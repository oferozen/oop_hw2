package homework2;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}	
	
	@Test
	public void one() {
		assertEquals("first white-box test", 0,0);
	}


	@Test
	public void pfWbAssertNull() {

		WeightedNode n1 = new WeightedNode("n1", 5);
		WeightedNode n2 = new WeightedNode("n2", 5);
		
		Graph<WeightedNode> graph = new Graph<WeightedNode>();
		
		graph.addNode(n1);
		WeightedNodePath p1 = new WeightedNodePath(n1);
		Set<WeightedNodePath> starts = new TreeSet<WeightedNodePath>();
		starts.add(p1);
		
		graph.addNode(n2);
		WeightedNodePath p2 = new WeightedNodePath(n1);
		Set<WeightedNodePath> goals = new TreeSet<WeightedNodePath>();
		goals.add(p2);
	
		var pf = new PathFinder<WeightedNode, WeightedNodePath>();
		
		assertNull(pf.FindShortestPath(null, starts, goals));
		assertNull(pf.FindShortestPath(graph, null, goals));
		assertNull(pf.FindShortestPath(graph, starts, null));
		assertNull(pf.FindShortestPath(null, null, goals));
		assertNull(pf.FindShortestPath(graph, null, null));
		assertNull(pf.FindShortestPath(null, starts, null));		
	}
	
	@Test
	public void pfWbAssertSize0() {

		WeightedNode n1 = new WeightedNode("n1", 5);
		WeightedNode n2 = new WeightedNode("n2", 5);
		
		Graph<WeightedNode> graph = new Graph<WeightedNode>();
		
		graph.addNode(n1);
		WeightedNodePath p1 = new WeightedNodePath(n1);
		Set<WeightedNodePath> starts = new TreeSet<WeightedNodePath>();
		starts.add(p1);
		
		graph.addNode(n2);
		WeightedNodePath p2 = new WeightedNodePath(n1);
		Set<WeightedNodePath> goals = new TreeSet<WeightedNodePath>();
		goals.add(p2);
		
		Set<WeightedNodePath> emptySet = new TreeSet<WeightedNodePath>();
		
		graph.addEdge(n1, n2);
	
		var pf = new PathFinder<WeightedNode, WeightedNodePath>();
						
		assertNotNull(pf.FindShortestPath(graph, starts, goals));
		assertNull(pf.FindShortestPath(graph, emptySet, goals));
		assertNull(pf.FindShortestPath(graph, starts, emptySet));
	}
	
	@Test
	public void pfWbAssertNodesInGraphs() {

		WeightedNode n1 = new WeightedNode("n1", 5);
		WeightedNode n2 = new WeightedNode("n2", 5);
		WeightedNode n3 = new WeightedNode("n3", 5);
		
		Graph<WeightedNode> graph = new Graph<WeightedNode>();
		
		graph.addNode(n1);
		WeightedNodePath p1 = new WeightedNodePath(n1);
		Set<WeightedNodePath> starts = new TreeSet<WeightedNodePath>();
		starts.add(p1);
		
		graph.addNode(n2);
		WeightedNodePath p2 = new WeightedNodePath(n1);
		Set<WeightedNodePath> goals = new TreeSet<WeightedNodePath>();
		goals.add(p2);
		
		Set<WeightedNodePath> set = new TreeSet<WeightedNodePath>();
		WeightedNodePath p3 = new WeightedNodePath(n3);
		set.add(p3);
		
		graph.addEdge(n1, n2);
	
		var pf = new PathFinder<WeightedNode, WeightedNodePath>();
						
		assertNotNull(pf.FindShortestPath(graph, starts, goals));
		assertNull(pf.FindShortestPath(graph, set, goals));
		assertNull(pf.FindShortestPath(graph, starts, set));
	}
	
}
