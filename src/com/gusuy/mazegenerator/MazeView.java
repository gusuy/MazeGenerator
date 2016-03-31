package com.gusuy.mazegenerator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class MazeView extends JFrame {	
	private Cell[][] maze;
	private int mazeWidth, mazeHeight, cellWidth;
	
	private JPanel controlPanel;					// Panel containing user input options
	private JButton generateMazeButton;
	private JRadioButton smlRadio, medRadio, lrgRadio;
	private ButtonGroup sizeButtonGroup;

	private JPanel mazePanel;						// Panel containing maze
	
	public MazeView() {
		this.setTitle("Maze Generator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		// Create maze panel and add to frame
		this.mazePanel = new MazePanel();
		this.mazePanel.setPreferredSize(new Dimension(600, 600));
		this.add(mazePanel);
		
		// Create control panel and add to frame
		this.controlPanel = new JPanel();
		this.controlPanel.setLayout(new GridBagLayout());
		this.controlPanel.setPreferredSize(new Dimension(300, 600));
		
		generateMazeButton = new JButton("Generate Maze");
		GridBagConstraints gmConstraint = new GridBagConstraints();
		gmConstraint.gridx = 1; gmConstraint.gridy = 0;
		controlPanel.add(generateMazeButton, gmConstraint);
		
		smlRadio = new JRadioButton("Small", true);
		smlRadio.setActionCommand("small");
		medRadio = new JRadioButton("Medium", false);
		medRadio.setActionCommand("medium");
		lrgRadio = new JRadioButton("Large", false);
		lrgRadio.setActionCommand("large");
		sizeButtonGroup = new ButtonGroup();
		sizeButtonGroup.add(smlRadio); sizeButtonGroup.add(medRadio); sizeButtonGroup.add(lrgRadio);
		GridBagConstraints sConstraint = new GridBagConstraints();
		sConstraint.gridx = 0; sConstraint.gridy = 1;
		GridBagConstraints mConstraint = new GridBagConstraints();
		mConstraint.gridx = 1; mConstraint.gridy = 1;
		GridBagConstraints lConstraint = new GridBagConstraints();
		lConstraint.gridx = 2; lConstraint.gridy = 1;
		controlPanel.add(smlRadio, sConstraint); controlPanel.add(medRadio, mConstraint); controlPanel.add(lrgRadio, lConstraint);
		
		this.add(controlPanel, BorderLayout.EAST);
		
		this.pack();
		this.setVisible(true);
	}
	
	
	public void addGenerateButtonListener(ActionListener generateButtonListener) {
		this.generateMazeButton.addActionListener(generateButtonListener);
	}
	
	
	public void addMazePanelResizeListener(ComponentListener MazePanelResizeListener) {
		this.mazePanel.addComponentListener(MazePanelResizeListener);
	}
	
	
	public ButtonGroup getSizeButtonGroup() {
		return this.sizeButtonGroup;
	}
	
	
	// Sets view's maze data for use when painting
	public void setMaze(Cell[][] maze, int mazeWidth, int mazeHeight) {
		this.maze = maze;
		this.mazeWidth = mazeWidth;
		this.mazeHeight = mazeHeight;
	}
	public Cell[][] getMaze() {
		return this.maze;
	}
	
	
	public void repaintMaze() {
		calculateCellWidth();
		this.mazePanel.repaint();
	}
	
	
	private void calculateCellWidth() {
		if (this.mazeWidth < 1 || this.mazeHeight < 1) {
			throw new IllegalStateException("Maze dimensions must be greater than 0.");
		}
		
		int minPanelDimension = Math.min(this.mazePanel.getSize().width, this.mazePanel.getSize().height);
		int maxMazeDimension = Math.max(this.mazeWidth, this.mazeHeight);
			
		this.cellWidth = minPanelDimension/maxMazeDimension;
	}
	
	
	// Inner class to represent the maze panel
	private class MazePanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			// Iterate through the cells and draw lines where a wall exists
			if (MazeView.this.maze != null) {
				for (int i = 0; i < MazeView.this.mazeWidth; i++) {
					for (int j = 0; j < MazeView.this.mazeHeight; j++) {
						Cell myCell = maze[i][j];
						int xCoord = cellWidth * i;
						int yCoord = cellWidth * j;
						
						// Draw top and bottom walls
						Edge topEdge = myCell.getEdge(Cell.TOP_EDGE);
						if (topEdge == null || topEdge.isWall() == true) {
							g.drawLine(xCoord, yCoord, xCoord+cellWidth, yCoord);
						}
						// For bottom row, draw bottom border as well
						if (j == MazeView.this.mazeHeight-1) {
							g.drawLine(xCoord, yCoord+cellWidth, xCoord+cellWidth, yCoord+cellWidth);
						}
						
						// Draw left and right walls
						Edge leftEdge = myCell.getEdge(Cell.LEFT_EDGE);
						if (leftEdge == null || leftEdge.isWall() == true) {
							g.drawLine(xCoord, yCoord, xCoord, yCoord+cellWidth);
						}
						// For right-most column, draw right border as well
						if (i == MazeView.this.mazeWidth-1) {
							g.drawLine(xCoord+cellWidth, yCoord, xCoord+cellWidth, yCoord+cellWidth);
						}
					}
				}
			}
		}
	}
	
}
