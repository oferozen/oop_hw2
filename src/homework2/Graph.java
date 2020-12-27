package homework2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A Graph models a set of nodes where between each pair of nodes there could be 1-way
 * or 2-way edges.
 * The following fields are used:
 *      nodes : Set of nodes 
 **/

public class Graph<E extends WeightedNode> {

    HashMap<E, HashSet<E>> nodes = new HashMap<>();
    
    /**
     * Add a node to the graph. If node already exists in the array, do nothing. 
     * @requires node != NULL
     * @modifies 
     * @effects if graph doesn't contain node, add node to graph.
     */
    public void addNode(E node) {
        
        assert(node != null);
        
        if (nodes.containsKey(node)) {
            return;
        }
        
        nodes.put(node, new HashSet<E>());
    }
    
    /**
     * Add an edge between 2 nodes in the graph.
     * @requires source != NULL  &&
     * @         source in graph &&
     * @         dest != NULL     &&
     * @         dest in graph
     * @modifies 
     * @effects if graph contains both nodes, and there is not an edge between them, add an edge
     * @        between them.
     */
    public void addEdge(E source, E dest) {
    	assert(source != null);
    	assert(dest != null);
    	assert(nodes.containsKey(source));
    	assert(nodes.containsKey(dest));
    	assert(! nodes.get(source).contains(dest));
    	
    	nodes.get(source).add(dest);
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
