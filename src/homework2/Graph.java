package homework2;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A Graph models a set of nodes where between each pair of nodes there could be 1-way
 * or 2-way edges.
 * The following fields are used:
 *      nodes : Set of nodes 
 **/

public class Graph<E> {

	// Abstraction Function:
	// every key of type E in the nodes hashMap represents a vertex in the graph.
	// the data that is associated with a specific key (v1) inside the hashMap is a hashSet of E elements
	// such that the directed edge (v1,v2) exists in the graph iff v2 exists in that aforementioned hashSet 
	
	// Representation invariant:
	// for each node v (of type E) in any hashSet the following must be fulfilled: 
	// nodes hashMap must contain v as a key
	
    private Map<E, HashSet<E>> nodes; 
    
    
    /**
     * Checks if this's status is in line with the representation invariant. 
     * If this is not the case then the running of the program will stop since the checking operation is done 
     * using the "assert" function.
     * @effect if assert is enabled, program stops running in case the current status of "this"
     * 		   does not fulfill the representation invariant
     **/
  	private void checkRep() {
  		Map<E, HashSet<E>> nodesTempCopy = new HashMap<>(this.nodes); // used to make sure we maintain iterator independency
  		for (Map.Entry<E, HashSet<E>> entry : this.nodes.entrySet()) {
  			Iterator<E> it = entry.getValue().iterator();
  		     while(it.hasNext()){
  		        assert(nodesTempCopy.containsKey(it));
  		     }
  		}
  	}
  	
  	/**
     * Graph Constructor
     * @effects constructs a new Graph with no vertices.
     **/
  	public Graph() {
  		this.nodes = new HashMap<>();
  		checkRep();
  	}
    
    
    
    /**
     * Add a node to the graph. If node already exists in the graph, do nothing. 
     * @requires node != NULL
     * @modifies  this graph  
     * @effects if this graph doesn't contain node, node will be added as a vertex to the graph.
     * @returns true if node was added, false otherwise
     */
    public boolean addNode(E node) {
    	checkRep();
        if (nodes.containsKey(node)) {
        	checkRep();
            return false;
        }
        nodes.put(node, new HashSet<E>());
        checkRep();
        return true;
    }
    
    /**
     * Add an edge between 2 nodes in the graph.
     * @requires source != NULL &&
     * 			source exists in graph &&
     * 			dest != NULL &&
     * 			dest exists in graph	 
     * @modifies this graph (specifically the nodes hashMap)
     * @effects if graph contains both nodes, and there is not an edge between them, add an edge
     * @        between them.
     * @return true if the edge was currently added, false if the edge existed before
     */
    public boolean addEdge(E source, E dest) {
    	checkRep();
    	if(nodes.get(source).contains(dest)) {
    		checkRep();
    		return false;
    	}
    	nodes.get(source).add(dest);
    	checkRep();
    	return true;
    }

    /**
     * check whether an element of type E exists as a node in this graph	 
     * @return true if this graph contains node, false otherwise
     */
    public boolean conatins(E node) {
    	return this.nodes.containsKey(node);
    }
    
    /**
     * Returns an immutable list of all nodes in the graph.
     * @requires
     * @modifies 
     * @effects Returns an immutable list of all nodes in the graph.
     */
    public Iterator<E> getNodes(){
        return nodes.keySet().iterator();
    }
    
    /**
     * Returns an immutable list of the children of a node in the graph.
     * @requires node != NULL
     * @requires node in graph
     * @modifies    
     * @effects Returns an iterator to the children of node.
     */
    public Iterator<E> getNodeChildren(E node){
        assert(node != null);
        assert(nodes.containsKey(node));
        return nodes.get(node).iterator();
    }
    

    /**
     * Calculate shortest path from source to dest. If path doesn't exists, return
     * an empty list
     * @requires source != NULL  &&
     * @         source in graph &&
     * @         dest != NULL     &&
     * @         dest in graph
     * @modifies
     * @effects Returns an path instance which describe the shortest path from source 
     *          to dest.
     */    
    /*
    public P findPath(E source, E dest){
    	assert(false);
    	
    	Queue<E> visiting = new PriorityQueue();
    	HashSet<E> NotVisited = new HashSet(nodes.keySet());
    	HashSet<E> finished = new HashSet();
    	return null;
    }
    */
}
