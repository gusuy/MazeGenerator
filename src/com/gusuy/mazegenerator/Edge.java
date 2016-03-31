package com.gusuy.mazegenerator;


// Represents an edge between two cells
public class Edge {
	private Boolean wall;						// Edge may either be a wall or open
	private Cell cell1;
	private Cell cell2;
	
	public Edge(Cell cell1, Cell cell2) {
		wall = true;							// Initialize maze with all edges as walls
		this.cell1 = cell1;
		this.cell2 = cell2;
	}
	
	
	public Boolean isWall() {
		return wall;
	}
	public void setWall(Boolean wall) {
		this.wall = wall;
	}
	
	
	public Cell getDestination(Cell curCell) {
		if (curCell == cell1) {
			return cell2;
		}
		
		return cell1;
	}
}