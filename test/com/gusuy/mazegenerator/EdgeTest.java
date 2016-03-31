package com.gusuy.mazegenerator;

import static org.junit.Assert.*;

import org.junit.Test;

public class EdgeTest {
	
	private Cell cell1 = new Cell();
	private Cell cell2 = new Cell();
	private Edge edge = new Edge(cell1, cell2);
	

	@Test
	public void testGetDestination() {
		assertEquals(this.cell1, this.edge.getDestination(cell2));
	}

}
