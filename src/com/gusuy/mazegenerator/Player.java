package com.gusuy.mazegenerator;


// Represents a player in the maze
public class Player {
	private Cell curCell;
	
	public Player(Cell startPoint) {
		curCell = startPoint;
	}
	
	
	public Cell getCurCell() {
		return curCell;
	}
	
	
	public void move(int direction) {
		if (direction > 3 || direction < 0) {
			throw new IllegalArgumentException("Invalid direction.");
		}
		
		if (curCell.getEdge(direction) != null && !curCell.getEdge(direction).isWall()) {
			curCell = curCell.getEdge(direction).getDestination(curCell);
		}
	}
}
