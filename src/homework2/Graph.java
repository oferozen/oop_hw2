package homework2;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A Graph models a set of nodes where between each pair of nodes there could be 1-way
 * or 2-way edges.
 * The following fields are used:
 *      nodes : Set of nodes 
 **/

public class Graph<E> {

    // Abstraction Function:
    // nodes.keys => graph vertexes
    // nodes.keys[i].set => a directed edge from vertex i to each of the vertexes in the set.
    
    // Representation invariant:
    //
    // for each node E in nodes.keys
    //     for each nodeChild in node.children 
    //         nodes.keys contain nodeChild
    
    private Map<E, HashSet<E>> nodes; 
    
    /**
     * Check if a given node is null. If it is, print error message and return false. 
     * @requires
     * @modifies
     * @effects if node == null print error message.
     * @return node != null
     */
    private boolean validateNodeNullity(E node, String function, String variable) {
        
        boolean valid = true;
        if (node == null) {
        	valid = false;
            System.out.println(String.format("ERROR: Graph: Node null in : %s: %s", 
            								 function, 
            								 variable));
        }
        return valid;
        
    }

    /**
     * Check if a given node exists in the graph and then compare it to expectExists.
     * If mismatch print an error and return false. 
     * @requires function != null
     * @requires variable != null
     * @modifies
     * @effects  nodes.containsKey(node) == expectExists print correct error message
     * @return nodes.containsKey(node) == expectExists
     */
    private boolean validateNodeExists(E node, boolean expectExists, String function, String variable) {
        if(node == null) return false;
        
        boolean valid = nodes.containsKey(node) == expectExists;
        if (!valid) {
            System.out.println(String.format("ERROR: Graph: Node%s exist : %s: %s", 
                                             expectExists ? " doesn't" : "",      
                                             function, 
                                             variable));
        }
        return valid;        
    }
    
    
    /**
     * Checks if this's status is in line with the representation invariant. 
     * If this is not the case then the running of the program will stop since the checking operation is done 
     * using the "assert" function.
     * @effect if assert is enabled, program stops running in case the current status of "this"
     *            does not fulfill the representation invariant
     **/
      private void checkRep() {
          
          Set<E> keys = nodes.keySet();
          
          for (Set<E> nodeChildren : this.nodes.values()) {
              for (E child : nodeChildren ) {
                  assert(keys.contains(child));
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
     * @requires node != NULL && node not in vertices
     * @modifies
     * @effects if this graph doesn't contain the node it will be added as a vertex to the graph.
     * @return true if the node was added to the graph and no error occured.
     */
    public boolean addNode(E node) {
        
        checkRep();
        
        boolean valid = true;
        if (!validateNodeNullity(node, "addNode", "node")) valid = false;
        if (!validateNodeExists(node, false, "addNode", "node")) valid = false; ;
        
        if (valid) {
        	nodes.put(node, new HashSet<E>());
        }
        
        checkRep();
        
        return valid;
    }
    
    /**
     * Add an edge between 2 nodes in the graph.
     * @requires source != NULL &&
     *           source in vertices
     *           dest != NULL &&
     *           dest in vertices
     * @modifies
     * @effects if graph contains both nodes, and there is not an edge between them, add an edge
     * @        between them.
     * @return true if and edge was added, false otherwise
     */
    public boolean addEdge(E source, E dest) {
        
        checkRep();
        
        boolean valid = true;
        
        if (!validateNodeNullity(source, "addEdge", "source")) return false; //if source is null return false
        if (!validateNodeNullity(dest, "addEdge", "dest"))  return false;  //if destination is null return false     
        if (!validateNodeExists(source, true, "addEdge", "source")) return false; // source doesn't exist
        if (!validateNodeExists(dest, true, "addEdge", "dest")) return false; // destination does't exist
        
        
        if (nodes.get(source).contains(dest)) {
        	valid = false;	
        }
        
        if (valid) {
        	nodes.get(source).add(dest);
        }
        
        checkRep();
        
        return valid;
    }

    /**
     * Check whether an element of type E exists as a node in this graph     
     * @return true if this graph contains node, false otherwise
     */
    public boolean contains(E node) {
        
        checkRep();
        
        boolean exists = false;
        boolean valid = true;
        
        if (!validateNodeNullity(node, "contains", "node")) valid = false;
        
        if (valid) {
        	exists = nodes.containsKey(node);
    	}
        
        checkRep();
        
        return exists;
    }
    
    /**
     * Returns an immutable list of all nodes in the graph.
     * @requires
     * @modifies 
     * @effects Returns an immutable list of all nodes in the graph.
     */
    public Iterator<E> getNodes(){
        
        checkRep();
        
        Iterator<E> result = nodes.keySet().iterator();
        
        checkRep();
        
        return result;
    }
    
    /**
     * Returns an immutable list of the children of a node in the graph.
     * @requires node != NULL && node in vertices
     * @modifies    
     * @effects Returns an iterator to the children of the given node.
     */
    public Iterator<E> getNodeChildren(E node){
        
        checkRep();
        
        boolean valid = true;
        if (!validateNodeNullity(node, "getNodeChildren", "node")) valid = false;
        if (!validateNodeExists(node, true, "getNodeChildren", "node")) valid = false;
        
        Iterator<E> result = valid ? nodes.get(node).iterator() : null;
        
        checkRep();
        
        return result;

    }
}
