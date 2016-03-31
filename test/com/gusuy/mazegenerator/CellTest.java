package com.gusuy.mazegenerator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
	
	private Cell cell = new Cell();
	private Cell topNeighbor = new Cell();
	private Cell botNeighbor = new Cell();
	private Cell rightNeighbor = new Cell();

	private Edge topEdge = new Edge(cell, topNeighbor);
	private Edge botEdge = new Edge(cell, botNeighbor);
	private Edge rightEdge = new Edge(cell, rightNeighbor);
	
	@Before
	public void init() {
		this.cell.setVisited(true);
		this.rightNeighbor.setVisited(true);
		
		this.cell.setEdge(topEdge, Cell.TOP_EDGE);
		this.cell.setEdge(botEdge, Cell.BOT_EDGE);
		this.cell.setEdge(rightEdge, Cell.RIGHT_EDGE);
	}
	
	
	@Test
	public void unvisitedNeighborsShouldBeSet() {
		ArrayList<Edge> testArrayList = new ArrayList<Edge>();
		testArrayList.add(this.topEdge);
		testArrayList.add(this.botEdge);
		
		this.cell.setUnvisitedNeighbors();
		
		assertEquals(testArrayList, this.cell.getUnvisitedNeighbors());
	}

	@Test
	public void unvisitedNeighborsShouldBeUpdated() {
		ArrayList<Edge> testArrayList = new ArrayList<Edge>();
		testArrayList.add(this.botEdge);
		
		this.cell.setUnvisitedNeighbors();
		topNeighbor.setVisited(true);
		this.cell.updateUnvisitedNeighbors();
		
		assertEquals(testArrayList, this.cell.getUnvisitedNeighbors());
	}
}
