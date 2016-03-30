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
				MazeController.this.model = new Maze(MazeController.SML_MAZE_WIDTH, MazeController.SML_MAZE_HEIGHT);
				MazeController.this.view.setMaze(model.getMaze(), MazeController.SML_MAZE_WIDTH, MazeController.SML_MAZE_HEIGHT);
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
