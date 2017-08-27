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
		
		gameFrame.setSize(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT);
		
		ball = new GameBall(GameConstants.ballPosX, GameConstants.ballPosY, GameConstants.ballVelX, GameConstants.ballVelY, GameConstants.ballColor);
		paddle = new GamePaddle(GameConstants.paddlePosX, GameConstants.paddlePosY, GameConstants.paddleWidth, GameConstants.paddleHeight, GameConstants.paddleColor);
		brick = new GameBrick(GameConstants.brickRow, GameConstants.brickColumn);
		
		board = new GameBoard(brick, ball, paddle);
		board.draw();
		
		gameFrame.add(board);
		gameFrame.setVisible(true);		
	}

}
