package homework2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Pathfinder is a class that receive a graph and then allows a user to check what is the weighted shorted
 * path between two groups of nodes.
 * 
 * @param <E> The node type of the nodes in the graph.
 * @param <P> The path type which must extend Path<E,P>
 */
public class PathFinder <E, P extends Path<E,P>>{

	/**
	 * This method receives 2 sets of nodes, one as a start group, and the second as and end group.
	 * The input nodes are represented as a path with a single node. This is done because the Path 
	 * interface doesn't provides us factory methods to generate paths, but it does allow us to 
	 * represent a node using a path interface
	 * 
	 * @requires graph != null && starts != null && goals != null
	 * @requires graph contains all nodes in starts && graphs contains all nodes in starts
	 * @requires starts and goals nodes are mutually exclusive
	 * @modifies
	 * @return a path which starts in one of the "starts" nodes and ends in one of the "ends" node.
	 */
	
	P FindShortestPath (Graph<E> graph, Set<P> starts, Set<P> goals) {
		
 		Map<E, P> paths = new HashMap<>();		
		for (var path : starts) {
			paths.put(path.getEnd(), path);
		}
				
		Queue<P> active = new PriorityQueue<P>(starts);
		Set<E> finished = new HashSet<E>();
		
		while (active.size() != 0) {
			
			P queueMin = active.poll();
			P queueMinPath = paths.get(queueMin.getEnd());
			
			if (goals.stream().anyMatch((goal) -> goal.getEnd() == queueMin.getEnd())) {
				return queueMinPath;
			}
			
			Iterator<E> it = graph.getNodeChildren(queueMin.getEnd());
			while (it.hasNext()) {
				
				E child = it.next();
				
				boolean childInFinished = finished.contains(child);
				if (childInFinished) continue;
				
				boolean childInActive = active.stream().anyMatch((node) -> child == node.getEnd());
				if (childInActive) continue;
				
				P cpath = queueMinPath.extend(child);
				active.add(cpath);
				paths.put(child, cpath);
			}
			
			finished.add(queueMin.getEnd());
		}
				
		return null;
	}
	
}
