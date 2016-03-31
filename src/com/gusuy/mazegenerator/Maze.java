package com.gusuy.mazegenerator;

import java.util.ArrayList;
import java.util.Random;


// Model of the maze
public class Maze {
	private Cell[][] maze;					// Structure of the maze - 2D array of cells
	private int width, height;				// Maze width and height (measured by number of cells)
	
	public Maze(int width, int height) {
		this.width = width;
		this.height = height;
		
		generateMazeStructure();
		// Starting cell is maze[0][0]
		carveMaze(getStartPoint());
	}
	
	
	public Cell[][] getMaze() {
		return maze;
	}
	
	
	public Cell getStartPoint() {
		return maze[0][0];
	}
	
	
	// Generates the structure of the maze with all walls up
	private void generateMazeStructure() {
		// Initialize maze 2D array with given size and fill with cells
		maze = new Cell[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				maze[i][j] = new Cell(i, j);
			}
		}		
		
		// Set edges - for each direction, set for all cells
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Set top and bottom edges
				if (j != 0) {	// Skip top row of cells because edges created as top edge of current cell
					Edge edge = new Edge(maze[i][j-1], maze[i][j]);
					maze[i][j].setEdge(edge, Cell.TOP_EDGE);
					maze[i][j-1].setEdge(edge, Cell.BOT_EDGE);
				}
				// Set left and right edges
				if (i != 0) {	// Skip left-most column of cells because edges created as left edge of current cell
					Edge edge = new Edge(maze[i-1][j], maze[i][j]);
					maze[i][j].setEdge(edge, Cell.LEFT_EDGE);
					maze[i-1][j].setEdge(edge, Cell.RIGHT_EDGE);
				}
			}
		}
	}
	
	
	// Recursive backtracking algorithm
	// Carves a path through a maze initialized with all walls up
	private void carveMaze(Cell curCell) {
		curCell.setVisited(true);
		curCell.setUnvisitedNeighbors();
		ArrayList<Edge> unvisitedNeighbors = curCell.getUnvisitedNeighbors();
		
		while (!unvisitedNeighbors.isEmpty()) {
			// Randomly choose an unvisited neighbor
			Random random = new Random();
			int randInt = random.nextInt(unvisitedNeighbors.size());
			Edge randEdge = unvisitedNeighbors.get(randInt);
			
			// Remove wall
			randEdge.setWall(false);
			
			// Pass destination cell as argument on recursive call
			Cell destCell = randEdge.getDestination(curCell);
			carveMaze(destCell);
			
			// Remove all visited neighbors from unvisited neighbors array
			curCell.updateUnvisitedNeighbors();
		}
		
	}
	
	
}
