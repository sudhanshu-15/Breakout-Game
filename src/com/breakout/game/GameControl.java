package com.breakout.game;

import java.util.ArrayList;

import com.breakout.command.BallCommand;
import com.breakout.command.BrickCommand;
import com.breakout.command.MacroCommand;
import com.breakout.command.PaddleCommand;
import com.breakout.command.TimerCommand;
import com.breakout.observer.Observable;
import com.breakout.observer.Observer;

public class GameControl implements Observer {
	
	private Observable observable;
	private GameBall ball;
	private GamePaddle paddle;
	private GameBrickList brickList;
	private GameCollision gameCollision;
	private GameTime timer;
	private boolean play;
	private MacroCommand macroCommand;
	private ArrayList<MacroCommand> macroCommandArray;
	
	private GameControl(GameBall ball, GamePaddle paddle, GameBrickList brickList, GameTime timer, Observable observable){
		this.ball = ball;
		this.paddle = paddle;
		this.brickList = brickList;
		this.timer = timer;
		this.observable = observable;
		observable.register(this);
		gameCollision = new GameCollision(ball, paddle, brickList);
		this.play = false;
		macroCommandArray = new ArrayList<MacroCommand>();
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(play){
			ball.checkBounds(GameConstants.BOARD_WIDTH - 20, GameConstants.BOARD_HEIGHT);
			gameCollision.ballPaddleCollision();
			ArrayList<Integer> deadBrickList = gameCollision.brickBallCollision();
			BallCommand ballCommand = new BallCommand(ball);
			PaddleCommand paddleCommand = new PaddleCommand(paddle);
			BrickCommand brickCommand = new BrickCommand(brickList, deadBrickList);
			TimerCommand timerCommand = new TimerCommand(timer);
			macroCommand = new MacroCommand();
			macroCommand.add(ballCommand);
			macroCommand.add(paddleCommand);
			macroCommand.add(brickCommand);
			macroCommand.add(timerCommand);
			macroCommand.execute();
			macroCommandArray.add(macroCommand);
		}

	}
	
	public void reset(){
		ball.reset();
		paddle.reset();
		brickList.reset();
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public ArrayList<MacroCommand> getMacroCommandArray() {
		return macroCommandArray;
	}

	public void setMacroCommandArray(ArrayList<MacroCommand> macroCommandArray) {
		this.macroCommandArray = macroCommandArray;
	}

}
