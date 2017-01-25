package com;

import java.awt.*;
import java.util.*;


public class Wierzcholek implements Comparable<Wierzcholek>{	
	protected Point pozycja;
	private double distanceFromStart = Double.POSITIVE_INFINITY;
	private double koszt;
	private Wierzcholek predecessor;
	private ArrayList<Krawedz> krawedzie;
	
	/************************************************************
	 * Constructs a vertex with a 2D point
	 * @param position the position of this vertex in 2D space.
	 ************************************************************/
	public Wierzcholek(Point pozycja){
		this.pozycja = pozycja;
		this.distanceFromStart = Double.POSITIVE_INFINITY;
		this.krawedzie = new ArrayList<Krawedz>();
	}
		
	/************************************************************
	 * Adds an edge to this vertex.
	 * @param edge an edge to be added to this vertex.
	 ************************************************************/
	public void addKrawedz(Krawedz edge){
		krawedzie.add(edge);		
	}
	
	/************************************************************
	 * Sets the distance of this vertex from the start vertex.
	 * @param distanceFromStart the distance of this vertex from
	 * the start vertex.
	 ************************************************************/
	public void setDistanceFromStart(double distanceFromStart){
		this.distanceFromStart = distanceFromStart;
	}	
	
	/************************************************************
	 * Sets the koszt of traversing to vertex.
	 * @param koszt the koszt of traversing to this vertex.
	 ************************************************************/
	public void setCost(double koszt){
		this.koszt = koszt;
	}
	
	/************************************************************
	 * Sets the predecessor of this vertex.
	 * @param predecessor the predecessor of this vertex.
	 ************************************************************/
	public void setPredecessor(Wierzcholek predecessor){
		this.predecessor = predecessor;
	}
	
	/************************************************************
	 * Gets the distance of this vertex from the start vertex.
	 * @return distanceFromStart the distance of this vertex from
	 * the start vertex.
	 ************************************************************/
	public double getDistanceFromStart(){
		return distanceFromStart;
	}
	
	/************************************************************
	 * Gets the koszt of traversing to this vertex. 
	 * @return kosztEstimate the koszt of traversing this vertex from
	 * the start vertex.
	 ************************************************************/
	public double getKoszt(){
		return koszt;
	}
	
	/************************************************************
	 * Gets the position of this vertex.
	 * @return position the position of the vertex in 2D space.
	 ************************************************************/
	public Point getPozycja(){
		return pozycja;
	}
	
	/************************************************************
	 * Gets a specified edge connected to this vertex.
	 * @param index the index of the edge to retrieve from the 
	 * krawedzie arraylist.
	 * @return edge an edge connecting this vertex to another.
	 ************************************************************/
	public Krawedz getEdge(int index){
		return krawedzie.get(index);
	}
	
	/************************************************************
	 * Gets all krawedzie traversable from this node.
	 * @return name the name of the vertex.
	 ************************************************************/	
	public ArrayList<Krawedz> getEdges(){
		return krawedzie;
	}
	
	/************************************************************
	 * Gets the predecessor of this vertex.
	 * @return predecessor the predecessor of this vertex.
	 ************************************************************/
	public Wierzcholek getPredecessor(){
		return predecessor;
	}
	
	/************************************************************
	 * Compares two vertices. 
	 * @param other the vertex to compare with this vertex 
	 * @return sign whether this vertex is greater than, less than 
	 * or equal to another vertex.
	 ************************************************************/
	public int compareTo(Wierzcholek other){
		return Double.compare(koszt, other.koszt);
	}
	
	/************************************************************
	 * Converts this object to string format. 	 
	 * @return string the string version of this object.
	 ************************************************************/
	@Override
	public String toString(){
		return "Vertex " + pozycja.getX() + ", " + pozycja.getY();
	}
	
}
