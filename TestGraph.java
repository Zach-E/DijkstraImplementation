package cs228hw4.graph;

public class TestGraph 
{
	public static void main(String[] args)
	{
		System.out.println("start");
		CS228Graph dig= new CS228Graph();
		CS228Dijkstra dj=  new CS228Dijkstra(dig);
		dj.run("A");
		System.out.println(dj.getShortestDistance("B"));
		
	}
}