package com.breakout.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GamePlay {
	
	private GameBoard board;
	public GameBrickList brickList;
	public GameBall ball;
	public GamePaddle paddle;
	public GameTime timeDisplay;
	private static boolean gameLoop = true;
	private static GamePlay gamePlay;
	private GameButtonPanel buttonPanel;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				gamePlay = new GamePlay();
			}			
		});	
		
	}
	
	public GamePlay() {
		//Create JFrame and initialize GameBoard object and add it to the Frame
		JFrame gameFrame = new JFrame();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setTitle("Breakout");
		gameFrame.setResizable(false);
		gameFrame.setSize(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGHT);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setLayout(new BorderLayout());

		
		//Initialize GameBall, GamePaddle, GameBrick, GameTime
		ball = new GameBall(GameConstants.BALL_POS_X, GameConstants.BALL_POS_Y, GameConstants.BALL_VEL_X, GameConstants.BALL_VEL_Y, GameConstants.BALL_COLOR);
		paddle = new GamePaddle(GameConstants.PADDLE_POS_X, GameConstants.PADDLE_POS_Y, GameConstants.PADDLE_WIDTH, GameConstants.PADDLE_HEIGHT, GameConstants.PADDLE_COLOR);
		brickList = new GameBrickList();
//		brick = new GameBrick(GameConstants.BRICK_ROW, GameConstants.BRICK_COLUMN);
		timeDisplay = new GameTime();
		
//		board = new GameBoard(ball, paddle, timeDisplay, brickList, this);
//		board.draw();
//		board.gameLoop();
//		
//		gameFrame.add(board);
		
		buttonPanel = new GameButtonPanel(gameFrame);
		gameFrame.add(buttonPanel,BorderLayout.NORTH);
		
		gameFrame.setVisible(true);		
	}
}
