package homework2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A Graph models a set of nodes where between each pair of nodes there could be 1-way
 * or 2-way edges.
 * The following fields are used:
 *      nodes : Set of nodes 
 **/

public abstract class Graph<E, P extends Path<E,P>> {

    HashMap<E, List<E>> m_nodes = new HashMap<>();
    
    /**
     * Add a node to the graph. If node already exists in the array, do nothing. 
     * @requires node != NULL
     * @modifies 
     * @effects if graph doesn't contain node, add node to graph.
     */
    public void addNode(E node) {
        
        assert(node != null);
        
        if (m_nodes.containsKey(node)) {
            return;
        }
        
        m_nodes.put(node, new ArrayList<>());
    }

    /**
     * Returns an immutable list of all nodes in the graph.
     * @requires
     * @modifies 
     * @effects Returns an immutable list of all nodes in the graph.
     */
    public Iterator<E> getNodes(){
        return m_nodes.keySet().iterator();
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
        assert(m_nodes.containsKey(node));
        return m_nodes.get(node).iterator();
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
    public Iterator<E> findPath(E source, E dest){

    	
    	// TODO
    	
    	return null;
    }
}
