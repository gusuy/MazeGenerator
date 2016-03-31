package com.gusuy.mazegenerator;

import java.util.ArrayList;


// Represents a cell in the maze
public class Cell {
	public static final int TOP_EDGE = 0;
	public static final int BOT_EDGE = 1;
	public static final int LEFT_EDGE = 2;
	public static final int RIGHT_EDGE = 3;
	
	private Edge[] edges;						// Cell neighbors - top, bottom, left, right. Null represents an outside border of the maze.
	private Boolean visited;					// Set to true when cell visited in generation algorithm
	private ArrayList<Edge> unvisitedNeighbors;	// Represents edges to unvisited neighbors
	
	public Cell() {
		this.edges = new Edge[4];
		this.visited = false;
		this.unvisitedNeighbors = new ArrayList<Edge>();
	}
	
	
//	public Edge[] getEdges() {
//		return this.edges;
//	}
	
	public Edge getEdge(int direction) {
		if (direction > 3 || direction < 0) {
			throw new IllegalArgumentException("Invalid direction.");
		}
		
		return this.edges[direction];
	}
	public void setEdge(Edge edge, int direction) {
		if (direction > 3 || direction < 0) {
			throw new IllegalArgumentException("Invalid direction.");
		}
		
		this.edges[direction] = edge;
	}

	
	private Boolean isVisited() {
		return this.visited;
	}
	public void setVisited(Boolean visited) {
		this.visited = visited;
	}
	
	
	public ArrayList<Edge> getUnvisitedNeighbors() {
		return this.unvisitedNeighbors;
	}
	public void setUnvisitedNeighbors() {
		for (Edge edge : this.edges) {
			if (edge != null && !edge.getDestination(this).isVisited()) {
				this.unvisitedNeighbors.add(edge);
			}
		}
	}
	// Iterates through unvisited neighbors and removes any edges to visited cells
	public void updateUnvisitedNeighbors() {
		for (int i = this.unvisitedNeighbors.size()-1; i >= 0; i--) {
			if (this.unvisitedNeighbors.get(i).getDestination(this).isVisited() == true) {
				this.unvisitedNeighbors.remove(i);
			}
		}
	}
}
