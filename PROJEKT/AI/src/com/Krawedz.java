package com;

/************************************************************
 * A weighted edge that connects two vertices in a graph 
 * together.
 * @author Adam Beecham
 * @version 1.0
 ************************************************************/
public class Krawedz {	
	private int koszt;	
	private Wierzcholek cel;
	
	/************************************************************
	 * Constructs a weighted edge to connect two vertices together.
	 * @param koszt the koszt of traversing this edge to reach the 
	 * cel vertex from the source vertex.
	 * @param cel the cel vertex for this edge.
	 ************************************************************/
	public Krawedz(int koszt, Wierzcholek cel){
		this.koszt = koszt;
		this.cel = cel;
	}
	
	/************************************************************
	 * Gets the koszt of traversing this edge.
	 * @return koszt the koszt of traversing this edge.
	 ************************************************************/
	public int getKoszt(){
		return koszt;
	}
	
	/************************************************************
	 * Gets the cel vertex of this edge
	 * @return cel the cel vertex of this edge.
	 ************************************************************/
	public Wierzcholek getCel(){
		return cel;
	}	
	
	/************************************************************
	 * sets the koszt of traversing this edge.
	 * @param koszt the koszt of traversing this edge.
	 ************************************************************/
	public void setKoszt(int koszt){
		this.koszt = koszt;
	}
}
