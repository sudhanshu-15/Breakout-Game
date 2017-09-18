package com.breakout.command;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import com.breakout.game.GameConstants;
import com.breakout.game.GamePaddle;

public class PaddleCommand implements Command,Serializable {
	
	private static final long serialVersionUID = 4L;
	
	private GamePaddle paddle;
	private int initX;
	private int direction;
	
	public PaddleCommand(GamePaddle paddle){
		this.paddle = paddle;
		this.direction = paddle.getPaddleDirection();
		this.initX = paddle.getPosX();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		initX = paddle.getPosX();
		if(direction == KeyEvent.VK_RIGHT){
			if(paddle.getPosX() >= GameConstants.PADDLE_RIGHT_BOUND){
				paddle.setPosX(GameConstants.PADDLE_RIGHT_BOUND);
			}else{
				paddle.setPosX(paddle.getPosX() + GameConstants.PADDLE_MOVE );
			}
		}
		
		if(direction == KeyEvent.VK_LEFT){
			if(paddle.getPosX() <= GameConstants.PADDLE_LEFT_BOUND){
				paddle.setPosX(GameConstants.PADDLE_LEFT_BOUND);
			}else{
				paddle.setPosX(paddle.getPosX() - GameConstants.PADDLE_MOVE);
			}
		}
		
		paddle.setPaddleDirection(1000);

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		paddle.setPosX(initX);
	}

}
