package com.gusuy.mazegenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


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
	
	private Player player;
	
	public MazeController(MazeView view) {
		this.view = view;
		
		addGenerateButtonListener();
		addMazePanelResizeListener();
		addArrowKeyListener();
	}
	
	
	private void addGenerateButtonListener() {
		view.addGenerateButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int mazeWidth, mazeHeight;
				String size = view.getSizeButtonGroup().getSelection().getActionCommand();
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
				
				model = new Maze(mazeWidth, mazeHeight);
				player = new Player(model.getStartPoint());
				view.setMaze(model.getMaze(), mazeWidth, mazeHeight);
				view.setPlayerLocation(player.getCurCell());
				view.calculateCellWidth();
				view.repaintMaze();
				view.requestMazeFocus();
			}
		});
	}
	
	
	private void addMazePanelResizeListener() {
		view.addMazePanelResizeListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				if (view.getMaze() != null) {
					view.calculateCellWidth();
					view.repaintMaze();
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
	
	
	private void addArrowKeyListener() {
		view.addArrowKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						player.move(Cell.TOP_EDGE);
						break;
					case KeyEvent.VK_DOWN:
						player.move(Cell.BOT_EDGE);
						break;
					case KeyEvent.VK_LEFT:
						player.move(Cell.LEFT_EDGE);
						break;
					case KeyEvent.VK_RIGHT:
						player.move(Cell.RIGHT_EDGE);
						break;
					default:
						break;
				}
				view.setPlayerLocation(player.getCurCell());
				view.repaintMaze();
			}
			public void keyTyped(KeyEvent e) {				
			}	
			public void keyReleased(KeyEvent e) {				
			}
		});
	}
	
}
