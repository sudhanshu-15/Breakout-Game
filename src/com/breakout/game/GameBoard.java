package com.breakout.game;

import java.awt.Graphics;
import java.awt.Graphics2D;

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
		this.setFocusable(true);
		this.requestFocusInWindow();
		
	}
	
	public void paintComponents(Graphics g){
		super.paintComponent(g);
		
		ball.draw(g);
		paddle.draw(g);
		brick.draw((Graphics2D)g);
	}
	
	public void draw(){
		repaint();
	}
}
