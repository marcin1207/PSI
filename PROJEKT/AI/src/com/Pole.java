package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/************************************************************
 * A visual representation of a uniform grid representing the
 * search space of the pathfinding class.
 * @author Adam Beecham
 ************************************************************/
public class Pole extends JPanel implements MouseListener{

	private int width;
	private int height;

	private int rowHeight;
	private int columnWidth;

	private int rows;
	private int columns;

	private boolean isSearching;
	private boolean isStartPositionable;
	private boolean isGoalPositionable;

	private Komorka cells[][];
	private Komorka startCell;
	private Komorka goalCell;

	private Silnik pathfinder;

	/************************************************************
	 * Constructs and initialises the Grid object
	 * @param width the pixel width of the grid
	 * @param height the pixel height of the grid
	 * @param rows the number of rows in the grid
	 * @param columns the number of columns in the grid
	 ************************************************************/
	public Pole(int width, int height, int rows, int columns){

		this.width = width;
		this.height = height;

		this.rows = rows;
		this.columns = columns;

		rowHeight = height / rows;
		columnWidth = width / columns;

		isSearching = false;
		isStartPositionable = true;
		isGoalPositionable = false;

		buildGraph();
		addMouseListener(this);

		this.setPreferredSize(new Dimension(width,height));
	}

	/************************************************************
	 * Start the pathfinding algorithm for this grid
	 * @param step the time between each iteration of the
	 * algorithm in milliseconds
	 * @param algorithm the search algorithm to use.
	 ************************************************************/
	public void start(int step , int algorithm){
		isSearching = true;
		pathfinder.findShortestPath(startCell, goalCell, this, step, algorithm);
	}

	/************************************************************
	 * Refresh the grid and draw any updates to the screen
	 ************************************************************/
	public void update(){
		this.repaint();
	}

	/************************************************************
	 * Reset the grid, interrupting any currently running
	 * searches.
	 ************************************************************/
	public void reset(){
		isSearching = false;
		pathfinder.stop();
		pathfinder = new Silnik();
		buildGraph();
		this.repaint();
	}

	/************************************************************
	 * Builds the grid graph for the pathfinder to search. Each
	 * cell in the graph is connected to all of its 8 neighbours
	 * with and edge, diagonal connections costing slightly more
	 * than horizontal or vertical edges
	 ************************************************************/
	private void buildGraph(){
		pathfinder = new Silnik();
		cells = new Komorka[rows][columns];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				cells[i][j] = new Komorka(new Point(i * columnWidth,j * rowHeight),columnWidth,rowHeight);
			}
		}

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(i + 1 < rows)
					cells[i][j].addKrawedz(new Krawedz(columnWidth, cells[i + 1][j]));
				if(j + 1 < columns)
					cells[i][j].addKrawedz(new Krawedz(rowHeight, cells[i][j + 1]));
				if(i - 1 >= 0)
					cells[i][j].addKrawedz(new Krawedz(columnWidth, cells[i - 1][j]));
				if(j - 1 >= 0)
					cells[i][j].addKrawedz(new Krawedz(rowHeight, cells[i][j - 1]));
				if(i + 1 < rows && j + 1 < columns)
					cells[i][j].addKrawedz(new Krawedz((int)(rowHeight * 1.4), cells[i + 1][j + 1]));
				if(i - 1 >= 0 && j - 1 >= 0)
					cells[i][j].addKrawedz(new Krawedz((int)(rowHeight * 1.4), cells[i - 1][j - 1]));
				if(i + 1 < rows && j - 1 >= 0)
					cells[i][j].addKrawedz(new Krawedz((int)(rowHeight * 1.4), cells[i + 1][j - 1]));
				if(i - 1 >= 0 && j + 1 < columns)
					cells[i][j].addKrawedz(new Krawedz((int)(rowHeight * 1.4), cells[i - 1][j + 1]));

			}
		}

		startCell = cells[0][30];
		startCell.setKolor(Color.MAGENTA);
		goalCell = cells[rows -1][columns - 1];
		goalCell.setKolor(Color.RED);
		update();

	}

	/************************************************************
	 * enables the start or goal cell to be repositioned,
	 * depending on the celltype selected by the user.
	 * @param cellType the type of cell to be positioned
	 ************************************************************/
	public void setPositionable(int cellType){
		switch(cellType){
		case 0:
			isStartPositionable = true;
			isGoalPositionable = false;
			break;
		case 1:
			isGoalPositionable = true;
			isStartPositionable = false;
			break;

		}
	}

	/************************************************************
	 * Draws the grid.
	 ************************************************************/
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				cells[i][j].draw(g);
			}
		}

	}

	/************************************************************
	 * Handle user clicks on the grid, placing the start or goal
	 * cell at the position the user selected
	 ************************************************************/
	public void mouseClicked(MouseEvent e){
		if(!isSearching){
			Point mousePos = new Point(e.getX(),e.getY());
			if(isStartPositionable){
				if(startCell != null)
					startCell.setKolor(Color.WHITE);
				startCell = cells[(int)(mousePos.x/columnWidth)][(int)(mousePos.y/rowHeight)];
				startCell.setKolor(Color.MAGENTA);
			}

			if(isGoalPositionable){
				if(goalCell != null)
					goalCell.setKolor(Color.WHITE);
				goalCell = cells[(int)(mousePos.x/columnWidth)][(int)(mousePos.y/rowHeight)];
				goalCell.setKolor(Color.RED);
			}
			update();
		}
	}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

	
}
