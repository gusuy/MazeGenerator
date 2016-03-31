package com.gusuy.mazegenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


public class MazeController {
	// Square maze for now
	private static final int SML_MAZE_WIDTH = 15;
	private static final int SML_MAZE_HEIGHT = 15;
	private static final int MED_MAZE_WIDTH = 20;
	private static final int MED_MAZE_HEIGHT = 20;
	private static final int LRG_MAZE_WIDTH = 25;
	private static final int LRG_MAZE_HEIGHT = 25;
	
	private Maze model;										// New model instance will be created on button click
	private MazeView view;
	
	public MazeController(MazeView view) {
		this.view = view;
		
		addGenerateButtonListener();
		addMazePanelResizeListener();
	}
	
	
	private void addGenerateButtonListener() {
		this.view.addGenerateButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int mazeWidth, mazeHeight;
				String size = MazeController.this.view.getSizeButtonGroup().getSelection().getActionCommand();
				if (size.equals("small")) {
					mazeWidth = MazeController.SML_MAZE_WIDTH;
					mazeHeight = MazeController.SML_MAZE_HEIGHT;
				}
				else if (size.equals("medium")) {
					mazeWidth = MazeController.MED_MAZE_WIDTH;
					mazeHeight = MazeController.MED_MAZE_HEIGHT;
				}
				else if (size.equals("large")) {
					mazeWidth = MazeController.LRG_MAZE_WIDTH;
					mazeHeight = MazeController.LRG_MAZE_HEIGHT;
				}
				else {
					throw new RuntimeException("Invalid size.");
				}
				
				MazeController.this.model = new Maze(mazeWidth, mazeHeight);
				MazeController.this.view.setMaze(MazeController.this.model.getMaze(), mazeWidth, mazeHeight);
				MazeController.this.view.repaintMaze();
			}
		});
	}
	
	
	private void addMazePanelResizeListener() {
		this.view.addMazePanelResizeListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				if (MazeController.this.view.getMaze() != null) {
					MazeController.this.view.repaintMaze();
				}
			}
			public void componentHidden(ComponentEvent e) {
			}
			public void componentMoved(ComponentEvent e) {
			}
			public void componentShown(ComponentEvent e) {
			}
		});
	}
	
}
