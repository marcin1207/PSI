package com;

import java.awt.*;

/************************************************************
 * Provides a visual representation of a vertex so they can
 * be drawn on the grid component.
 * @Author Adam Beecham
 ************************************************************/
public class Komorka extends Wierzcholek {

	private int width;
	private int height;
	private Color kolor;

	/************************************************************
	 * Constructs the cell object
	 * @param pozycja the x and y pixel coordinates of the cell
	 * @param width the pixel width of the cell
	 * @param height the pixel height of the cell
	 ************************************************************/
	public Komorka(Point pozycja, int width, int height){
		super(pozycja);
		this.width = width;
		this.height = height;
		this.kolor = Color.WHITE;
	}

	/************************************************************
	 * Set the fill color of the cell.
	 * @param k the color of the cell
	 ************************************************************/
	public void setKolor(Color kolor){
		this.kolor = kolor;
	}

	/************************************************************
	 * Get the color of this cell
	 ************************************************************/
	public Color getKolor(){
		return this.kolor;
	}

	/************************************************************
	 * Draw the cell
	 ************************************************************/
	public void draw(Graphics g){
		g.setColor(kolor);
		g.fillRect(pozycja.x, pozycja.y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(pozycja.x, pozycja.y, width, height);

	}
}
