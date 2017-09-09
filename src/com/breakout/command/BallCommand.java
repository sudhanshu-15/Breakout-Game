package com.breakout.command;

import com.breakout.game.GameBall;

public class BallCommand implements Command {
	
	private GameBall ball;
	private int initX, initY;
	
	public BallCommand(GameBall ball){
		this.ball = ball;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		initX = ball.getPosX();
		initY = ball.getPosY();
		
		ball.setPosX(initX + ball.getVelX());
		ball.setPosY(initY + ball.getVelY());
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		ball.setPosX(initX);
		ball.setPosY(initY);
	}

}