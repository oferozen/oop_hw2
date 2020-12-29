package homework2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class PathFinder <E, P extends Path<E,P>>{

	private final Graph<E> graph;
	
	public PathFinder(Graph<E> graph) {
		this.graph = graph;
	}

	P FindShortestPath (Set<P> starts, Set<P> goals) {
		
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
