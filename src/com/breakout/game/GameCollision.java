package com.breakout.game;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GameCollision {
	
	private GameBall ball;
	private GamePaddle paddle;
	private GameBrickList brickList;
	private GameControl gameControl;
	
	public GameCollision(GameBall ball, GamePaddle paddle, GameBrickList brickList, GameControl gameControl){
		this.ball = ball;
		this.paddle = paddle;
		this.brickList = brickList;
		this.gameControl = gameControl;
	}
	
	public void ballPaddleCollision(){
		Rectangle ballCollider = ball.createCollider(ball.getPosX(), ball.getPosY(), 20, 20);
		Rectangle paddleCollider = paddle.createCollider(paddle.getPosX(), paddle.getPosY(), paddle.getWidth(), paddle.getHeight());
		if(ballCollider.intersects(paddleCollider)){
//			ball.setVelX(-ball.getVelX());
			ball.setVelY(-ball.getVelY());
		}
	}
	
	public ArrayList<Integer> brickBallCollision(){
		Rectangle ballCollider = ball.createCollider(ball.getPosX(), ball.getPosY(), 20, 20);
		ArrayList<Integer> deadBrickList = new ArrayList<Integer>();
		
		for(GameBrick brick : brickList.getBrickArrayList()){
			if(ballCollider.intersects(brick.getBrickCollider()) && !brick.isDead()){
				ball.setVelY(-ball.getVelY());
				int i = brickList.getBrickArrayList().indexOf(brick);
				deadBrickList.add(i);
			}
		}
		return deadBrickList;
	}
	
	public void gameWin(){
		boolean win = true;
		for(GameBrick brick : brickList.getBrickArrayList()){
			if(!brick.isDead()){
				win = false;
			}
		}
		if(win){
			JOptionPane.showMessageDialog(null, "You Win");
			gameControl.reset();
			gameControl.setPlay(false);
		}
	}
	
	public void gameOver(){
		if(ball.getPosY() > GameConstants.BOARD_HEIGHT){
			JOptionPane.showMessageDialog(null, "Game Over! :(");
			gameControl.reset();
			gameControl.setPlay(false);
		}
	}
}
