package com.gusuy.mazegenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MazeController {
	// Square maze for now
	private static final int SML_MAZE_WIDTH = 15;
	private static final int SML_MAZE_HEIGHT = 15;
	private static final int MED_MAZE_WIDTH = 20;
	private static final int MED_MAZE_HEIGHT = 20;
	private static final int LRG_MAZE_WIDTH = 25;
	private static final int LRG_MAZE_HEIGHT = 25;
	
	// Temporary constant - will be calculated value
	private static final int cellWidth = 40;
	
	private Maze model;										// New model instance will be created on button click
	private MazeView view;
	
	public MazeController(MazeView view) {
		this.view = view;
		
		addButtonListeners();
	}
	
	
	private void addButtonListeners() {
		this.view.addGenerateButtonListener(new GenerateButtonListener());
	}
	
	
	
	// Inner class to take action when generate maze button is clicked
	private class GenerateButtonListener implements ActionListener {
		// Create new model instance, pass data to view, paint maze
		public void actionPerformed(ActionEvent e) {
			MazeController.this.model = new Maze(MazeController.SML_MAZE_WIDTH, MazeController.SML_MAZE_HEIGHT);
			MazeController.this.view.setMaze(model.getMaze(), MazeController.SML_MAZE_WIDTH, MazeController.SML_MAZE_HEIGHT);
			MazeController.this.view.repaintMaze();
		}
	}
	
	
}
