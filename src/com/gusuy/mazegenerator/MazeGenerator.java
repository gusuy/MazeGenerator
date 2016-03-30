package com.gusuy.mazegenerator;


public class MazeGenerator {

	public static void main(String[] args) {
		MazeView view = new MazeView();
		
		new MazeController(view);
	}

}
