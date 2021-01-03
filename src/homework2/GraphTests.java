package homework2;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import java.io.*;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	
	// black-box test are inherited from super
			public GraphTests(java.nio.file.Path testFile) {
				super(testFile);
			}	
	
	
	 private void captureOut() {
		 System.setOut( new PrintStream( outContent ) );
	  }
	 
	 private String getOut() {
		 System.setOut( new PrintStream( new FileOutputStream( FileDescriptor.out ) ) );
	     return outContent.toString().replaceAll( "\r", "" );
	 }
		
	 
	 
	 	@Test
		public void testcontains() {
			String theOutput = null;
			Graph<WeightedNode> graph = new Graph<>();
			
			// we test looking for a null node 
			// first if: true  |  second if : false
			captureOut();
			graph.contains(null);
			theOutput = getOut();
			assertEquals("contain null",theOutput,"ERROR: Graph: Node null in : contains: node\n");
		}
	 
	 	
	 	@Test
		public void testAddNodeSafetyLayer() {
			String theOutput = null;
			Graph<WeightedNode> graph = new Graph<>();
			WeightedNode node1 = new WeightedNode("node1", 10);
			
			// we test adding a null node 
			// first if: true  |  second if : false
			captureOut();
			assert(graph.addNode(null) == false);
			theOutput = getOut();
			assertEquals("adding null",theOutput,"ERROR: Graph: Node null in : addNode: node\n");
			
			// we test adding a non existing non-null node
			// first if: false  |  second if : false (node supposed to be added)
			assert(graph.addNode(node1) == true);
			assert(graph.contains(node1));
			theOutput = getOut();
			assertEquals("adding null",theOutput,"ERROR: Graph: Node null in : addNode: node\n");
			
			// we test adding an existing node
			// first if: false  |  second if : true
			captureOut();
			assert(graph.addNode(node1) == false);
			theOutput = getOut();
			assertEquals("adding existing node",theOutput,"ERROR: Graph: Node null in : addNode: node\n"
														+ "ERROR: Graph: Node exist : addNode: node\n");
			
		}

	 	
	 	
	 	@Test
		public void testGetNodeChildrenSafetyLayer() {
			String theOutput = null;
			Graph<WeightedNode> graph = new Graph<>();
			WeightedNode node1 = new WeightedNode("node1", 10);
			
			// we test for a null node 
			// first if: true  |  second if : false
			captureOut();
			graph.getNodeChildren(null);
			theOutput = getOut();
			assertEquals("getChildren null",theOutput,"ERROR: Graph: Node null in : getNodeChildren: node\n");
			
			// we add node1 to graph
			assert(graph.addNode(node1) == true);
			assert(graph.contains(node1));
			
			// we test for a non existing node
			// first if: false  |  second if : true
			captureOut();
			WeightedNode node2 = new WeightedNode("node2", 20);
			graph.getNodeChildren(node2);
			theOutput = getOut();
			assertEquals("get children non existing ",theOutput,"ERROR: Graph: Node null in : getNodeChildren: node\n"
													+ "ERROR: Graph: Node doesn't exist : getNodeChildren: node\n");
		
			// we test for an existing node
			// first if: false  |  second if : false
			captureOut();
			Iterator<WeightedNode> it = graph.getNodeChildren(node1);
			int childrenNumber = 0;
			while(it.hasNext()) {
				childrenNumber++; // count children (supposed to be 0)
			}
			assert(childrenNumber == 0); 
			theOutput = getOut();
			assertEquals("get children reassure",theOutput,"ERROR: Graph: Node null in : getNodeChildren: node\n"
					+ "ERROR: Graph: Node doesn't exist : getNodeChildren: node\n"); // nothing more was printed
			
		}

	 	
		@Test
		public void testAddEdge() {
			String theOutput = null;
			Graph<WeightedNode> graph = new Graph<>();
			WeightedNode node1 = new WeightedNode("node1", 10);
			WeightedNode node2 = new WeightedNode("node2", 10);
			assert(graph.addNode(node1) == true);
			assert(graph.contains(node1));
			assert(graph.addNode(node2) == true);
			assert(graph.contains(node2));
			
			// we test with a null source
			// for conditions relevant to source : first if: true  |  second if : false
			captureOut();
			assert(graph.addEdge(null, node1) == false);
			theOutput = getOut();
			assertEquals("addEdge null source",theOutput,"ERROR: Graph: Node null in : addEdge: source\n");
			
			// we test for a non existing source
			// for conditions relevant to source : first if: false  |  second if : true
			captureOut();
			WeightedNode node3 = new WeightedNode("node3", 40);
			assert(graph.addEdge(node3,node1) == false);
			theOutput = getOut();
			assertEquals("addEdge no existing source",theOutput,"ERROR: Graph: Node null in : addEdge: source\n"
													+ "ERROR: Graph: Node doesn't exist : addEdge: source\n");
		
			// we test for an existing source 
			// first if: false  |  second if : false
			captureOut();
			assert(graph.addEdge(node1,node2) == true); // we add an edge that didn't exist before
			assert(graph.addEdge(node1,node2) == false); // we add an edge that existed before
			assertEquals("addEdge no existing source",theOutput,"ERROR: Graph: Node null in : addEdge: source\n"
									+ "ERROR: Graph: Node doesn't exist : addEdge: source\n"); // nothing more was printed
			Iterator<WeightedNode> it = graph.getNodeChildren(node1);
			WeightedNode destinationNode = it.next();
			assert((destinationNode.getName() == node2.getName()) && (destinationNode.getCost() == node2.getCost()));
			int childrenNumber = 1;
			while(it.hasNext()) {
				childrenNumber++; // count children (supposed to be 1)
			}
			assert(childrenNumber == 1);		
		}

	 	
	 	
		@Test (timeout = 300)
		public void testHighLoad() {
			Graph<WeightedNode> graph = new Graph<>();
			for(int i = 0 ; i < 1000 ; i++) { // add 1000 nodes
				assert(graph.addNode(new WeightedNode(Integer.toString(i),i)) == true); 
			}
			for(int i = 0 ; i < 1000 ; i++) { // make sure these 1000 nodes has been added
				assert(graph.contains(new WeightedNode(Integer.toString(i),i)));
			}
			WeightedNode source = new WeightedNode("source",888);
			graph.addNode(source);
			for(int i = 0 ; i < 1000 ; i++) { // connect source to each one of the 1000 nodes
				assert(graph.addEdge(source,new WeightedNode(Integer.toString(i),i)) == true);
			}
			for(int i = 0 ; i < 1000 ; i++) { // make sure source is connected to all of them
				assert(graph.addEdge(source,new WeightedNode(Integer.toString(i),i)) == false);
			}
			
			
		}
		
		
		
}
