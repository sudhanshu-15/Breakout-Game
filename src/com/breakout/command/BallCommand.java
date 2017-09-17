package com.breakout.command;

import java.io.Serializable;

import com.breakout.game.GameBall;

public class BallCommand implements Command,Serializable {
	
	private static final long serialVersionUID = 2L;
	
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

	public int getInitX() {
		return initX;
	}

	public void setInitX(int initX) {
		this.initX = initX;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		ball.setPosX(initX);
		ball.setPosY(initY);
	}

}
