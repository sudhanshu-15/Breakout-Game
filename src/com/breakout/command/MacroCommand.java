package com.breakout.command;

import java.io.Serializable;
import java.util.ArrayList;

import com.breakout.game.GameBall;
import com.breakout.game.GameBrickList;
import com.breakout.game.GamePaddle;
import com.breakout.game.GameTime;

public class MacroCommand implements Command,Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Command> commands;
	public GameBall ball;
	public GamePaddle paddle;
	public GameBrickList brickList;
	public GameTime timer;
	
	public MacroCommand(GameBall ball, GamePaddle paddle, GameBrickList brickList, GameTime timer){
		commands = new ArrayList<Command>();
		this.ball = ball;
		this.paddle = paddle;
		this.brickList = brickList;
		this.timer = timer;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for(int i = 0; i < commands.size(); i++){
			Command command = commands.get(i);
			command.execute();
		}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		for(int i = 0; i < commands.size(); i++){
			Command command = commands.get(i);
			command.undo();
		}

	}
	
	public void add(Command command){
		if(command != null){
			commands.add(command);
		}
	}
	
	public void remove(Command command){
		int i = commands.indexOf(command);
		if(i >= 0){
			commands.remove(i);
		}
	}

}
