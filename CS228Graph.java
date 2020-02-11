package cs228hw4.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CS228Graph<V,E> implements DiGraph<V>
{

	private class Vertex
	{
		private String VID;
		private V data;
		private Vertex(String ID)
		{
			VID=ID;
		}
	}
	
	private class Edge
	{
		private String VID1;
		private String VID2;
		private int data;
		private Edge(String ID1, String ID2, int d)
		{
			VID1=ID1;
			VID2=ID2;
			data=d;
		}
	}
	
	private ArrayList<Vertex> vs;
	private ArrayList<Edge> es;
	
	public CS228Graph()
	{
		vs= new ArrayList<Vertex>();
		vs.add(new Vertex("A"));
		vs.add(new Vertex("B"));
		vs.add(new Vertex("C"));
		vs.add(new Vertex("D"));
		vs.add(new Vertex("E"));
		vs.add(new Vertex("F"));
		vs.add(new Vertex("G"));
		
		es= new ArrayList<Edge>();
		es.add(new Edge("A", "B", 3));
		es.add(new Edge("A", "C", 2));
		es.add(new Edge("A", "D", 7));
		es.add(new Edge("B", "D", 2));
		es.add(new Edge("C", "E", 1));
		es.add(new Edge("C", "F", 4));
		es.add(new Edge("F", "G", 1));
	}
	

	@Override
	public int getEdgeCost(Object start, Object end) 
	{
		for(int i=0; i<es.size(); i++)
		{
			if(start.toString()==es.get(i).VID1&&end.toString()==es.get(i).VID2)
			{
				return es.get(i).data;
			}
		}
		return -1;
	}

	@Override
	public int numVertices() 
	{
		return vs.size();
	}

	@Override
	public Iterator<V> iterator() 
	{
		return this.iterator();
	}

	@Override
	public Set getNeighbors(Object vertex) 
	{
		HashSet<String> hv= new HashSet<String>();
		String target= vertex.toString();
		for(int i=0; i<es.size(); i++)
		{
			if(es.get(i).VID1==target)
			{
				hv.add(es.get(i).VID2);
			}
		}
		return hv;
	}


}