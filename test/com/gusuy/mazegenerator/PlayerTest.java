package com.gusuy.mazegenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PlayerTest {
	
	private Player player;
	private Cell curCell;
	private Cell destination;
	private Edge edge;
	
	@Before
	public void init() {
		curCell = new Cell(1, 1);
		destination = new Cell(2, 1);
		edge = new Edge(curCell, destination);
		curCell.setEdge(edge, Cell.RIGHT_EDGE);
		
		player = new Player(curCell);
	}

	
	@Test
	public void playerShouldMove() {
		edge.setWall(false);
		player.move(Cell.RIGHT_EDGE);
		
		assertEquals(destination, player.getCurCell());
		assertNotEquals(curCell, player.getCurCell());
	}

	
	@Test
	public void playerShouldNotMove() {
		edge.setWall(true);
		player.move(Cell.RIGHT_EDGE);
		
		assertEquals(curCell, player.getCurCell());
		assertNotEquals(destination, player.getCurCell());
	}
}
