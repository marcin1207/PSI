package com;

import java.awt.Color;
import java.awt.Point;
import java.util.*;

/************************************************************
 * Finds the shortest path between 2 points in a grid based
 * map.
 * @author Adam Beecham
 ************************************************************/
public class Silnik {

	public static final int DIJKSTRA = 1;
	public static final int ASTAR =0;
	//cells we have already looked at
	private ArrayList<Komorka>closedList;
	//cells we need to look at
	private PriorityQueue<Komorka>openList;
	private boolean flaga;

	/************************************************************
	 * Constructs a pathfinder and initialises our data
	 * structures.
	 ************************************************************/
	public Silnik(){
		//priority queue means that we can just pull the cells
		//with the best path from the top of the list
		openList = new PriorityQueue<Komorka>();
		closedList = new ArrayList<Komorka>();
	}

	/************************************************************
	 * Finds the shortest path between 2 points in a grid.
	 * @param Start the start cell for the algorithm.
	 * @param Goal the cell this algorithm must find a path to
	 * @param grid the grid to be searched.
	 * @param step the time taken to make 1 iteration of the
	 * algorithm.
	 * @param algorithm the search algorithm for finding the
	 * shortest path.
	 ************************************************************/
	public ArrayList<Komorka> findShortestPath(Komorka start, Komorka goal, Pole grid, int step, int algorithm){

		flaga = true;
		start.setDistanceFromStart(0);
                start.setCost(obliczDystans(start.pozycja, goal.pozycja));

		//begin search from start cell
		openList.add(start);

		long currentTime = System.currentTimeMillis();

		//keep looping until we find the goal or search every
		//node
		while(!openList.isEmpty() && flaga){

			if(!flaga)
				break;

			long timeSinceLastStep = System.currentTimeMillis() - currentTime;

			//Check if its time to take another step in the search
			if(timeSinceLastStep >= step){
				currentTime = System.currentTimeMillis();

				//show the user the progress of the last step on the grid
				grid.update();

				//get the cell with the best path
				Komorka current = openList.poll();
				//we've visited this cell so we color it green
				current.setKolor(Color.GREEN);

				//if we're using A-Star search, we no longer need to look at this
				//node again so we add it to the closed list
				
					closedList.add(current);

				//start node is always magenta
				if(current == start)
					start.setKolor(Color.MAGENTA);

				//goal node is always red
				if(current == goal){
					goal.setKolor(Color.RED);
					grid.update();
					break;
				}

				//examine the edges of the current cell to find the best
				//neighbouring cell to navigate to based on the algorithm the user chose
				for(Krawedz e : current.getEdges()){

					switch(algorithm){
                                            case ASTAR: {
							//get the cell at the end of the current edge
							Komorka next = (Komorka) e.getCel();

							//we only look at cells once with a star
							if(closedList.contains(next))
								continue;

							double distanceFromStart = current.getDistanceFromStart() + e.getKoszt();
							//we use a heuristic to estimate how far away from the goal we are
							double distanceToGoal = obliczDystans(next.getPozycja(), goal.getPozycja());
							//sum the start and goal distances to get an idea of how good this cell would be
							//to navigate to next. this pushes the search towards the goal.
							double estimate = distanceFromStart + distanceToGoal;
							if(next.getKolor() != Color.GREEN && next.getKolor()!= Color.MAGENTA)
								next.setKolor(Color.BLUE);
							//add the cell to the open list if it isnt already there
							if(!openList.contains(next) || distanceFromStart < next.getDistanceFromStart()){
								next.setDistanceFromStart(distanceFromStart);
								next.setCost(estimate);
								next.setPredecessor(current);
								openList.add(next);
							}
							break;
						}
						

						
					}
				}
			}
		}

		//the search is finished so we get the shortest path and return it
		ArrayList<Komorka> shortestPath = new ArrayList<Komorka>();
		Komorka current = goal;
		shortestPath.add(current);

		//navigate backwards from the goal to the start via the shortest path
		while(current.getPredecessor()!= null){
			//store the current cell in the shortest path
			shortestPath.add((Komorka)current.getPredecessor());
			current = (Komorka)current.getPredecessor();
			//highlight it
			if(current != start)
				current.setKolor(Color.ORANGE);
		}
		//show the path to the user
		grid.update();
		//reverse the list so the path is the correct way round
		Collections.reverse(shortestPath);

		return shortestPath;
	}

	//stop the search
	public void stop(){
		flaga = false;
	}

	//a heauristic estimate of the distance between two points in the grid.
	//it slightly overestimates the distance between two cells but provides an
	//adequate estimate for our a star search
	public double obliczDystans(Point p1, Point p2){
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}
}
