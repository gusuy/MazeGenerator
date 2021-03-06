package com.gusuy.mazegenerator;


// Represents a player in the maze
public class Player {
	private Cell curCell;
	boolean didFinish;
	
	public Player(Cell startCell) {
		curCell = startCell;
		didFinish = false;
	}
	
	
	public Cell getCurCell() {
		return curCell;
	}
	
	
	public void finished() {
		didFinish = true;
	}
	
	
	public boolean move(int direction) {
		if (direction > 3 || direction < 0) {
			throw new IllegalArgumentException("Invalid direction.");
		}
		
		if (!didFinish && curCell.getEdge(direction) != null && !curCell.getEdge(direction).isWall()) {
			curCell = curCell.getEdge(direction).getDestination(curCell);
			return true;
		}
		
		return false;
	}
}
