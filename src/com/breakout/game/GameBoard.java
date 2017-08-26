package com.breakout.game;

import javax.swing.JPanel;

public class GameBoard extends JPanel{

	private GameBrick brick;
	private GameBall ball;
	private GamePaddle paddle;
	
	public GameBoard(GameBrick brick, GameBall ball, GamePaddle paddle) {
		this.brick = brick;
		this.ball = ball;
		this.paddle = paddle;
		
		this.setSize(GameConstants.boardDimensions);
		//this.setFocusable(true);
		//this.requestFocusInWindow();
		
	}
}
