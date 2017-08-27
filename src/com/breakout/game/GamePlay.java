package com.breakout.game;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class GamePlay {
	
	private GameBoard board;
	private GameBrick brick;
	private GameBall ball;
	private GamePaddle paddle;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new GamePlay();
			}
			
		});
		
	}
	
	public GamePlay() {
		JFrame gameFrame = new JFrame();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setTitle("Breakout");
		gameFrame.setSize(600,600);		//TO-DO: Fix the dimension from GameConstants
		
		/*Constant part needs to be fixed.
		 * */
		
		ball = new GameBall(120, 10, -1, -2, Color.RED); //TO-DO: From Constants
		paddle = new GamePaddle(300, 500, 30, 10, Color.GREEN);
		brick = new GameBrick(3, 3);
		
		/*Constant part fix ends*/
		
		board = new GameBoard(brick, ball, paddle);
		board.draw();
		
		gameFrame.add(board);
		gameFrame.setVisible(true);
		
	}

}
