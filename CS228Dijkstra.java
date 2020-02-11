package cs228hw4.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import java.util.Set;
import java.util.Stack;

/**
 * Class that runs the dijkstra algorithm and stores the shortest distance and path 
 * @author Zach Eisele
 *
 * @param <V>
 */
public class CS228Dijkstra<V> implements Dijkstra<V> 
{
	//graph used
	private DiGraph<V> graph;
	
	//PQ with a personal comparator
	private PriorityQueue<V> toVisit= new PriorityQueue<V>(new DiComparator());
	
	//amount of vertices
	private int V;
	
	//stack to keep track of visited vertices
	private Stack<V> visited= new Stack<V>();
	
	//hashmap to keep track of shortest distances
	private HashMap<V,Integer> dist= new HashMap<V,Integer>();
	
	//hashmap for shortest path
	private HashMap<V, List<V>> path= new HashMap<V, List<V>>();
	
	/**
	 * Custom comparator for the priority queue used
	 * @author Zach
	 *
	 */
	class DiComparator implements Comparator<V>
	{
		
		/*
		 * compares the numbers based on their distance values(non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(V a, V b) 
		{
			int v1=dist.get(a);
			int v2=dist.get(b);
			if(v1>v2)
			{
				return 1;
			}
			else if(v2>v1)
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	}
	
	/*
	 * constructor, sets up all data structures
	 * @param a given graph
	 */
	public CS228Dijkstra(DiGraph<V> g)
	{
		graph=g;
		toVisit= new PriorityQueue<V>();
		V=g.numVertices();
		dist=new HashMap<V, Integer>(V); 
		visited= new Stack<V>();
	}
	
	/*
	 * method that actually runs the algorithm, stores the distances and paths in hashmaps
	 * @param a starting vertex
	 */
	@Override
	public void run(V start) 
	{
		//sets up the starting vertices in the data structures as a place to start
		V cur=start;
		toVisit.add(cur);
		LinkedList<V> ll= new LinkedList<V>();
		ll.add(cur);
		dist.put(cur, 0);
		path.put(cur, ll);
		
		//keeps going until all vertices are checked
		while(!toVisit.isEmpty())
		{
			
			Set<V> neighbors= new HashSet<V>(graph.getNeighbors(cur));
			Iterator<V> iter=neighbors.iterator();
			//remove the vertices that the neighbors were taken and added from
			toVisit.remove(cur);
			visited.add(cur);
			
			//corrects and updates each neighbor
			for(int i=0; i<neighbors.size(); i++)
			{
				V addition=iter.next();
				
				//if the neighbor is not already added to toVisit, then put distance to infinity and path as null and add to to visit list
				if(!toVisit.contains(addition))
				{
					dist.put(addition, Integer.MAX_VALUE);
					path.put(addition, new LinkedList<V>());
					toVisit.add(addition);
				}
				
				//if a newest low distance is found, then update 
				if(graph.getEdgeCost(cur, addition)+dist.get(cur)<dist.get(addition))
				{
					int newCost=graph.getEdgeCost(cur, addition)+dist.get(cur);
					dist.replace(addition, newCost);
					
					//if there was a prior path, it should be deleted as it is not optimal
					if(path.get(addition)!=null)
					{
						path.get(addition).clear();
					}
					
					//just puts the path for the prior vertex and then adds the current vertex to be inclusive
					path.get(addition).addAll(this.getShortestPath(cur));				
					path.get(addition).add(addition);
					
				}	
			}
		//the new vertex that will be removed from pq and their neighbors will be added and updated
		cur=toVisit.peek();	
		}
	}
		
		
		
		
	/*
	 * accesses the saved shortest path for the given vertex
	 * @param given vertex
	 */
	@Override
	public List<V> getShortestPath(V vertex) 
	{
		List<V> answer=path.get(vertex);
		return answer;
	}

	/*
	 * Accesses the saved shortest distance for the given vertex
	 * @param desired vertex
	 */
	@Override
	public int getShortestDistance(V vertex) 
	{
		return dist.get(vertex);
	}

	
}